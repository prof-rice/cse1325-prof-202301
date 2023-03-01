import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class MessageDialog extends JFrame {
    public MessageDialog() {
        // Initialize the JFrame from which this class inherited
        super();  
        
        // Close when the X in the corner of the window is clicked
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        // Create a button that shows "Click Me!" on it
        JButton button = new JButton("Click Me!");
        
        // offset 20,20 & size of 180x30 pixels
        button.setBounds(20,20,180,30);  
        
        // When clicked, create nd display a message dialog that says "Ouch!"
        // This relies on a "lambda" - see Lecture 12!
        
        // static void showMessageDialog​(
        //       Component parentComponent,  // Any Component 
        //              (Component <|- Container <|- Window <|- Frame <|- JFrame)
        //       Object message)             // The message is ANY Object!
		button.addActionListener(
		    event -> JOptionPane.showMessageDialog(
		        this,    // This is the main window object (if none, use null)
		        "Ouch!" // This is the message to display
		    )
		);
		
		// Add the button to the JFrame
        add(button);                      
		
		// Make the main window 220x110 pixels, with no layout manager
        setSize(220,110);
        setLayout(null);
        
        // Make the JFrame visible and interactive
        setVisible(true); 
    }
    public static void main(String[] args) {
        new MessageDialog();
    }
}
