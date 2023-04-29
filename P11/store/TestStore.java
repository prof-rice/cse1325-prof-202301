package store;

import java.util.Arrays; // A class filled with useful static methods related to classic arrays

public class TestStore {
    public static void main(String[] args) {
        String failFormat = "FAIL %s\n\n#### Expected\n\n%s\n\n#### Actual\n\n%s\n\n";
    
        int result = 0;  // non-zero is a failure
        int vector = 1;  // single bit representing the test
    
        Store store = new Store("Tester Joes");
        
        // Test Customer
        Object[] customers = new Object[]{
            new Customer("Prof Rice", "george.rice@uta.edu")
        };
        store.add(new Customer("Prof Rice", "george.rice@uta.edu"));
        if(!deepEqualsCustomer(customers, store.customers())) {
            System.err.printf(failFormat, "Customer",
                Arrays.toString(customers),
                Arrays.toString(store.customers())
            );
            result |= vector;
        }
        vector <<= 1;

        // Test Option
        Object[] options = new Object[]{
            new Option("Mainboard", 20000)
        };
        store.add(new Option("Mainboard", 20000));
        if(!deepEqualsOption(options, store.options())) {
            System.err.printf(failFormat, "Option",
                Arrays.toString(options),
                Arrays.toString(store.options())
            );
            result |= vector;
        }
        vector <<= 1;

        // Test Computer
        Object[] computers = new Object[]{
            new Computer("SuperCalc", "1Z200XL")
        };
        Computer c = (Computer) computers[0];
        c.addOption((Option) options[0]);

        c = new Computer("SuperCalc", "1Z200XL");
        c.addOption((Option) options[0]);       
        store.add(c);
        if(!deepEqualsComputer(computers, store.computers())) {
            System.err.printf(failFormat, "Computer",
                Arrays.toString(computers),
                Arrays.toString(store.computers())
            );
            result |= vector;
        }
        vector <<= 1;
        
        // Test Order
        Object[] orders = new Object[]{
            new Order((Customer) customers[0])
        };
        Order o1 = (Order) orders[0];
        o1.addComputer((Computer) computers[0]);
        
        Order o2 = new Order((Customer) customers[0]);
        o2.addComputer((Computer) computers[0]);                       
        store.add(o2);
                
        if(!deepEqualsOrder(orders, store.orders())) {
            System.err.printf(failFormat, "Order",
                Arrays.toString(orders),
                Arrays.toString(store.orders())
            );
            result |= vector;
        }
        vector <<= 1;

        // Print result
        if (result != 0) {
            System.err.println("FAIL: Error code " + result);
            System.exit(result);
        }
    }
    
    // These methods do a "deep compare" of two Object arrays
    //   based on the type of the objects stored in the arrays

    private static boolean deepEqualsCustomer(Object[] a, Object[] b) {
        try {
            if(a.length != b.length) return false;
            for(int i=0; i < a.length; ++i) {
                if(!((Customer) a[i]).equals((Customer) b[i])) return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    private static boolean deepEqualsOption(Object[] a, Object[] b) {
        try {
            if(a.length != b.length) return false;
            for(int i=0; i < a.length; ++i) {
                if(!((Option) a[i]).equals((Option) b[i])) return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean deepEqualsComputer(Object[] a, Object[] b) {
        try {
            if(a.length != b.length) return false;
            for(int i=0; i < a.length; ++i) {
                if(!((Computer) a[i]).equals((Computer) b[i])) return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean deepEqualsOrder(Object[] a, Object[] b) {
        try {
            if(a.length != b.length) return false;
            for(int i=0; i < a.length; ++i) {
                if(!((Order) a[i]).equals((Order) b[i])) return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
