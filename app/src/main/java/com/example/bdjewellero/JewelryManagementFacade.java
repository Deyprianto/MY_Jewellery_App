package com.example.bdjewellero;

public class JewelryManagementFacade {
    private DatabaseManager dbManager;
    private PriceCalculator priceCalculator;
    private JewelryItemFactory itemFactory;
    private JewelryCommandInvoker commandInvoker;
    
    public JewelryManagementFacade() {
        dbManager = DatabaseManager.getInstance();
        priceCalculator = new PriceCalculator();
        itemFactory = new JewelryItemFactory();
        commandInvoker = new JewelryCommandInvoker();
    }
    
    public void addJewelryItem(String type, String name, String weight, 
                              String price, String category, ManageJewelryActivity activity) {
        String id = dbManager.getDatabaseReference().push().getKey();
        JewelryItem item = itemFactory.createJewelryItem(type, id, name, weight, price, category);
        
        AddJewelryCommand command = new AddJewelryCommand(activity, item);
        commandInvoker.executeCommand(command);
    }
    
    public void deleteJewelryItem(JewelryItem item) {
        DeleteJewelryCommand command = new DeleteJewelryCommand(item);
        commandInvoker.executeCommand(command);
    }
    
    public double calculateJewelryPrice(int ed1, int ed2, int ed3, int ed4, int ed5, int ed6) {
        return priceCalculator.calculateTotalPrice(ed1, ed2, ed3, ed4, ed5, ed6);
    }
    
    public void undoLastAction() {
        commandInvoker.undo();
    }
}

// Usage in ManageJewelryActivity.java (modified)
public class ManageJewelryActivity extends AppCompatActivity {
    private JewelryManagementFacade jewelryFacade;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ... existing code ...
        
        jewelryFacade = new JewelryManagementFacade();
        
        btnSave.setOnClickListener(v -> {
            String name = editName.getText().toString();
            String weight = editWeight.getText().toString();
            String price = editPrice.getText().toString();
            String category = editCategory.getText().toString();
            
            if (!name.isEmpty() && !weight.isEmpty() && !price.isEmpty() && !category.isEmpty()) {
                // Use facade to add item
                jewelryFacade.addJewelryItem(category, name, weight, price, category, this);
                
                // Clear fields
                editName.setText("");
                editWeight.setText("");
                editPrice.setText("");
                editCategory.setText("");
            }
        });
    }
    
    // Add undo button functionality
    public void onUndoClick(View view) {
        jewelryFacade.undoLastAction();
    }
}
