public class Taxed implements Product {
    public Taxed(String name, double cost) {
        this.name = name;
        this.cost = cost;
        if(cost < 0) throw new RuntimeException("Negative cost for " + name);
    }
    public static void setTaxRate(double taxRate) {
        salesTaxRate = taxRate;
    }
    @Override
    public double price() {
        return cost * (1 + salesTaxRate);
    }
    @Override
    public String toString() {
        return Product.toString(name, cost, price());
    }
    protected String name;
    protected double cost;    
    private static double salesTaxRate = 0.0;
}
