package com.example.bdjewellero;

public class SilverPriceStrategy implements PriceStrategy {
    @Override
    public double calculateTotalPrice(int ed1, int ed2, int ed3, int ed4, int ed5, int ed6) {
        double pointPrice = ((double) ed1 / 480) + ((double) ed2 / 480);
        int totalPoint = (ed3 * 480) + (ed4 * 24) + (ed5 * 10) + ed6;
        return totalPoint * pointPrice;
    }
}
