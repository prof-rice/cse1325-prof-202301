import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class EasyButtons extends JFrame {
    private enum Grades {A, B, C, D, F, I};
    public EasyButtons() {
        // Allow the window (JFrame) to initialize itself
        super();  
        
        // Configure the window to exit if the close button is clicked
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        // Create a button that, when clicked, calls onButtonClick()
        JButton button = new JButton("Click me!");
        button.setBounds(20,20,120, 30);  // position & size
		button.addActionListener(
		    event -> onButtonClick());    // Call onButtonClick when clicked
        add(button);                      // Add the button to the JFrame
		
		// Size and run the main window
        setSize(160,110); // Set the JFrame size
        setVisible(true); // Make the JFrame visible (run the window!)
    }
    
    // This method (an observer, or action listener in Java terms)
    //   will run whenever the button is clicked
    public void onButtonClick() {
        // Create an empty combobox (also called a drop-down list)
        String[] options = {"One Fish", "Two Fish", "Red Fish", "Blue Fish"};
        
        
        int button = JOptionPane.showOptionDialog​(
            this,
            "Pick a fish, Dr. Seuss", 
            "Fishes", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.QUESTION_MESSAGE,
            null, 
            options, 
            "Blue Fish");
            
        // The confirmation dialog usually takes a String prompt
        //   as the 2nd parameter, but it can actually accept
        //   ANY object. So we show the combobox and run the dialog.
        // QUESTION_MESSAGE as parameter 5 makes the dialog icon a ?
        // With OK_CANCEL_OPTION as parameter 4, it will return 
        //   OK_OPTION or CANCEL_OPTION. See your other options at
        //   https://docs.oracle.com/en/java/javase/16/docs/api/java.desktop/javax/swing/JOptionPane.html
        // We could use a message dialog, but that offers only
        //   an OK button. We usually prefer OK and CANCEL when
        //   accepting user data such as with a widget.
        
        // Parse the result and display in a message dialog
        String result = "Button " + button;
        if(button >=0 && button < options.length) result = options[button];
        JOptionPane.showMessageDialog(this, result);
    }

    public static void main(String[] args) {
        new EasyButtons();
    }
}
