package com.example.bdjewellero;


public abstract class Jewelry {
    protected String id;
    protected String name;
    protected String weight;
    protected String price;
    protected String category;
    
    public abstract String getDescription();
    public abstract double calculateMakingCharge();
    
   
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getWeight() { return weight; }
    public void setWeight(String weight) { this.weight = weight; }
    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}

public class ChainJewelry extends Jewelry {
    private double chainLength;
    
    public ChainJewelry(String id, String name, String weight, String price, double chainLength) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.category = "Chain";
        this.chainLength = chainLength;
    }
    
    @Override
    public String getDescription() {
        return String.format("Chain: %s (%.1f inches)", name, chainLength);
    }
    
    @Override
    public double calculateMakingCharge() {
        double basePrice = Double.parseDouble(price);
        return basePrice * 0.08; 
    }
}

public class RingJewelry extends Jewelry {
    private String ringSize;
    private boolean hasStone;
    
    public RingJewelry(String id, String name, String weight, String price, 
                      String ringSize, boolean hasStone) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.category = "Ring";
        this.ringSize = ringSize;
        this.hasStone = hasStone;
    }
    
    @Override
    public String getDescription() {
        return String.format("Ring: %s (Size: %s, Stone: %s)", 
                           name, ringSize, hasStone ? "Yes" : "No");
    }
    
    @Override
    public double calculateMakingCharge() {
        double basePrice = Double.parseDouble(price);
        double charge = basePrice * 0.10; 
        if (hasStone) {
            charge += basePrice * 0.05; 
        }
        return charge;
    }
}

public class JewelryFactory {
    public static Jewelry createJewelry(String type, String id, String name, 
                                       String weight, String price, Object... params) {
        switch (type.toLowerCase()) {
            case "chain":
                double length = (double) params[0];
                return new ChainJewelry(id, name, weight, price, length);
                
            case "ring":
                String size = (String) params[0];
                boolean hasStone = (boolean) params[1];
                return new RingJewelry(id, name, weight, price, size, hasStone);
                
            case "necklace":
                // Implement NecklaceJewelry class similarly
                return new SimpleJewelry(id, name, weight, price, "Necklace");
                
            case "bangle":
                return new SimpleJewelry(id, name, weight, price, "Bangle");
                
            case "earring":
                return new SimpleJewelry(id, name, weight, price, "Earring");
                
            case "pendant":
                return new SimpleJewelry(id, name, weight, price, "Pendant");
                
            default:
                throw new IllegalArgumentException("Unknown jewelry type: " + type);
        }
    }
}

class SimpleJewelry extends Jewelry {
    public SimpleJewelry(String id, String name, String weight, String price, String category) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.category = category;
    }
    
    @Override
    public String getDescription() {
        return String.format("%s: %s", category, name);
    }
    
    @Override
    public double calculateMakingCharge() {
        double basePrice = Double.parseDouble(price);
        return basePrice * 0.07; 
    }
}
