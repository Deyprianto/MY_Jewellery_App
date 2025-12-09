package com.example.bdjewellero;

import java.util.Iterator;
import java.util.List;

public interface JewelryCollection {
    JewelryIterator createIterator();
}

public class JewelryCollectionImpl implements JewelryCollection {
    private final List<JewelryItem> jewelryItems;
    
    public JewelryCollectionImpl(List<JewelryItem> jewelryItems) {
        this.jewelryItems = jewelryItems;
    }
    
    @Override
    public JewelryIterator createIterator() {
        return new JewelryIteratorImpl(jewelryItems);
    }
}

public interface JewelryIterator extends Iterator<JewelryItem> {
    JewelryItem first();
    JewelryItem current();
    boolean isDone();
}

public class JewelryIteratorImpl implements JewelryIterator {
    private final List<JewelryItem> jewelryItems;
    private int currentPosition = 0;
    
    public JewelryIteratorImpl(List<JewelryItem> jewelryItems) {
        this.jewelryItems = jewelryItems;
    }
    
    @Override
    public boolean hasNext() {
        return currentPosition < jewelryItems.size();
    }
    
    @Override
    public JewelryItem next() {
        if (hasNext()) {
            return jewelryItems.get(currentPosition++);
        }
        return null;
    }
    
    @Override
    public JewelryItem first() {
        currentPosition = 0;
        return jewelryItems.isEmpty() ? null : jewelryItems.get(0);
    }
    
    @Override
    public JewelryItem current() {
        if (currentPosition >= 0 && currentPosition < jewelryItems.size()) {
            return jewelryItems.get(currentPosition);
        }
        return null;
    }
    
    @Override
    public boolean isDone() {
        return currentPosition >= jewelryItems.size();
    }
    
    public List<JewelryItem> filterByCategory(String category) {
        return jewelryItems.stream()
            .filter(item -> item.getCategory().equalsIgnoreCase(category))
            .collect(java.util.stream.Collectors.toList());
    }
}
