package com.example.bdjewellero;

import java.util.ArrayList;
import java.util.List;

public class PriceUpdateObserver {
    public interface PriceListener {
        void onPriceUpdated(String priceType, double price);
    }
    
    private static PriceUpdateObserver instance;
    private List<PriceListener> listeners = new ArrayList<>();
    
    private PriceUpdateObserver() {}
    
    public static PriceUpdateObserver getInstance() {
        if (instance == null) {
            instance = new PriceUpdateObserver();
        }
        return instance;
    }
    
    public void addListener(PriceListener listener) {
        listeners.add(listener);
    }
    
    public void removeListener(PriceListener listener) {
        listeners.remove(listener);
    }
    
    public void notifyPriceUpdate(String priceType, double price) {
        for (PriceListener listener : listeners) {
            listener.onPriceUpdated(priceType, price);
        }
    }
}

// Usage in Live_Price.java (modified)
public class Live_Price extends AppCompatActivity implements PriceUpdateObserver.PriceListener {
    // ... existing code ...
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ... existing code ...
        
        // Register as observer
        PriceUpdateObserver.getInstance().addListener(this);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unregister observer
        PriceUpdateObserver.getInstance().removeListener(this);
    }
    
    @Override
    public void onPriceUpdated(String priceType, double price) {
        runOnUiThread(() -> {
            switch (priceType) {
                case "22K":
                    text4.setText(String.valueOf(price));
                    break;
                case "21K":
                    text5.setText(String.valueOf(price));
                    break;
                case "18K":
                    text6.setText(String.valueOf(price));
                    break;
            }
        });
    }
}
