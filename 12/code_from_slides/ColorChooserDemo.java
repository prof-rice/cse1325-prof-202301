import javax.swing.JFrame;
import javax.swing.JButton;

public class ColorChooserDemo extends JFrame {
    public ColorChooserDemo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on X click
        
        JButton button = new JButton("Show Color Chooser");
        button.setBounds(20,20,180,30);  // position & size
		button.addActionListener(
		    event -> {
                ColorChooser dialog = new ColorChooser(this, "Color Chooser");
                dialog.setVisible(true);
		    }
		);
        add(button);                      // Add the button to the JFrame
		
        setSize(220,110); // Set the JFrame size
        setLayout(null);  // setBounds avoids layout manager need
        setVisible(true); // Make the JFrame visible
    }

    public static void main(String[] args) {
       new ColorChooserDemo();
    }
}
