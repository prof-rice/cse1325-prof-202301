public class Test {
    public static void main(String[] args) {
        int result = 0;
        int vector = 1;
    
        String expected = "";
        
        // Test 1 - Taxfree with valid data
        try {
            Taxfree tf = new Taxfree("Coffee", 3.79);
            expected = "Coffee ($3.79)                          $  3.79";
            if(!tf.toString().equals(expected)) {
                System.err.println("ERROR (Taxfree): Expected '" + expected 
                              + "'\n                  but got '" + tf + "'");
                result |= vector;
            }
        } catch(Exception e) {
            System.err.println("ERROR (Taxfree): Expected '" + expected 
                           + "\n                  but got " + e);
            result |= vector;
        }
        vector <<= 1;
        
        // Test 2 - Taxfree with invalid data
        try {
            Taxfree tfb = new Taxfree("Coffee", -3.79);
            System.err.println("ERROR (Taxfree): Expected Runtime Exception" 
                           + "\n                  but got NO exception");
            result |= vector;
        } catch (RuntimeException e) {
        } catch (Exception e) {
            System.err.println("ERROR (Taxfree): Expected Runtime Exception" 
                           + "\n                  but got " + e);
            result |= vector;
        }
        vector <<= 1;

        // Test 3 - Taxed with valid data and default (0%) tax rate
        try {
            Taxed t = new Taxed("Coffee", 3.79);
            expected = "Coffee ($3.79)                          $  3.79";
            if(!t.toString().equals(expected)) {
                System.err.println("ERROR (Taxed): Expected '" + expected 
                              + "'\n                but got '" + t + "'");
                result |= vector;
            }
        } catch(Exception e) {
            System.err.println("ERROR (Taxed): Expected '" + expected 
                           + "\n                but got " + e);
            result |= vector;
        }
        vector <<= 1;
        
        // Test 4 - Taxed with valid data and 0.0825% tax rate
        try {
            Taxed.setTaxRate(0.0825);
            Taxed tt = new Taxed("Coffee", 3.79);
            expected = "Coffee ($3.79)                          $  4.10";
            if(!tt.toString().equals(expected)) {
                System.err.println("ERROR (Taxed): Expected '" + expected 
                              + "'\n                but got '" + tt + "'");
                result |= vector;
            }
        } catch(Exception e) {
            System.err.println("ERROR (Taxed): Expected '" + expected 
                           + "\n                but got " + e);
            result |= vector;
        }
        vector <<= 1;
        
        // Test 5 - Taxed with invalid data
        try {
            Taxed tx = new Taxed("Coffee", -3.79);
            System.err.println("ERROR (Taxed): Expected Runtime Exception" 
                           + "\n                but got NO exception");
            result |= vector;
        } catch (RuntimeException e) {
        } catch (Exception e) {
            System.err.println("ERROR (Taxed): Expected Runtime Exception" 
                           + "\n                but got " + e);
            result |= vector;
        }
        vector <<= 1;        
        
        // Report results of testing
        if(result != 0) {
            System.err.println("\nFAIL with error code " + result);
            System.exit(result);
        }       
    }
}
