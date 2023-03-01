import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;


class AnimalJOptionPane extends JFrame {
    private final static int width = 450;  // Default main window size
    private final static int height = 110;
    
    private JComboBox types;   // Types of animals
    private JTextField names;  // Name of new animal
    private JComboBox genders; // Gender of new animal
    private JSpinner ages;     // Age of new animal
    
    private ImageIcon petIcon = new ImageIcon("dog_64x64.png");
       
    public AnimalJOptionPane() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        JButton button = new JButton("Enter Animal Information");
        button.setBounds(20,20,120, 30);  // position & size
        button.addActionListener(
            event -> onButtonClick());
        add(button);                  
		
        setSize(width,height);
        setVisible(true); 
    }

    public void onButtonClick() {
        // Type of animal
        JLabel type = new JLabel("Type");

        String[] options = {"Cat", "Dog", "Rabbit"};
        types = new JComboBox<String>(options);
        types.setEditable(true); // Allow custom types to be entered as well

        // Name of animal
        JLabel name = new JLabel("<HTML><br/>Name</HTML>");
        names = new JTextField(20); 
               
        // Gender of animal
        JLabel gender = new JLabel("<HTML><br/>Gender</HTML>");
        
        String[] gender_text = {"Male", "Female"};
        genders = new JComboBox<String>(gender_text);

        // Age of animal
        JLabel age = new JLabel("<HTML><br/>Age</HTML>");
        
        SpinnerModel range = new SpinnerNumberModel(0, 0, 100, 1);
        ages = new JSpinner(range);

        // Display the dialog
        Object[] objects = {  // Array of widgets to display
            type,   types, 
            name,   names, 
            gender, genders, 
            age,    ages};
        int button = JOptionPane.showConfirmDialog( // Show the dialog
            this,
            objects,
            "New Pet",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            petIcon);
        if(button == JOptionPane.OK_OPTION)  // If OK clicked, show data
            JOptionPane.showMessageDialog(
                this,
                names.getText() + " ("
                    + genders.getSelectedItem() + " " 
                    + types.getSelectedItem() 
                    + " age " + ages.getValue() + ")");
    }
    public static void main(String[] args) {
        new AnimalJOptionPane();
    }
}

