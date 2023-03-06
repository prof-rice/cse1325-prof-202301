import java.awt.Color;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class WithClasses {
    public WithClasses(Simple simple, Color color) {
        this.simple = simple;
        this.color = color;
    }
    
    public WithClasses(BufferedReader br) throws IOException {
        simple = new Simple(br);
        color = new Color(Integer.parseInt(br.readLine()));
    }
    
    public void save(BufferedWriter bw) throws IOException {
        simple.save(bw);
        bw.write("" + color.getRGB() + '\n');
    }

    @Override
    public String toString() {
        return simple + "\n" + color;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(this.getClass() != o.getClass()) return false;
        WithClasses s = (WithClasses) o;
        return simple.equals(s.simple)
            && color.equals(s.color);
    }

    private Simple simple;  // A class we wrote
    private Color color;    // A class we did NOT write
    
    public static void main(String[] args) {
        String filename = "withclasses.txt";
    
        // Create and print a simple object
        Simple simple = new Simple(
            "Hello, World!", 42, 3.14, 'x', true);
        System.out.println(simple.toString());

        Color color = new Color(255, 255, 0);
        System.out.println(color.toString());
        
        // Create and print a WithClasses instance
        WithClasses wc = new WithClasses(simple, color);
        System.out.println(wc.toString());
        
        // Save the object to a simple file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            wc.save(bw);
        } catch (Exception e) {
            System.err.println("Failed to write: " + e);
            System.exit(-1);
        }
        
        // Open the simple file and recreate the object
        WithClasses wcRecreated = null;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            wcRecreated = new WithClasses(br);
        } catch (Exception e) {
            System.err.println("Failed to read: " + e);
            System.exit(-2);
        }
        System.out.println(wcRecreated.toString());
        
        if(wc.equals(wcRecreated)) System.out.println("They match!");
    }
}
