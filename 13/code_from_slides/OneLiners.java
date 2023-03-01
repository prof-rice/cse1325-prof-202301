import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class OneLiners extends JFrame {
    private JButton message, confirm, input, option;
    private JLabel response;
    
    public OneLiners() {
        super("Four One-Liner Dialogs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;

        
        // ///////////////////////////////////////////////
        // Message Dialog
        message = new JButton("Message Dialog");
        message.addActionListener(
		    event -> JOptionPane.showMessageDialog(this,
		        "Hi! I'm a Message Dialog!"));
        add(message, constraints);
        
        // ///////////////////////////////////////////////
        // Confirm Dialog
        confirm = new JButton("Confirm Dialog");
        confirm.addActionListener(
		    event -> {
		        int n = JOptionPane.showConfirmDialog(this, 
		            "Do you like Confirm Dialogs?", "Question", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) response.setText("Great!");
                else if (n == JOptionPane.NO_OPTION) response.setText("Well, write your own!");
                else response.setText("You skipped the question!");
            });
        constraints.gridy++;
        add(confirm, constraints);
        
        // ///////////////////////////////////////////////
        // Input Dialog
        input = new JButton("Input Dialog");
        input.addActionListener(
		    event -> {
		        String name = JOptionPane.showInputDialog(this,
		            "Input Dialog asks, What's your name?");
		        if(name != null) response.setText("Hi, " + name + "!");
		        else response.setText("Then I shall call you Squishy. Hi, Squishy!");
		    });
		constraints.gridy++;
        add(input, constraints);
        
        // ///////////////////////////////////////////////
        // Option Dialog
        option = new JButton("Option Dialog");
        option.addActionListener(
		    event -> {
		        Object[] options = {"CSE1310", "CSE1320", "CSE1325", "All of these!"};
                int n = JOptionPane.showOptionDialog(this,
                    "Which intro to programming class have you enjoyed most?",
                    "Thoughtful Question",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[3]);
                if(n == 3) response.setText("Yes, they're all fun!");
                else if (n >= 0) response.setText("Yes, " + options[n] + " is fun!");
                else response.setText("Hope things improve!");
            });
        constraints.gridy++;
        add(option, constraints);
        
        response = new JLabel();
        constraints.gridy++;
        add(response, constraints);
        
        setSize(320,160);
        setVisible(true); 
    }

    public static void main(String[] args) {
       new OneLiners();
    }
}
