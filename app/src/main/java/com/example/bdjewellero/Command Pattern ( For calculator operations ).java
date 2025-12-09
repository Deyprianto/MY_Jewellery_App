package com.example.bdjewellero;


public interface CalculatorCommand {
    void execute();
    void undo();
}

public class CalculatePriceCommand implements CalculatorCommand {
    private final Calculator calculator;
    private final CalculatorStateOriginator originator;
    private final CalculatorStateCaretaker caretaker;
    
    public CalculatePriceCommand(Calculator calculator, 
                               CalculatorStateOriginator originator,
                               CalculatorStateCaretaker caretaker) {
        this.calculator = calculator;
        this.originator = originator;
        this.caretaker = caretaker;
    }
    
    @Override
    public void execute() {
        caretaker.saveMemento(originator.saveToMemento());
        calculator.calculateTotalPrice();
    }
    
    @Override
    public void undo() {
        if (caretaker.hasMemento()) {
            CalculatorMemento memento = caretaker.getMemento();
            originator.restoreFromMemento(memento);
            
            applyStateToCalculator();
        }
    }
    
    private void applyStateToCalculator() {
    }
}

public class ClearCalculatorCommand implements CalculatorCommand {
    private final Calculator calculator;
    private final CalculatorStateOriginator originator;
    private final CalculatorStateCaretaker caretaker;
    
    public ClearCalculatorCommand(Calculator calculator,
                                CalculatorStateOriginator originator,
                                CalculatorStateCaretaker caretaker) {
        this.calculator = calculator;
        this.originator = originator;
        this.caretaker = caretaker;
    }
    
    @Override
    public void execute() { 
        caretaker.saveMemento(originator.saveToMemento());
        calculator.clearCalculator();
    }
    
    @Override
    public void undo() {
        if (caretaker.hasMemento()) {
            CalculatorMemento memento = caretaker.getMemento();
            originator.restoreFromMemento(memento);
            applyStateToCalculator();
        }
    }
    
    private void applyStateToCalculator() {
    }
}

public class CalculatorCommandInvoker {
    private final java.util.Stack<CalculatorCommand> commandHistory = new java.util.Stack<>();
    
    public void executeCommand(CalculatorCommand command) {
        command.execute();
        commandHistory.push(command);
    }
    
    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            CalculatorCommand lastCommand = commandHistory.pop();
            lastCommand.undo();
        }
    }
}
