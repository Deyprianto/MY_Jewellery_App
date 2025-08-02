package com.example.bdjewellero;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;


class JewelryItem {
    private String id, name, category;
    private double weight, price;

    public JewelryItem(String id, String name, double weight, double price, String category) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.category = category;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getWeight() { return weight; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
}


class TestableJewelryAdapter {
    private List<JewelryItem> jewelryList;

    public TestableJewelryAdapter(List<JewelryItem> jewelryList) {
        this.jewelryList = jewelryList;
    }

    public int getItemCount() {
        return jewelryList.size();
    }

    public void removeItem(int position) {
        jewelryList.remove(position);
    }

    public JewelryItem getItemAt(int position) {
        return jewelryList.get(position);
    }
}

public class JewelryAdapterTest {

    private TestableJewelryAdapter adapter;
    private List<JewelryItem> mockList;

    @Before
    public void setUp() {
        mockList = new ArrayList<>();
        mockList.add(new JewelryItem("1", "Ring", 5.0, 15000.0, "Gold"));
        mockList.add(new JewelryItem("2", "Necklace", 10.0, 30000.0, "Diamond"));

        adapter = new TestableJewelryAdapter(mockList);
    }

    @Test
    public void testItemCount() {
        assertEquals(2, adapter.getItemCount());
    }

    @Test
    public void testRemoveItem() {
        adapter.removeItem(0);
        assertEquals(1, adapter.getItemCount());
        assertEquals("Necklace", adapter.getItemAt(0).getName());
    }

    @Test
    public void testGetItemAt() {
        JewelryItem item = adapter.getItemAt(1);
        assertEquals("Necklace", item.getName());
        assertEquals("Diamond", item.getCategory());
    }
}
