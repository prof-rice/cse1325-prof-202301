import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class MessageDialogWithIcon extends JFrame {
    // Current icon index (DON'T RELY on this but is -1 to 3)
    // See https://docs.oracle.com/en/java/javase/16/docs/api/constant-values.html#javax.swing.JOptionPane.PLAIN_MESSAGE 
    private int icon = -1; 

    // A small 36x32 pixel UTA Logo
    private ImageIcon utaLogo = new ImageIcon("UTA_logo.png");

    // Contructor
    public MessageDialogWithIcon() {
        // Close when the X in the corner of the window is clicked
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        // Create a button that shows "Click Me!" on it
        JButton button = new JButton("Click Me Several Times!");
        
        // offset 20,20 & size of 180x30 pixels
        button.setBounds(20,20,220,30);  
        
        // When clicked, create and display a message dialog
        //   with different icons (including a custom icon)
        
        // static void showMessageDialog​(
        //       Component parentComponent,  // Any Component 
        //       Object message,             // The message is ANY Object!
        //       int messageType,            // See constants below
        //       Icon icon)                  // 
		button.addActionListener(
		    event -> {
		        if(icon <= 3) {
                    JOptionPane.showMessageDialog(
		                this,               // This is the main window object (if none, use null)
		                "Icon = " + icon,   // This is the message to display
		                "Pre-Define Icon",  // This is the dialog title
		                icon);              // This is the index of the pre-defined icon to show
		            ++icon;
		        } else {
                    JOptionPane.showMessageDialog(
		                this,               // This is the main window object (if none, use null)
		                "UTA Logo",         // This is the message to display
		                "Custom Icon",      // This is the dialog title
		                1,                  // Any index (ignored)
		                utaLogo);           // An ImageIcon
                    icon = -1;              // Loop around and start over!
                }	        
		    }
		);
		
		// Add the button to the JFrame
        add(button);                      
		
		// Make the main window 220x110 pixels, with no layout manager
        setSize(260,110);
        setLayout(null);
        
        // Make the JFrame visible and interactive
        setVisible(true); 
    }
    public static void main(String[] args) {
        new MessageDialogWithIcon();
    }
}
