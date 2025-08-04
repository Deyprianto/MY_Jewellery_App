package com.example.bdjewellero;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GoldPriceParserTest {

    @Test
    public void testParsePriceToBDT() {
        String rawPrice = "7,150 BDT/GM";
        int parsed = GoldPriceParser.parsePriceToBDT(rawPrice);
        assertEquals(7150, parsed);
    }


  
    @Test
    public void testConvertToVori() {
        int gramPrice = 7000;
        int voriPrice = GoldPriceParser.convertToVori(gramPrice);
        assertEquals(81648, voriPrice);  
    }


  
    @Test
    public void testParseAndConvert() {
        String rawPrice = "6,500 BDT/GM";
        int perGram = GoldPriceParser.parsePriceToBDT(rawPrice);
        int voriPrice = GoldPriceParser.convertToVori(perGram);
        assertEquals(75816, voriPrice); 
    }
}
