package com.example.bdjewellero;

import java.util.ArrayList;
import java.util.List;

public class CalculatorStateMemento {
    private List<String> editTextValues;
    private String result;
    
    public CalculatorStateMemento(List<String> values, String result) {
        this.editTextValues = new ArrayList<>(values);
        this.result = result;
    }
    
    public List<String> getEditTextValues() { return editTextValues; }
    public String getResult() { return result; }
}

// Originator class
class CalculatorState {
    private List<String> editTextValues = new ArrayList<>();
    private String result;
    
    public void setState(List<String> values, String result) {
        this.editTextValues = new ArrayList<>(values);
        this.result = result;
    }
    
    public CalculatorStateMemento saveState() {
        return new CalculatorStateMemento(editTextValues, result);
    }
    
    public void restoreState(CalculatorStateMemento memento) {
        this.editTextValues = memento.getEditTextValues();
        this.result = memento.getResult();
    }
    
    public List<String> getEditTextValues() { return editTextValues; }
    public String getResult() { return result; }
}

// Usage in Calculator.java (modified)
public class Calculator extends AppCompatActivity {
    private CalculatorState calculatorState = new CalculatorState();
    private CalculatorStateMemento currentMemento;
    
    // ... existing code ...
    
    private void saveCurrentState() {
        List<String> currentValues = new ArrayList<>();
        currentValues.add(editText1.getText().toString());
        currentValues.add(editText2.getText().toString());
        currentValues.add(editText3.getText().toString());
        currentValues.add(editText4.getText().toString());
        currentValues.add(editText5.getText().toString());
        currentValues.add(editText6.getText().toString());
        
        String currentResult = textView.getText().toString();
        
        calculatorState.setState(currentValues, currentResult);
        currentMemento = calculatorState.saveState();
    }
    
    private void restoreState() {
        if (currentMemento != null) {
            calculatorState.restoreState(currentMemento);
            
            List<String> values = calculatorState.getEditTextValues();
            if (values.size() >= 6) {
                editText1.setText(values.get(0));
                editText2.setText(values.get(1));
                editText3.setText(values.get(2));
                editText4.setText(values.get(3));
                editText5.setText(values.get(4));
                editText6.setText(values.get(5));
            }
            
            textView.setText(calculatorState.getResult());
        }
    }
}
