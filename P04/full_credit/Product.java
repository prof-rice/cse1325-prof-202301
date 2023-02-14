public abstract class Product {
    public Product(String name, double cost) {
        this.name = name;
        this.cost = cost;
        if(cost < 0) throw new RuntimeException("Negative cost for " + name);
    }
    public abstract double price();
    protected String name;
    protected double cost;
    @Override
    public String toString() {
        return String.format("%-40s", name + " ($" + String.format("%.2f", cost) + ")") 
                + String.format("$%6.2f", price());  
    }
}
