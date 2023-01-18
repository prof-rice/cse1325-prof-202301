// This program demonstrates how to build and run a Java package.
// It also verifies that you can run GUI programs, which is required
//    for section 2 of this class.

// Compile using 'javac hello/HelloJava.java'
// Execute using 'java hello.HelloJava'

// This declares the source file to be part of a Java package named hello
package hello;

// The javax.swing package is the Swing GUI library
import javax.swing.JFrame;   // A window on the desktop
import javax.swing.JButton;  // A button in the window

// Preview of classes AND inheritance! More soon.
public class HelloJava extends JFrame {

    // This is a special function called a "constructor"
    // It runs when 'new HelloJava()' executes - more next week!
    public HelloJava() {
    
        // Close the window if X is clicked in title bar
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create and size a button
        JButton button = new JButton("Hello, Java!");
        button.setBounds(20,20,120, 30);
		
		// Add the button to the window and print "Ouch!" when it's clicked
        add(button);
		button.addActionListener(event -> System.out.println("Ouch!"));
		
        setSize(160,110); // Set the window size
        setLayout(null);  // Only one widget, so we don't need to manage layout
        setVisible(true); // Make window interactive
    }

    // Execution begins here as always in Java!
    public static void main(String[] args) {
        new HelloJava();  // This calls HelloJava.HelloJava()
    }
}
