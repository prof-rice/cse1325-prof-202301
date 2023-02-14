public class Taxed extends Product {
    public Taxed(String name, double cost) {
        super(name, cost);
    }
    public static void setTaxRate(double taxRate) {
        salesTaxRate = taxRate;
    }
    @Override
    public double price() {
        return cost * (1 + salesTaxRate);
    }
    private static double salesTaxRate = 0.0;
}
