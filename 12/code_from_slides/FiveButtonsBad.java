import javax.swing.JFrame;
import javax.swing.JButton;

public class FiveButtonsBad extends JFrame {
    public FiveButtonsBad() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on X click
        
        for(int i=0; i<5; ++i) {
            JButton button = new JButton("Hello, Button " + i + "!");
            button.setBounds(20,20,180, 30);  // position & size
		    button.addActionListener(
		        event -> System.out.println(event.getActionCommand()));
            add(button);                      // Add the button to the JFrame
        }
		
        setSize(480,160); // Set the JFrame size
        setLayout(null);
        setVisible(true); // Make the JFrame visible
    }

    public static void main(String[] args) {
       new FiveButtonsBad();
    }
}
