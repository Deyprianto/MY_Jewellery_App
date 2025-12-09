package com.example.bdjewellero;

import java.util.Iterator;
import java.util.List;

public class JewelryCollection implements Iterable<JewelryItem> {
    private List<JewelryItem> items;
    
    public JewelryCollection(List<JewelryItem> items) {
        this.items = items;
    }
    
    @Override
    public Iterator<JewelryItem> iterator() {
        return new JewelryIterator();
    }
    
    private class JewelryIterator implements Iterator<JewelryItem> {
        private int position = 0;
        
        @Override
        public boolean hasNext() {
            return position < items.size();
        }
        
        @Override
        public JewelryItem next() {
            if (hasNext()) {
                return items.get(position++);
            }
            return null;
        }
        
        public JewelryItem previous() {
            if (position > 0) {
                return items.get(--position);
            }
            return null;
        }
        
        public boolean hasPrevious() {
            return position > 0;
        }
    }
    
    // Usage example
    public void iterateThroughJewelry() {
        JewelryCollection collection = new JewelryCollection(jewelryList);
        Iterator<JewelryItem> iterator = collection.iterator();
        
        while (iterator.hasNext()) {
            JewelryItem item = iterator.next();
            // Process item
        }
    }
}
