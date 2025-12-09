package com.example.bdjewellero;


public interface JewelryDataAdapter {
    String getFormattedName();
    String getFormattedWeight();
    String getFormattedPrice();
    String getFormattedCategory();
}

public class JewelryItemAdapter implements JewelryDataAdapter {
    private final JewelryItem jewelryItem;
    
    public JewelryItemAdapter(JewelryItem jewelryItem) {
        this.jewelryItem = jewelryItem;
    }
    
    @Override
    public String getFormattedName() {
        return "Item: " + jewelryItem.getName();
    }
    
    @Override
    public String getFormattedWeight() {
        return "Weight: " + jewelryItem.getWeight() + " grams";
    }
    
    @Override
    public String getFormattedPrice() {
        return "Price: ৳" + String.format("%,.2f", Double.parseDouble(jewelryItem.getPrice()));
    }
    
    @Override
    public String getFormattedCategory() {
        return "Category: " + jewelryItem.getCategory().toUpperCase();
    }
}
