import javax.swing.JFrame;
import javax.swing.JButton;

public class Animals extends JFrame {
    public Animals() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on X click
        
        JButton button = new JButton("Click for Example Dialog");
        button.setBounds(20,20,240, 30);                // position & size
        button.addActionListener(event -> newAnimal()); // Set button observer
        add(button);                                    // Add button to window
		
        setSize(280,110); // Set the JFrame size
        setLayout(null);  // setBounds avoids layout manager need
        setVisible(true); // Make the JFrame visible
    }
    
    public void newAnimal() {
        AnimalDialog ad = new AnimalDialog(this); // Run the dialog
        String animal = ad.toString();            // Extract the data in String format
        if(animal.length() > 0) 
            System.out.println(animal);           // Print the entered data (if any)
    }

    public static void main(String[] args) {
       new Animals(); // Run the application
    }
}

