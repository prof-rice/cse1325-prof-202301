interface Product {
    public double price();
    public static String toString(String name, double cost, double price) {
        return String.format("%-40s", name + " ($" + String.format("%.2f", cost) + ")") 
                + String.format("$%6.2f", price);  
    }
}
