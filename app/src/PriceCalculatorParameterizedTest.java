package com.example.bdjewellero;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class PriceCalculatorParameterizedTest {

    private final int ed1, ed2, ed3, ed4, ed5, ed6;
    private final double expected;

    public PriceCalculatorParameterizedTest(int ed1, int ed2, int ed3, int ed4, int ed5, int ed6, double expected) {
        this.ed1 = ed1;
        this.ed2 = ed2;
        this.ed3 = ed3;
        this.ed4 = ed4;
        this.ed5 = ed5;
        this.ed6 = ed6;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{index}: calculateTotalPrice({0},{1},{2},{3},{4},{5}) = {6}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {10, 10, 1, 0, 0, 0, 20.0},         
                {0, 0, 0, 0, 0, 0, 0.0},            
                {48, 48, 1, 2, 3, 4, 111.4},        
                {96, 0, 2, 0, 0, 0, 192.0},         
                {240, 0, 0, 2, 0, 0, 30.0},         
                {480, 480, 0, 0, 9, 6, 96.0},       
                // Additional test cases
                {100, 200, 2, 1, 1, 1, 301.25},     
                {50, 100, 1, 1, 0, 0, 150.0},       
                {0, 0, 0, 0, 10, 0, 5.0},           
                {0, 0, 0, 0, 0, 10, 6.0},           
                {960, 0, 0, 0, 0, 0, 240.0},        
                {480, 480, 1, 1, 5, 5, 104.5},     
        });
    }

    @Test
    public void testCalculateTotalPrice_parametrized() {
        double result = PriceCalculator.calculateTotalPrice(ed1, ed2, ed3, ed4, ed5, ed6);
        assertEquals(expected, result, 0.001);
    }
}
