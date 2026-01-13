package com.example.bdjewellero;

public class PriceCalculator {
    private PriceStrategy strategy;

    // Constructor to set a strategy
    public PriceCalculator(PriceStrategy strategy) {
        this.strategy = strategy;
    }

    // Method to change strategy at runtime
    public void setStrategy(PriceStrategy strategy) {
        this.strategy = strategy;
    }

    // Delegate calculation to the current strategy
    public double calculateTotalPrice(int ed1, int ed2, int ed3, int ed4, int ed5, int ed6) {
        return strategy.calculateTotalPrice(ed1, ed2, ed3, ed4, ed5, ed6);
    }
}
