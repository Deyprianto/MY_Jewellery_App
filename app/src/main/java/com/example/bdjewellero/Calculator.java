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
    Button button, button2, goldButton, silverButton, diamondButton;

    private PriceCalculator priceCalculator; // context

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

        editText1 = findViewById(R.id.edText1);
        editText2 = findViewById(R.id.edtext2);
        editText3 = findViewById(R.id.edText3);
        editText4 = findViewById(R.id.edText4);
        editText5 = findViewById(R.id.edText5);
        editText6 = findViewById(R.id.edText6);
        textView = findViewById(R.id.textview);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        // Strategy selection buttons
        goldButton = findViewById(R.id.goldButton);
        silverButton = findViewById(R.id.silverButton);
        diamondButton = findViewById(R.id.diamondButton);

        // Default strategy → Gold
        priceCalculator = new PriceCalculator(new GoldPriceStrategy());

        goldButton.setOnClickListener(v -> {
            priceCalculator.setStrategy(new GoldPriceStrategy());
            textView.setText("Gold Price Calculator Selected");
        });

        silverButton.setOnClickListener(v -> {
            priceCalculator.setStrategy(new SilverPriceStrategy());
            textView.setText("Silver Price Calculator Selected");
        });

        diamondButton.setOnClickListener(v -> {
            priceCalculator.setStrategy(new DiamondPriceStrategy());
            textView.setText("Diamond Price Calculator Selected");
        });

        button2.setOnClickListener(v -> {
            editText1.setText("");
            editText2.setText("");
            editText3.setText("");
            editText4.setText("");
            editText5.setText("");
            editText6.setText("");
            textView.setText("Price will show here");
        });

        button.setOnClickListener(v -> calculateTotalPrice());
    }

    private void calculateTotalPrice() {
        try {
            int v1 = parseInput(editText1);
            int v2 = parseInput(editText2);
            int v3 = parseInput(editText3);
            int v4 = parseInput(editText4);
            int v5 = parseInput(editText5);
            int v6 = parseInput(editText6);

            double totalPrice = priceCalculator.calculateTotalPrice(v1, v2, v3, v4, v5, v6);
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
