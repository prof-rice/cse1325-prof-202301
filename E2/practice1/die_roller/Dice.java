import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import javax.swing.JToolBar;
import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;

import javax.swing.JOptionPane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.awt.Font;
import java.awt.BorderLayout;

import java.util.ArrayList;


public class Dice extends JFrame {

    private ArrayList<Integer> rolls = new ArrayList<>();
    private int size = 6;
    
    private JLabel data;
    private JLabel msg;

    public static void main(String[] args) {
        Dice myApp = new Dice("Dice Roller");
        myApp.setVisible(true);
    }
    
    public Dice(String title) {
        super(title);
        setSize(480,160);
        
        // 1a Configure program to exit if close (x) is selected
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // M E N U B A R

        // 1b Create menubar with File > Set Size menu item
        //    that calls onSetSizeClick() when selected
        JMenuBar menubar = new JMenuBar();
        
        JMenu     file       = new JMenu("File");
        JMenuItem anew       = new JMenuItem("New");
        JMenuItem open       = new JMenuItem("Open");
        JMenuItem save       = new JMenuItem("Save");
        JMenuItem setsize    = new JMenuItem("Set Size");
        JMenuItem quit       = new JMenuItem("Quit");
        
        anew.addActionListener(event -> onNewClick());
        open.addActionListener(event -> onOpenClick());
        save.addActionListener(event -> onSaveClick());
        setsize.addActionListener(event -> onSetSizeClick());
        quit.addActionListener(event -> onQuitClick());

        
        file.add(anew);
        file.add(open);
        file.add(save);
        file.add(setsize);
        file.add(quit);
        
        menubar.add(file);
        setJMenuBar(menubar);
        
        // T O O L B A R
        
        // 1c Add a toolbar with button with icon "roll.png" 
        //   and tool tip "Roll die"that calls onRollDieClick()
        //   when clicked
        
        JToolBar toolbar = new JToolBar("Dice Roller Controls");

        JButton rollIcon  = new JButton(new ImageIcon("roll.png"));
          rollIcon.setActionCommand("Roll die");
          rollIcon.setToolTipText("Roll die");
          rollIcon.setBorder(null);
          toolbar.add(rollIcon);
          rollIcon.addActionListener(event -> onRollDieClick());
        add(toolbar, BorderLayout.PAGE_START);
        
        // D A T A   D I S P L A Y
        
        // 1.d Provide a label in the center of the window to show the results
        //     (no specific font is required)
        data = new JLabel();
        data.setFont(new Font("SansSerif", Font.BOLD, 24));
        add(data, BorderLayout.CENTER);

        // S T A T U S   B A R 
        
        // Provide a status bar for license compliance
        msg = new JLabel("Dice Icon by Mister Pixel from the Noun Project");
        add(msg, BorderLayout.PAGE_END);

        // 1.e Make everything in the JFrame visible and run the Swing application
        setVisible(true);
    }
  
    public void onNewClick() {
        rolls.clear();
        updateDisplay();
    }
    
    // 3 Implement onOpenClick. Read file "untitled.dice", converting each line
    //   of text to an integer to add to ArrayList rolls. Use try-with-resources
    //   to display a message dialog "Unable to read untitled.dice" on an IOException.
    public void onOpenClick() {
        rolls.clear(); // Not required by the exam
        try (BufferedReader br = new BufferedReader(new FileReader("untitled.dice"))) {
            String s;
            while((s = br.readLine()) != null) {
                rolls.add(Integer.parseInt(s));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to read untitled.dice: " + e);
        }
        updateDisplay();
    }
     
    public void onSaveClick() {
        try(BufferedWriter br = new BufferedWriter(new FileWriter("untitled.dice"))) {
            for(int r : rolls) br.write("" + r + '\n');
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to write untitled.dice: " + e);
        }
    }
    
    // 2 Create a dialog for the user to select 4, 6, 8, 12, or 20 sided die
    //    Set the size attribute to the number of sides selected
    // Could be either buttons, input, or combo box, at the student's option.
    /*
    // Using buttons
    public void onSetSizeClick() {
        String[] sizes = {"4", "6", "8", "12", "20"};
        int[] values = {4, 6, 8, 12, 20};
        int choice = JOptionPane.showOptionDialog(this, "Choose a die size", "Change Die",
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, sizes, "6");
        if(choice >= 0 && choice < sizes.length) size = values[choice];
    }
    
    
    // Using input
    public void onSetSizeClick() {
        String s = JOptionPane.showInputDialog(this, "Choose a die size", "Change Die",
            JOptionPane.DEFAULT_OPTION);
        try {
            if (s != null) size = Integer.parseInt(s);
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid size");
        }
    }
    */
     
    // Using a combo box
    public void onSetSizeClick() {
        Integer[] options = {4, 6, 8, 12, 20};
        JComboBox<Integer> values = new JComboBox<>(options);
        values.setSelectedItem(size);
        JOptionPane.showMessageDialog(this, values, "Change Die",             JOptionPane.DEFAULT_OPTION);
        size = (Integer) values.getSelectedItem();
    }
    

    public void onQuitClick() {
        dispose();
    }
    
    public void onRollDieClick() {
        int roll = (int) (Math.random() * size + 1);
        rolls.add(roll);
        updateDisplay();
    }
    private void updateDisplay() {
        String s = "";
        String div = "";
        int sum = 0;
        for(int r : rolls) {
            s += div + r; 
            div = " + ";
            sum += r;
        }
        if(rolls.size() > 1) s += " = " + sum;
        data.setText(s);
    }
    
}
