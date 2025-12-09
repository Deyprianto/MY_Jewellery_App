package com.example.bdjewellero;

public class JewelryItemFactory {
    public static JewelryItem createJewelryItem(String type, String id, String name, 
                                               String weight, String price, String category) {
        switch (type.toLowerCase()) {
            case "chain":
                return new ChainItem(id, name, weight, price, category, "chain-specific-property");
            case "necklace":
                return new NecklaceItem(id, name, weight, price, category, "necklace-specific-property");
            case "ring":
                return new RingItem(id, name, weight, price, category, "ring-specific-property");
            default:
                return new JewelryItem(id, name, weight, price, category);
        }
    }
}

// Extended JewelryItem classes
class ChainItem extends JewelryItem {
    private String chainType;
    
    public ChainItem(String id, String name, String weight, String price, 
                    String category, String chainType) {
        super(id, name, weight, price, category);
        this.chainType = chainType;
    }
    
    public String getChainType() { return chainType; }
}

class NecklaceItem extends JewelryItem {
    private String necklaceDesign;
    
    public NecklaceItem(String id, String name, String weight, String price, 
                       String category, String necklaceDesign) {
        super(id, name, weight, price, category);
        this.necklaceDesign = necklaceDesign;
    }
    
    public String getNecklaceDesign() { return necklaceDesign; }
}

class RingItem extends JewelryItem {
    private String ringSize;
    
    public RingItem(String id, String name, String weight, String price, 
                   String category, String ringSize) {
        super(id, name, weight, price, category);
        this.ringSize = ringSize;
    }
    
    public String getRingSize() { return ringSize; }
}
