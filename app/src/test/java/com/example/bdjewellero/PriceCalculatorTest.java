package com.example.bdjewellero;
import static org.junit.Assert.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class PriceCalculatorTest {

    @Test
    public void testCalculateTotalPrice_basic() {
        double result = PriceCalculator.calculateTotalPrice(10, 10, 1, 0, 0, 0);
        assertEquals(20.0, result, 0.001);
    }

    @Test
    public void testCalculateTotalPrice_zero() {
        double result = PriceCalculator.calculateTotalPrice(0, 0, 0, 0, 0, 0);
        assertEquals(0.0, result, 0.001);
    }

    @Test
    public void testCalculateTotalPrice_mixed() {
        double result = PriceCalculator.calculateTotalPrice(48, 48, 1, 2, 3, 4);
        assertEquals(111.4, result, 0.001);
    }
}
