package com.example.bdjewellero;


import java.util.Stack;

public class CalculatorStateCaretaker {
    private final Stack<CalculatorMemento> mementoStack = new Stack<>();
    
    public void saveMemento(CalculatorMemento memento) {
        mementoStack.push(memento);
    }
    
    public CalculatorMemento getMemento() {
        if (!mementoStack.isEmpty()) {
            return mementoStack.pop();
        }
        return null;
    }
    
    public boolean hasMemento() {
        return !mementoStack.isEmpty();
    }
    
    public void clearHistory() {
        mementoStack.clear();
    }
}
