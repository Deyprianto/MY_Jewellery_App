import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PriceCalculatorTest {

    @Test
    public void testCalculateTotalPrice_basic() {
        int ed1 = 100;
        int ed2 = 200;
        int ed3 = 1;
        int ed4 = 0;
        int ed5 = 0;
        int ed6 = 0;

        double expected = 300.0; 
        double result = PriceCalculator.calculateTotalPrice(ed1, ed2, ed3, ed4, ed5, ed6);
        assertEquals(expected, result, 0.001);
    }

    @Test
    public void testCalculateTotalPrice_allZeros() {
        double result = PriceCalculator.calculateTotalPrice(0, 0, 0, 0, 0, 0);
        assertEquals(0.0, result, 0.001);
    }

    @Test
    public void testCalculateTotalPrice_partialValues() {
        double result = PriceCalculator.calculateTotalPrice(300, 300, 0, 2, 0, 0);
        assertEquals(75.0, result, 0.001);
    }
}
