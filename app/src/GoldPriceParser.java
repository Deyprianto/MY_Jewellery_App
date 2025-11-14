package com.example.bdjewellero;

public class GoldPriceParser {

    public static int parsePriceToBDT(String priceText) {
        return Integer.parseInt(priceText.replaceAll("[^0-9]", ""));
    }

    public static int convertToVori(int perGramPrice) {
        return (int) (perGramPrice * 11.664);
    }
}
