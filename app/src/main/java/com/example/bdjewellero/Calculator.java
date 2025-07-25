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

public class Calculator extends AppCompatActivity {

    EditText editText1, editText2, editText3, editText4, editText5, editText6;
    TextView textView;
    Button button,button2;

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


        button2.setOnClickListener(new View.OnClickListener() {
                                       @Override
            public void onClick(View v) {
                  editText1.setText("");
                  editText2.setText("");
                  editText3.setText("");
                  editText4.setText("");
                  editText5.setText("");
                  editText6.setText("");
                  textView.setText("Price will show here");
         }
             });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTotalPrice();
            }
        });
    }


    private void calculateTotalPrice() {
        try {
            int edValue1 = parseInput(editText1);
            int edValue2 = parseInput(editText2);
            int edValue3 = parseInput(editText3);
            int edValue4 = parseInput(editText4);
            int edValue5 = parseInput(editText5);
            int edValue6 = parseInput(editText6);

            // ✅ FIXED: Correct floating-point division
            double pointPrice = ((double) edValue1 / 960) + ((double) edValue2 / 960);

            int totalPoint = (edValue3 * 960) + (edValue4 * 60) + (edValue5 * 10) + edValue6;

            // ✅ FIXED: Using double for accurate multiplication
            double totalPrice = totalPoint * pointPrice;

            textView.setText(String.format("Total Price: %.2f", totalPrice));
        } catch (Exception e) {
            textView.setText("Error: Please enter valid numbers.");
        }
    }

    private int parseInput(EditText editText) {
        String text = editText.getText().toString().trim();
        return text.isEmpty() ? 0 : Integer.parseInt(text);
    }


}
