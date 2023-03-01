import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

class AnimalDialog extends JDialog {
    private final static int width = 300;  // Default dialog size
    private final static int height = 110;
    
    private JComboBox types;   // Types of animals
    private JTextField names;  // Name of new animal
    private JComboBox genders; // Gender of new animal
    private JSpinner ages;     // Age of new animal
    
    private boolean canceled;  // True if the Cancel button is clicked
    
    AnimalDialog(Frame aFrame) {  // The dialog runs when executed
        super(aFrame, "New Pet", true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // Close on X click
        canceled = true;                                // Treat X click as Cancel
        
        setSize(width,height);                          // Set the JDialog default size

        // Configure GridBagLayout constraints for the right (data controls) column
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints(); // Specify cell for widgets
        constraints.gridwidth = 1;   // 1 cell wide
        constraints.gridheight = 1;  // 1 cell high
        constraints.weightx = 1;     // Resize data controls in width as window resizes
        constraints.weighty = 0;     // Do NOT resize in height as window resizes
        constraints.insets = new Insets(2, 5, 2, 5);  // Pixel spacing above, left, below, and right
        constraints.fill= GridBagConstraints.BOTH;    // Resize widget to fill the cell - options:
            // NONE (the default), HORIZONTAL (width only), VERTICAL (height only), or BOTH
        constraints.anchor = GridBagConstraints.LINE_START;  // Position widget center left in cell - options:
            // FIRST_LINE_START   PAGE_START   FIRST_LINE_END
            // LINE_START           CENTER	         LINE_END
            // LAST_LINE_START     PAGE_END     LAST_LINE_END
        
        // Configure GridBagLayout constraints for the left (labels) column
        GridBagConstraints constraintsLabel = (GridBagConstraints) constraints.clone(); // Duplicate constraints
        constraintsLabel.weightx = 0;  // Do NOT resize the label column in width as window resizes
        
        // ////////////////////////////////////
        // Type of animal
        JLabel type = new JLabel("Type");
        constraintsLabel.gridx = 0;
        constraintsLabel.gridy = 0;
        add(type, constraintsLabel);                         // Add the button to the JFrame

        String[] options = {"Cat", "Dog", "Rabbit"};
        types = new JComboBox<String>(options);
        types.setEditable(true); // Allow custom types to be entered as well
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weighty = 0;
        add(types, constraints);

        // ///////////////////////////////////
        // Name of animal
        JLabel name = new JLabel("Name");
        constraintsLabel.gridx = 0;
        constraintsLabel.gridy = 1;
        add(name, constraintsLabel);                         // Add the button to the JFrame
        
        names = new JTextField(20); // parameter is number of chars visible at once (not max length!)
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weighty = 1;    // Resize in height as the window resizes 
        add(names, constraints);

                
        // ////////////////////////////////////
        // Gender of animal
        JLabel gender = new JLabel("Gender");
        constraintsLabel.gridx = 0;
        constraintsLabel.gridy = 2;
        add(gender, constraintsLabel);                         // Add the button to the JFrame
        
        String[] gender_text = {"Male", "Female"};
        genders = new JComboBox<String>(gender_text);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.weighty = 0;
        add(genders, constraints);

        // ///////////////////////////////////
        // Age of animal
        JLabel age = new JLabel("Age");
        constraintsLabel.gridx = 0;
        constraintsLabel.gridy = 3;
        add(age, constraintsLabel);                         // Add the button to the JFrame
        
        SpinnerModel range = new SpinnerNumberModel(0, 0, 100, 1);
        ages = new JSpinner(range);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.weighty = 0;
        add(ages, constraints);

        // Add OK and Cancel buttons
        JPanel panel = new JPanel();  // Put them into a JPanel to lay out as a group
        
        JButton ok = new JButton("OK");
        ok.addActionListener(event -> {   // Execute this lambda when OK button is clicked
            canceled = false;   // This accepts the data below
            setVisible(false);  // Hide the dialog but keep it around to get the entered data!
        });
        panel.add(ok);
        
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(event -> {
            canceled = true;    // Ignore the data below - the user clicked Cancel
            setVisible(false);
        });
        panel.add(cancel);
        
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;   // The buttons on the panel span the entire dialog
        constraints.anchor = GridBagConstraints.CENTER;    // Center the buttons
        add(panel, constraints);
        
	    // Display the dialog
		pack();
        setVisible(true); // Make the JFrame visible and wait for it to be hidden again
        
        /* OK, Cancel, or the little X was clicked
        if(canceled) System.out.println("Dialog was canceled");
        else System.out.println("OK was clicked! The user specified "
            + names.getText() + " ("
            + genders.getSelectedItem() + " "
            + types.getSelectedItem() + " age "
            + ages.getValue() + ")");
        */
    }
    public String toString() {
        if (canceled) return "";
        return names.getText() + " ("
             + genders.getSelectedItem() + " "
             + types.getSelectedItem() + " age "
             + ages.getValue() + ")";
    }
}

