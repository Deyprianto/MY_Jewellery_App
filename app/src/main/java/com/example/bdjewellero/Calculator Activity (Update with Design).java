package com.example.bdjewellero;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Calculator extends AppCompatActivity implements GoldPriceObserver {
    
    EditText editText1, editText2, editText3, editText4, editText5, editText6;
    TextView textView;
    Button button, button2, buttonUndo;
    
   
    private CalculatorStateOriginator stateOriginator;
    private CalculatorStateCaretaker stateCaretaker;
    private CalculatorCommandInvoker commandInvoker;
    private GoldPriceManager goldPriceManager;
    private JewelryCalculationFacade calculationFacade;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initializing UI components
        editText1 = findViewById(R.id.edText1);
        editText2 = findViewById(R.id.edtext2);
        editText3 = findViewById(R.id.edText3);
        editText4 = findViewById(R.id.edText4);
        editText5 = findViewById(R.id.edText5);
        editText6 = findViewById(R.id.edText6);
        textView = findViewById(R.id.textview);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        buttonUndo = findViewById(R.id.buttonUndo);
        
       
        stateOriginator = new CalculatorStateOriginator();
        stateCaretaker = new CalculatorStateCaretaker();
        commandInvoker = new CalculatorCommandInvoker();
        goldPriceManager = GoldPriceManager.getInstance();
        goldPriceManager.registerObserver(this);
        calculationFacade = new JewelryCalculationFacade();


        saveCurrentState();

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearCalculatorCommand clearCommand = new ClearCalculatorCommand(
                    Calculator.this, stateOriginator, stateCaretaker
                );
                commandInvoker.executeCommand(clearCommand);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalculatePriceCommand calculateCommand = new CalculatePriceCommand(
                    Calculator.this, stateOriginator, stateCaretaker
                );
                commandInvoker.executeCommand(calculateCommand);
            }
        });
        
        buttonUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commandInvoker.undoLastCommand();
            }
        });
    }
    
    private void saveCurrentState() {
        stateOriginator.setState(
            editText1.getText().toString(),
            editText2.getText().toString(),
            editText3.getText().toString(),
            editText4.getText().toString(),
            editText5.getText().toString(),
            editText6.getText().toString(),
            textView.getText().toString()
        );
    }
    
    public void restoreState(CalculatorMemento memento) {
        if (memento != null) {
            editText1.setText(memento.getEd1());
            editText2.setText(memento.getEd2());
            editText3.setText(memento.getEd3());
            editText4.setText(memento.getEd4());
            editText5.setText(memento.getEd5());
            editText6.setText(memento.getEd6());
            textView.setText(memento.getResult());
            
           
            stateOriginator.setState(
                memento.getEd1(), memento.getEd2(), memento.getEd3(),
                memento.getEd4(), memento.getEd5(), memento.getEd6(),
                memento.getResult()
            );
        }
    }
    
    public void calculateTotalPrice() {
        try {
            int edValue1 = parseInput(editText1);
            int edValue2 = parseInput(editText2);
            int edValue3 = parseInput(editText3);
            int edValue4 = parseInput(editText4);
            int edValue5 = parseInput(editText5);
            int edValue6 = parseInput(editText6);

          
            int[] inputs = {edValue1, edValue2, edValue3, edValue4, edValue5, edValue6};
            double totalPrice = calculationFacade.calculateTotalPriceWithTax(inputs);
            String breakdown = calculationFacade.getPriceBreakdown(inputs);

            textView.setText(String.format("Total Price (with taxes): ৳%.2f\n\n%s", totalPrice, breakdown));
            
         
            saveCurrentState();
        } catch (Exception e) {
            textView.setText("Error: Please enter valid numbers.");
        }
    }
    
    public void clearCalculator() {
        editText1.setText("");
        editText2.setText("");
        editText3.setText("");
        editText4.setText("");
        editText5.setText("");
        editText6.setText("");
        textView.setText("Price will show here");
        
        
        saveCurrentState();
    }
    
    @Override
    public void onGoldPriceUpdated(GoldPriceData data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Update UI or provide suggestion based on current gold prices
                TextView goldPriceHint = findViewById(R.id.goldPriceHint);
                if (goldPriceHint != null) {
                    goldPriceHint.setText(String.format(
                        "Current Gold Rates:\n22K: ৳%.2f/g | 21K: ৳%.2f/g | 18K: ৳%.2f/g",
                        data.getPrice22K(), data.getPrice21K(), data.getPrice18K()
                    ));
                }
            }
        });
    }
    
    private int parseInput(EditText editText) {
        String text = editText.getText().toString().trim();
        return text.isEmpty() ? 0 : Integer.parseInt(text);
    }
    
    public CalculatorStateOriginator getStateOriginator() {
        return stateOriginator;
    }
    
    public CalculatorStateCaretaker getStateCaretaker() {
        return stateCaretaker;
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        goldPriceManager.removeObserver(this);
    }
}
