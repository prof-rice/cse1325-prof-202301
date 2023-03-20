import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class MainWin extends JFrame {

    public static final String TITLE = "CSE1325 Paint";
    public static final String VERSION = "0.6.0";

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
        quit.addActionListener((ActionEvent event) -> onQuitClick());
        // Make clicking the 'x' the same as File > Quit
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onQuitClick(); // Call this when 'x' is clicked
            }
        });

        // Pen menu
        
        JMenu     pen         = new JMenu("Pen");
        JMenuItem color       = new JMenuItem("Color");        
        color.addActionListener((ActionEvent event) -> onColorClick());
        
        JMenuItem width       = new JMenuItem("Width");        
        width.addActionListener((ActionEvent event) -> onWidthClick());
        
        // Help menu

        JMenu     help       = new JMenu("Help");
        JMenuItem about      = new JMenuItem("About");        
        about.addActionListener((ActionEvent event) -> onAboutClick());
        
        file.add(quit);
        pen.add(color);
        pen.add(width);
        help.add(about);
        
        menubar.add(file);
        menubar.add(pen);
        menubar.add(help);        
        
        setJMenuBar(menubar);
        
        // ///////////// //////////////////////////////////////////////////////////
        // T O O L B A R
        // Add a toolbar to the PAGE_START region below the menu
        JToolBar toolbar = new JToolBar("Paint Controls");

        // Create the Color button
        JButton colorButton  = 
            new JButton(new ImageIcon("resources/select_pen_color.png"));
        colorButton.setActionCommand("Select the pen color");
        colorButton.setToolTipText("Select the pen color");
        toolbar.add(colorButton);
        colorButton.addActionListener((ActionEvent event) -> onColorClick());

        // Create the Width button
        JButton widthButton  = 
            new JButton(new ImageIcon("resources/select_pen_width.png"));
        widthButton.setActionCommand("Select the pen width");
        widthButton.setToolTipText("Select the pen width");
        toolbar.add(widthButton);
        widthButton.addActionListener((ActionEvent event) -> onWidthClick());

        getContentPane().add(toolbar, BorderLayout.PAGE_START);
      
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
    
    protected void onColorClick() {   // Select a new pen color
        canvas.color(
            JColorChooser.showDialog(this, "Choose a color", canvas.color())
        );
    }
    
    protected void onWidthClick() {   // Select a new pen width
        // Configure a slider to select the pen width
        JSlider slider = new JSlider(1, 25, (int) canvas.width());
        slider.setMajorTickSpacing(3);
        slider.setMinorTickSpacing(1);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setPaintTrack(true);

        // Use the slider as the "message" in an Option Dialog
        int choice = JOptionPane.showOptionDialog(this, 
            slider,
            "Select Line Width", 
            JOptionPane.OK_CANCEL_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null,
            null, 
            null);

        if(choice == JOptionPane.OK_OPTION) {
            canvas.width((float) slider.getValue());
        }    
     }
    
    protected void onAboutClick() {                 // Display About dialog
        JLabel logo = null;
        try {
            logo = new JLabel(new ImageIcon(ImageIO.read(new File("resources/logo.png"))));
        } catch(IOException e) {
        }
        
        JLabel title = new JLabel("<html>"
          + "<p><font size=+4>" + TITLE + "</font></p>"
          + "</html>", SwingConstants.CENTER);

        JLabel artists = new JLabel("<html>"
          + "<p>Version " + VERSION + "</p>"
          + "<p>Copyright 2017-2023 by George F. Rice</p>"
          + "<p>Licensed under Gnu GPL 3.0</p>"
          + "<br/>"
          + "<p>Paint Palette by елизавета екимова from the Noun Project</p>"
          + "<p><font size=-2>https://www.thenounproject.com</p>"
          + "<p>Color Swatch icon by Stickers per the Flatiron License</p>"          
          + "<p><font size=-2>https://www.flaticon.com/free-icon/swatches_2954691</p>"
          + "<p>Line Width icon Freepik per the Flatiron License</p>"
          + "<p><font size=-2>https://www.flaticon.com/free-icon/width_764608</p>"
          + "<br/>"
          + "</html>");

        JOptionPane.showMessageDialog(this, new Object[]{logo, title, artists},
            "About", JOptionPane.PLAIN_MESSAGE);
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
