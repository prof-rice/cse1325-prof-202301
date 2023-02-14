public class Taxfree implements Product {
    public Taxfree(String name, double cost) {
        this.name = name;
        this.cost = cost;
        if(cost < 0) throw new RuntimeException("Negative cost for " + name);
    }
    public double price() {
        return cost;
    }
    @Override
    public String toString() {
        return Product.toString(name, cost, cost);
    }
    protected String name;
    protected double cost;
}
