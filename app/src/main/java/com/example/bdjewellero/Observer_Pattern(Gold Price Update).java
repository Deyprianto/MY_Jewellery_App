package com.example.bdjewellero;

// Observer Pattern
import java.util.ArrayList;
import java.util.List;

public interface GoldPriceObserver {
    void onGoldPriceUpdated(GoldPriceData data);
}

public interface GoldPriceSubject {
    void registerObserver(GoldPriceObserver observer);
    void removeObserver(GoldPriceObserver observer);
    void notifyObservers();
}

public class GoldPriceData {
    private final double price22K;
    private final double price21K;
    private final double price18K;
    
    public GoldPriceData(double price22K, double price21K, double price18K) {
        this.price22K = price22K;
        this.price21K = price21K;
        this.price18K = price18K;
    }
    
    public double getPrice22K() { return price22K; }
    public double getPrice21K() { return price21K; }
    public double getPrice18K() { return price18K; }
}

public class GoldPriceManager implements GoldPriceSubject {
    private static GoldPriceManager instance;
    private final List<GoldPriceObserver> observers = new ArrayList<>();
    private GoldPriceData currentPriceData;
    
    private GoldPriceManager() {}
    
    public static synchronized GoldPriceManager getInstance() {
        if (instance == null) {
            instance = new GoldPriceManager();
        }
        return instance;
    }
    
    @Override
    public void registerObserver(GoldPriceObserver observer) {
        observers.add(observer);
    }
    
    @Override
    public void removeObserver(GoldPriceObserver observer) {
        observers.remove(observer);
    }
    
    @Override
    public void notifyObservers() {
        for (GoldPriceObserver observer : observers) {
            observer.onGoldPriceUpdated(currentPriceData);
        }
    }
    
    public void updateGoldPrices(GoldPriceData newData) {
        this.currentPriceData = newData;
        notifyObservers();
    }
}
