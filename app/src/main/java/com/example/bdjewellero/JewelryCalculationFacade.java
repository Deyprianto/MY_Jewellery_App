package com.example.bdjewellero;

public class JewelryCalculationFacade {
    private final PriceCalculator priceCalculator;
    private final GoldPriceManager goldPriceManager;
    
    public JewelryCalculationFacade() {
        this.priceCalculator = new PriceCalculator();
        this.goldPriceManager = GoldPriceManager.getInstance();
    }
    
    public double calculateTotalPriceWithTax(int[] inputs) {
        double basePrice = priceCalculator.calculateTotalPrice(
            inputs[0], inputs[1], inputs[2], inputs[3], inputs[4], inputs[5]
        );
        
        // Add VAT and other charges
        double vat = basePrice * 0.15; // 15% VAT
        double serviceCharge = basePrice * 0.02; // 2% service charge
        
        return basePrice + vat + serviceCharge;
    }
    
    public String getPriceBreakdown(int[] inputs) {
        double basePrice = priceCalculator.calculateTotalPrice(
            inputs[0], inputs[1], inputs[2], inputs[3], inputs[4], inputs[5]
        );
        double vat = basePrice * 0.15;
        double serviceCharge = basePrice * 0.02;
        double total = basePrice + vat + serviceCharge;
        
        return String.format(
            "Base Price: ৳%.2f\nVAT (15%%): ৳%.2f\nService Charge: ৳%.2f\nTotal: ৳%.2f",
            basePrice, vat, serviceCharge, total
        );
    }
}
