package com.example.bdjewellero;


public class JewelryItem {
    private String id;
    private String name, weight, price, category;
    private String description;
    private String makingCharge;
    private String dateAdded;
    
    public JewelryItem() {}
    
    public JewelryItem(String id, String name, String weight, String price, String category) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.category = category;
        this.dateAdded = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            .format(new java.util.Date());
    }
    
   
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
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getMakingCharge() { return makingCharge; }
    public void setMakingCharge(String makingCharge) { this.makingCharge = makingCharge; }
    
    public String getDateAdded() { return dateAdded; }
    public void setDateAdded(String dateAdded) { this.dateAdded = dateAdded; }
    
  
    public static JewelryItem fromJewelry(Jewelry jewelry) {
        JewelryItem item = new JewelryItem(
            jewelry.getId(),
            jewelry.getName(),
            jewelry.getWeight(),
            jewelry.getPrice(),
            jewelry.getCategory()
        );
        item.setDescription(jewelry.getDescription());
        return item;
    }
}
