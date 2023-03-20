import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class MainWin extends JFrame {

    public static final String TITLE = "CSE1325 Paint";
    public static final String VERSION = "0.4.0";

    public MainWin(String title) {
        super(title);

        setSize(1024, 768);
        
        // /////// ////////////////////////////////////////////////////////////////
        // M E N U
        // Add a menu bar to the PAGE_START area of the Border Layout

        JMenuBar menubar = new JMenuBar();
        
        // File menu
        
        JMenu     file       = new JMenu("File");
        JMenuItem quit       = new JMenuItem("Quit");        
        quit.addActionListener(event -> onQuitClick());
        // Make clicking the 'x' the same as File > Quit
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onQuitClick(); // Call this when 'x' is clicked
            }
        });

        file.add(quit);
        
        menubar.add(file);
        
        // Pen menu
        
        JMenu     pen         = new JMenu("Pen");
        JMenuItem color       = new JMenuItem("Color");        
        color.addActionListener(event -> onColorClick());
        pen.add(color);
        
        menubar.add(pen);
        
        setJMenuBar(menubar);
        
        // /////////////////////////// ////////////////////////////////////////////
        // C A N V A S
        // Provide a JPanel to act as the Canvas
        canvas = new Canvas();
        add(canvas, BorderLayout.CENTER);
        
        // Make everything in the JFrame visible
        setVisible(true);
    }
    
    // Listeners
    protected void onSaveClick() {
        canvas.save();
        System.err.println("Data saved");
    }
    
    protected void onQuitClick() {   // Exit the program
        if(okToExit()) dispose(); //System.exit(0);
    }
    
    protected void onColorClick() {   // Exit the program
        canvas.color(
            JColorChooser.showDialog(this, "Choose a color", canvas.color())
        );
    }
    
    protected boolean okToExit() {  // Returns true if OK to exit
        if (!canvas.isDirty()) return true;  // OK to exit if data already saved
        int choice = JOptionPane.showOptionDialog(this,
            "Save data before exiting?",
            "Unsaved Data",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.WARNING_MESSAGE,
            null,
            null,
            null); 
        if (choice == JOptionPane.YES_OPTION) {
            onSaveClick();
            return !canvas.isDirty(); // OK to exit only is saved successfully
        } 
        if (choice == JOptionPane.NO_OPTION) {
            return true;     // OK to discard data and exit
        } 
        return false;  // Exit is not approved
    }       
   
    private Canvas canvas;
}
