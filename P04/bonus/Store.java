import java.util.ArrayList;
import java.util.Scanner;

public class Store {
    private static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<Product> shoppingCart = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        Taxed.setTaxRate(0.0825);
        products.add(new Taxfree("Milk",      2.85));
        products.add(new Taxfree("Bread",     1.99));
        products.add(new Taxfree("Cheese",    0.85));
        products.add(new Taxfree("Eggs",      6.95));
        products.add(new Taxed(  "Ice Cream", 4.95));
        products.add(new Taxed(  "Poptarts",  3.49));
        products.add(new Taxed(  "Oreos",     5.99));
        
        
        while(true) {
            System.out.println("""
                               ========================
                                 Welcome to the Store
                               ========================
                               """);
            int index = 0;
            for(Product p : products) System.out.println(index++ + ") " + p);
            if(!shoppingCart.isEmpty()) {
                System.out.println("\nCurrent Order\n-------------\n");
                double price = 0.00;
                for(Product p : shoppingCart) {
                    System.out.println(p);
                    price += p.price();
                }
                System.out.printf("\nTotal price: $%.2f\n", price);
            }

            int productIndex;
            try {
                System.out.println("\nBuy which product (q to exit)? ");
                String cmd = in.nextLine();
                if(cmd.toLowerCase().charAt(0) == 'q') break;
                productIndex = Integer.parseInt(cmd);
                shoppingCart.add(products.get(productIndex));

            } catch(Exception e) {
                System.err.println("\n###\n### Error: " + e.getMessage() + "\n###\n");
            }
        }        
    }
}
