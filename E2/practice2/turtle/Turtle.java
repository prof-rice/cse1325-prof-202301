import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JSlider;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;

public class Turtle extends JFrame {
    private int command = 0;

    public Turtle() {
        super("Program Turtle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setSize(450,225);
        JButton button;
        
        button = new JButton("ComboBox Text");
        button.addActionListener(event -> onControlTurtleClickBoxText());
        add(button);
        button = new JButton("ComboBox Spin");
        button.addActionListener(event -> onControlTurtleClickBoxSpin());
        add(button);
        button = new JButton("ComboBox Slider");
        button.addActionListener(event -> onControlTurtleClickBoxSlider());
        add(button);
        
        button = new JButton("Radio Text");
        button.addActionListener(event -> onControlTurtleClickRadioText());
        add(button);
        button = new JButton("Radio Spin");
        button.setBackground(java.awt.Color.GREEN);
        button.addActionListener(event -> onControlTurtleClickRadioSpin());
        add(button);
        button = new JButton("Radio Slider");
        button.addActionListener(event -> onControlTurtleClickRadioSlider());
        add(button);
        
        setVisible(true);
    }

    
    // ////////////////////////////////////////////////////////////////
    
    public void onControlTurtleClickBoxText() {
        final String[] commands = {"Forward", "Turn", "Pen Down", "Pen Up"};

        Object[] objects = new Object[2];
        
        JTextField param = new JTextField(5);
        objects[1] = param;

        JComboBox<String> command = new JComboBox<>(commands);
        command.setEditable(false);
        command.addActionListener(event -> 
            param.setVisible(command.getSelectedIndex() <= 1));
        objects[0] = command;
                                     
        int button = JOptionPane.showConfirmDialog( // Show the dialog
            this,
            objects,
            "Control Turtle",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        if(button == JOptionPane.OK_OPTION)
            System.out.println(command.getSelectedItem() + " " + param.getText());
    }

    public void onControlTurtleClickBoxSpin() {
        final String[] commands = {"Forward", "Turn", "Pen Down", "Pen Up"};

        Object[] objects = new Object[2];
        
        SpinnerModel model = new SpinnerNumberModel(1, 0, 359, 1);
        JSpinner param = new JSpinner(model);
        objects[1] = param;
        
        JComboBox<String> command = new JComboBox<>(commands);
        command.setEditable(false);
        command.addActionListener(event -> 
            param.setVisible(command.getSelectedIndex() <= 1));
        objects[0] = command;
                             
       int button = JOptionPane.showConfirmDialog( // Show the dialog
            this,
            objects,
            "Control Turtle",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        if(button == JOptionPane.OK_OPTION) {
            System.out.println(command.getSelectedItem() + " " 
                             + param.getValue().toString());
        }
    }

    public void onControlTurtleClickBoxSlider() {
       final String[] commands = {"Forward", "Turn", "Pen Down", "Pen Up"};

        Object[] objects = new Object[3];
        
        JLabel paramValue = new JLabel("180");
        JSlider param = new JSlider(0, 359);
        param.addChangeListener(event -> paramValue.setText(
            Integer.toString(param.getValue())));
        objects[1] = paramValue;
        objects[2] = param;
        
        JComboBox<String> command = new JComboBox<>(commands);
        command.setEditable(false);
        command.addActionListener(event -> {
            param.setVisible(command.getSelectedIndex() <= 1);
            paramValue.setVisible(command.getSelectedIndex() <= 1);
            });
        objects[0] = command;
                                     

       int button = JOptionPane.showConfirmDialog( // Show the dialog
            this,
            objects,
            "Control Turtle",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        if(button == JOptionPane.OK_OPTION) {
            System.out.println(command.getSelectedItem() + " " 
                             + param.getValue());
        }
    }    

    public void onControlTurtleClickRadioText() {
        final String[] commands = {"Forward", "Turn", "Pen Down", "Pen Up"};

        Object[] objects = new Object[5];
        
        JTextField param = new JTextField(5);
        objects[4] = param;

        ButtonGroup radioGroup = new ButtonGroup(); 
        for(int i=0; i<4; ++i) {
            JRadioButton button = new JRadioButton(commands[i]);
            if(i<2) button.addActionListener(event -> param.setVisible(true));
            else    button.addActionListener(event -> param.setVisible(false));
            radioGroup.add(button);
            objects[i] = button;
        }
                    
        int button = JOptionPane.showConfirmDialog(
            this,
            objects,
            "Control Turtle",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        if(button == JOptionPane.OK_OPTION) 
            for(int i=0; i<3; ++i)
                if(((JRadioButton) objects[i]).isSelected())
                    System.out.println(commands[i] + " " + param.getText());
    }
    
    public void onControlTurtleClickRadioSpin() {
        final String[] commands = {"Forward", "Turn", "Pen Down", "Pen Up"};

        Object[] objects = new Object[5];
        
        SpinnerModel model = new SpinnerNumberModel(1, 0, 359, 1);
        JSpinner param = new JSpinner(model);
        objects[4] = param;
        
        ButtonGroup radioGroup = new ButtonGroup(); 
        for(int i=0; i<4; ++i) {
            JRadioButton button = new JRadioButton(commands[i]);
            if(i<2) button.addActionListener(event -> param.setVisible(true));
            else    button.addActionListener(event -> param.setVisible(false));
            radioGroup.add(button);
            objects[i] = button;
        }
                    
        int button = JOptionPane.showConfirmDialog(
            this,
            objects,
            "Control Turtle",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        if(button == JOptionPane.OK_OPTION) {
            for(int i=0; i<3; ++i)
                if(((JRadioButton) objects[i]).isSelected())
                    System.out.println(commands[i] + " " 
                                     + param.getValue().toString());
        }
    }
    
    public void onControlTurtleClickRadioSlider() {
       final String[] commands = {"Forward", "Turn", "Pen Down", "Pen Up"};

        Object[] objects = new Object[6];
        
        JLabel paramValue = new JLabel("180");
        JSlider param = new JSlider(0, 359);
        param.addChangeListener(event -> paramValue.setText(
            Integer.toString(param.getValue())));
        objects[4] = paramValue;
        objects[5] = param;
        
        ButtonGroup radioGroup = new ButtonGroup(); 
        for(int i=0; i<4; ++i) {
            JRadioButton button = new JRadioButton(commands[i]);
            if(i<2) button.addActionListener(event -> {
                paramValue.setVisible(true); param.setVisible(true);});
            else    button.addActionListener(event -> {
                paramValue.setVisible(false); param.setVisible(false);});
            radioGroup.add(button);
            objects[i] = button;
        }

        int button = JOptionPane.showConfirmDialog(
            this,
            objects,
            "Control Turtle",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        if(button == JOptionPane.OK_OPTION) {
            for(int i=0; i<3; ++i)
                if(((JRadioButton) objects[i]).isSelected())
                    System.out.println(commands[i] + " " 
                                     + param.getValue());
        }
    }
    
    public static void main(String[] args) {
        new Turtle();
    }
}
        
