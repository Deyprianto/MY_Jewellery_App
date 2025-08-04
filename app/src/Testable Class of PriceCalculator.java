public class PriceCalculator {

    public static double calculateTotalPrice(int edValue1, int edValue2, int edValue3, int edValue4, int edValue5, int edValue6) {
        double pointPrice = ((double) edValue1 / 960) + ((double) edValue2 / 960);
        int totalPoint = (edValue3 * 960) + (edValue4 * 60) + (edValue5 * 10) + edValue6;
        return totalPoint * pointPrice;
    }
}
