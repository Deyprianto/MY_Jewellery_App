package com.example.bdjewellero;

public class PriceCalculator {

    public static double calculateTotalPrice(int ed1, int ed2, int ed3, int ed4, int ed5, int ed6) {
        double pointPrice = ((double) ed1 / 960) + ((double) ed2 / 960);
        int totalPoint = (ed3 * 960) + (ed4 * 60) + (ed5 * 10) + ed6;
        return totalPoint * pointPrice;
    }
}
