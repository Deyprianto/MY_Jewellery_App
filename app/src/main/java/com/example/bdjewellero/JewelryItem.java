package com.example.bdjewellero;

public class JewelryItem {
    private String id;
    private String name, weight, price, category;

    public JewelryItem() {}

    public JewelryItem(String id, String name, String weight, String price, String category) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.category = category;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public String getWeight() { return weight; }
    public String getPrice() { return price; }
    public String getCategory() { return category; }
}
