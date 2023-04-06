import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JProgressBar;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import java.lang.Math;

public class AllWidgets extends JFrame {
    private final static int width = 480;
    private final static int height = 110;
    
    private JButton button;
    private JTextField text;
    private JComboBox combo;
    private JToggleButton toggle;
    private JCheckBox check;
    private JRadioButton radio1, radio2;
    private JSpinner spinner;
    private JLabel number;
    private JSlider slider;
    private JProgressBar progress;
    
    public AllWidgets() {
        super("Demo of Swing Widgets");  // Initialize JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close on X click
        setSize(width,height); // Set the JFrame size
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints(); // Specify cell for widgets
        constraints.gridx = 0;  // Fill the left column
        constraints.gridy = GridBagConstraints.RELATIVE; // Add each widget in the next cell
        constraints.gridwidth = 1;   // 1 cell wide
        constraints.gridheight = 1;  // 1 cell high
        constraints.weightx = 1;     // Resize data controls in width as window resizes
        constraints.weighty = 1;     // Resize data controls in height as window resizes
        constraints.insets = new Insets(0, 5, 0, 5);  // Pixel spacing above, left, below, right
        constraints.fill= GridBagConstraints.BOTH;    // Resize widget to fill cell - options:
            // NONE (the default), HORIZONTAL (width only), VERTICAL (height only), or BOTH
        constraints.anchor = GridBagConstraints.CENTER;  // Center left in cell - options:
            // FIRST_LINE_START   PAGE_START   FIRST_LINE_END
            // LINE_START           CENTER	         LINE_END
            // LAST_LINE_START     PAGE_END     LAST_LINE_END

        // /////////////////////////////////////////////////////////
        // Button
        button = new JButton("Click this button!");
        button.addActionListener(
		    event -> JOptionPane.showMessageDialog(this, "Ouch!"));
        add(button, constraints);                      // Add the button to the JFrame
		
        // /////////////////////////////////////////////////////////
        // Text Field
        text = new JTextField(20); // parameter is number of 'm' chars visible (not max length!)
        text.addActionListener(
		    event -> JOptionPane.showMessageDialog(this, text.getText()));
        add(text, constraints);

        // /////////////////////////////////////////////////////////
		// Combo Box
		String[] options = {"JComboBox Row 1", "JComboBox Row 2", "JComboBox Row 3"};
		combo = new JComboBox<String>(options); // This is a template (coming soon!)
		combo.addActionListener(
		    event -> JOptionPane.showMessageDialog(this, (String)combo.getSelectedItem()));
		add(combo, constraints);
		
        // /////////////////////////////////////////////////////////
		// Toggle Button
		toggle = new JToggleButton("Toggle Button");
		toggle.addActionListener(
		    event -> JOptionPane.showMessageDialog(this, toggle.isSelected() ? "Down" : "Up"));
		/* Or more verbosely,
		toggle.addItemListener(
		    event -> {
		        if(event.getStateChange() == ItemEvent.SELECTED)   
		            JOptionPane.showMessageDialog(this, "SELECTED");		        
		        else if(event.getStateChange() == ItemEvent.DESELECTED)  
		            JOptionPane.showMessageDialog(this, "DESELECTED");
		        else JOptionPane.showMessageDialog(this, event.paramString()); // "Impossible"
		    });
		*/
		add(toggle, constraints);

        // /////////////////////////////////////////////////////////
        // Check Box
        check = new JCheckBox("Check Box (enables Slider)");
        check.setSelected(true); // start checked
        check.addActionListener(
            event -> {
                slider.setEnabled(check.isSelected());
                JOptionPane.showMessageDialog(this, 
                    check.isSelected() ? "Checked" : "Unchecked");
            });
        add(check, constraints);
        
        // /////////////////////////////////////////////////////////
        // Radio Buttons


        ButtonGroup radioGroup = new ButtonGroup(); // Logical grouping - only one button at a time
        JPanel radioPanel = new JPanel();           // Physical grouping - visually group the radio buttons
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.PAGE_AXIS));
        radioPanel.setBorder(BorderFactory.createTitledBorder(
            "This Border visually groups the JRadioButtons"));
        
        radio1 = new JRadioButton("Radio Button 1");
        radio1.setSelected(true);
        radio1.addActionListener(
            event -> JOptionPane.showMessageDialog(this, "Radio 1"));
        radioGroup.add(radio1);  // Add to logical grouping
        radioPanel.add(radio1);  // Add to physical grouping
        progress.setValue(30);
        
        radio2 = new JRadioButton("Radio Button 2");
        radio2.addActionListener(
            event -> {JOptionPane.showMessageDialog(this, "Radio 2");
                              progress.setValue(70);});
        radioGroup.add(radio2);
        radioPanel.add(radio2);


        add(radioPanel, constraints);

        // /////////////////////////////////////////////////////////
        // Spinner
        // JSpinner() is an int spinner starting at 0, with no min or max
        // For better control, provide a model as parameter:
        //   SpinnerNumberModel int or double parameters are initial value, min, max, and step
        //   SpinnerList Model accepts Object[] or List parameter and spins through its elements
        SpinnerModel range = new SpinnerNumberModel(0, 0, 1000, 1);
        spinner = new JSpinner(range);
        spinner.addChangeListener(
            event -> {
                int val=(int)spinner.getValue();
                // Or, more generally, int val=(int)((JSpinner)event.getSource()).getValue();
                number.setText(Integer.toString(val*val));
            });
        add(spinner, constraints);
        
        // /////////////////////////////////////////////////////////
        // Label for Spinner and Slider
        number = new JLabel("0"); // set by JSpinner and JSlider
        add(number, constraints);

        // /////////////////////////////////////////////////////////
        // Slider
        // Note the the Java slider lacks the ability to show the value above the thumb
        slider = new JSlider(0, 100);
        slider.setValue(0);
        slider.addChangeListener(
            event -> {
                int val=slider.getValue();
                number.setText(Integer.toString(val*val));
                progress.setValue((int)(Math.sqrt(val)*10.0));
            });
        add(slider, constraints);


        // /////////////////////////////////////////////////////////
        // Progress Bar
        progress = new JProgressBar(0, 100);  // in percent (in this case)
        progress.setStringPainted(true); // include a text value inside the bar
        // Since a JProgressBar is output only, we don't (but could) provide a ChangeListener
        add(progress, constraints);

		// Display the window
		pack();
        setVisible(true); // Make the JFrame visible
    }

    public static void main(String[] args) {
       new AllWidgets();
    }
}
