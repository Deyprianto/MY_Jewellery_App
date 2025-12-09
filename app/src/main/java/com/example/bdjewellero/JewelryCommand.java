package com.example.bdjewellero;

public interface JewelryCommand {
    void execute();
    void undo();
}

class AddJewelryCommand implements JewelryCommand {
    private ManageJewelryActivity activity;
    private JewelryItem item;
    private DatabaseManager dbManager;
    
    public AddJewelryCommand(ManageJewelryActivity activity, JewelryItem item) {
        this.activity = activity;
        this.item = item;
        this.dbManager = DatabaseManager.getInstance();
    }
    
    @Override
    public void execute() {
        dbManager.getDatabaseReference().child(item.getId()).setValue(item);
    }
    
    @Override
    public void undo() {
        dbManager.getDatabaseReference().child(item.getId()).removeValue();
    }
}

class DeleteJewelryCommand implements JewelryCommand {
    private JewelryItem item;
    private DatabaseManager dbManager;
    
    public DeleteJewelryCommand(JewelryItem item) {
        this.item = item;
        this.dbManager = DatabaseManager.getInstance();
    }
    
    @Override
    public void execute() {
        dbManager.getDatabaseReference().child(item.getId()).removeValue();
    }
    
    @Override
    public void undo() {
        dbManager.getDatabaseReference().child(item.getId()).setValue(item);
    }
}

// Command Invoker
class JewelryCommandInvoker {
    private List<JewelryCommand> commandHistory = new ArrayList<>();
    private int currentPosition = -1;
    
    public void executeCommand(JewelryCommand command) {
        command.execute();
        commandHistory.add(command);
        currentPosition++;
    }
    
    public void undo() {
        if (currentPosition >= 0) {
            JewelryCommand command = commandHistory.get(currentPosition);
            command.undo();
            currentPosition--;
        }
    }
}
