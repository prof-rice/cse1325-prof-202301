import java.awt.GraphicsEnvironment;
import java.awt.BorderLayout;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.image.BufferedImage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.LineNumberReader; // Remembers file line number!

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class MainWin extends JFrame {
    public static final String TITLE = "CSE1325 Paint";
    public static final String MAGIC_COOKIE = "𝓒𝓼𝓮 ₁₃₂₅ 🎨 𝓟𝓪𝓲𝓷𝓽";
    public static final String VERSION = "0.9.0";
    public static final String FILE_VERSION = "0.1";
    public static final String EXTENSION = "paint";

    public MainWin(String title) {
        super(title);

        setSize(1024, 768);
        
        // /////// ////////////////////////////////////////////////////////////////
        // M E N U
        // Add a menu bar to the PAGE_START area of the Border Layout

        JMenuBar menubar = new JMenuBar();
        
        // File menu
        
        JMenu     file       = new JMenu("File");
        
        // File > New
        JMenuItem newp       = new JMenuItem("New");        
        newp.addActionListener(event -> onNewClick());
        file.add(newp);
        
        // File > Open
        JMenuItem open       = new JMenuItem("Open");        
        open.addActionListener(event -> onOpenClick());
        file.add(open);
        
        // File > Save
        JMenuItem save       = new JMenuItem("Save");        
        save.addActionListener(event -> onSaveClick());
        file.add(save);
        
        // File > Save As
        JMenuItem saveAs     = new JMenuItem("Save As");        
        saveAs.addActionListener(event -> onSaveAsClick());
        file.add(saveAs);
        
        // File > Quit
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

        // Pen menu
        JMenu     pen         = new JMenu("Pen");
        
        // Pen > Line
        JMenuItem line       = new JMenuItem("Line");        
        line.addActionListener(event -> onTextClick());
        
        // Pen > Text
        JMenuItem text       = new JMenuItem("Text");        
        text.addActionListener(event -> onTextClick());
        
        // Pen > Font
        JMenuItem font       = new JMenuItem("Font");        
        font.addActionListener(event -> onFontClick());
        
        // Pen > Color
        JMenuItem color       = new JMenuItem("Color");        
        color.addActionListener(event -> onColorClick());

        // Pen > Width
        JMenuItem width       = new JMenuItem("Width");        
        width.addActionListener(event -> onWidthClick());
        
        // Help menu

        JMenu     help       = new JMenu("Help");
        JMenuItem about      = new JMenuItem("About");        
        about.addActionListener(event -> onAboutClick());
        
        file.add(quit);
        pen.add(line);
        pen.add(text);
        pen.add(font);
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

        // New button
        JButton newButton  = 
            new JButton(new ImageIcon("resources/new_file.png"));
        newButton.setActionCommand("New");
        newButton.setToolTipText("Create a new painting");
        toolbar.add(newButton);
        newButton.addActionListener(event -> onNewClick());
        
        // Open button
        JButton openButton  = 
            new JButton(new ImageIcon("resources/open.png"));
        openButton.setActionCommand("Open");
        openButton.setToolTipText("Open a painting from a file");
        toolbar.add(openButton);
        openButton.addActionListener(event -> onOpenClick());
        
        // Save button
        JButton saveButton  = 
            new JButton(new ImageIcon("resources/save.png"));
        saveButton.setActionCommand("Save");
        saveButton.setToolTipText("Save the painting to the current file");
        toolbar.add(saveButton);
        saveButton.addActionListener(event -> onSaveClick());

        // Save As button
        JButton saveAsButton  = 
            new JButton(new ImageIcon("resources/save_as.png"));
        saveAsButton.setActionCommand("Save As");
        saveAsButton.setToolTipText("Save the painting to a different file");
        toolbar.add(saveAsButton);
        saveAsButton.addActionListener(event -> onSaveAsClick());

        // A "horizontal strut" is just a space of the specified pixel width
        toolbar.add(Box.createHorizontalStrut(25));
        
        // These buttons are radio buttons
        ButtonGroup penModeGroup = new ButtonGroup();
  
        // Create the line toggle button (acts like a radio button)
        lineButton  = 
            new JToggleButton(new ImageIcon("resources/line.png"), true);
        lineButton.setActionCommand("DrawLines");
        lineButton.setToolTipText("Draw single lines (click-click)");
        penModeGroup.add(lineButton);
        toolbar.add(lineButton);
        lineButton.addActionListener(event -> onLineClick());
        
        // Create the Text toggle button (acts like a radio button)
        textButton  = 
            new JToggleButton(new ImageIcon("resources/text.png"));
        textButton.setActionCommand("DrawText");
        textButton.setToolTipText("Draw text (enter text and click on location)");
        penModeGroup.add(textButton);
        toolbar.add(textButton);
        textButton.addActionListener(event -> onTextClick());
        
        // A "horizontal strut" is just a space of the specified pixel width
       toolbar.add(Box.createHorizontalStrut(25));
  
        // Create the Color button
        JButton colorButton  = 
            new JButton(new ImageIcon("resources/select_pen_color.png"));
        colorButton.setActionCommand("SelectPenColor");
        colorButton.setToolTipText("Select the pen color");
        toolbar.add(colorButton);
        colorButton.addActionListener(event -> onColorClick());

        // Create the Width button
        JButton widthButton  = 
            new JButton(new ImageIcon("resources/select_pen_width.png"));
        widthButton.setActionCommand("SelectPenWidth");
        widthButton.setToolTipText("Select the pen width");
        toolbar.add(widthButton);
        widthButton.addActionListener(event -> onWidthClick());

        // Create the Font button
        JButton fontButton  = 
            new JButton(new ImageIcon("resources/font.png"));
        fontButton.setActionCommand("SelectFont");
        fontButton.setToolTipText("Select the font");
        toolbar.add(fontButton);
        fontButton.addActionListener(event -> onFontClick());

        getContentPane().add(toolbar, BorderLayout.PAGE_START);
      
        // /////////////////////////// ////////////////////////////////////////////
        // C A N V A S
        // Provide a JPanel to act as the Canvas
        // canvas = new Canvas();
        // add(canvas, BorderLayout.CENTER);
        onNewClick();
        
        // Make everything in the JFrame visible
        setVisible(true);
    }
    
    // Listeners
    protected void onNewClick() {
        if(okToExit()) {
            if(canvas != null) {
                remove(canvas);  // Must remove old Canvas for garbage collector
                canvas = null;   // In case new Canvas() fails
            }
            canvas = new Canvas();
            add(canvas, BorderLayout.CENTER);
            revalidate();
            repaint();
        }
    }

    protected void onOpenClick() {
        // Don't discard unsaved data without permission
        if(!okToExit()) return;  

        // Identify a default filename
        File filename = new File("untitled." + EXTENSION);;

        // If canvas exists, get filename from it instead, then remove it
        if(canvas != null) {
            filename = canvas.filename();
            remove(canvas); // Don't delete twice
            canvas = null;
        }

        // Create a file chooser
        final JFileChooser fc = new JFileChooser(filename);
        FileFilter paintFiles = new FileNameExtensionFilter("Paint files", EXTENSION);
        fc.addChoosableFileFilter(paintFiles);
        fc.setFileFilter(paintFiles);  // Show CSE1325 Paint files only by default
        
        // Run the dialog and return the button clicked
        int result = fc.showOpenDialog(this);
        
        // If the user clicked OK, set the filename; if Cancel, just return
        if (result == JFileChooser.APPROVE_OPTION) {
            filename = fc.getSelectedFile();
        } else if (result == JFileChooser.CANCEL_OPTION) {
            onNewClick();
            return;
        }
         
        // Preset error message (what a pessimist!)
        // This allows us to capture the line number in our data file
        //   in the event of an error via LineNumberReader
        String errorMessage = "Unable to open " + filename;
        try (LineNumberReader br = new LineNumberReader(new FileReader(filename))) {
            try {
                // Verify that this is indeed a CSE1325 Paint-generated file
                String magicCookie = br.readLine();
                if(!magicCookie.equals(MAGIC_COOKIE)) 
                    throw new IOException("Not a " + TITLE + " file");
                    
                // Verify that the file format is known (i.e., version is <= to our version)
                String fileVersion = br.readLine();
                if(fileVersion.compareTo(FILE_VERSION) > 0) 
                    throw new IOException("Incompatible file format version " + fileVersion);
                
                // Verify that a canvas was saved (written by Canvas.save)
                String programName = br.readLine();
                if(!programName.equals(Canvas.id())) // Was this added by Canvas.save?
                    throw new IOException("Missing '" + Canvas.id() + "' ID");
                
                // Instance a new Canvas from the file stream
                canvas = new Canvas(br, fileVersion);
                add(canvas, BorderLayout.CENTER);
                revalidate();
                repaint();
                
            // On a read error from opened file, capture the line number and rethrow
            } catch (Exception e) {
                if (br != null) 
                    errorMessage = " Unable to read " + filename 
                                 + " at line " + br.getLineNumber();
                throw e;
            }
        // On any error (file open OR read), report to the user and clean up
        } catch (Exception e) {
            JDialog dialog = new JOptionPane(
                errorMessage + '\n' + e, 
                JOptionPane.ERROR_MESSAGE,
                JOptionPane.DEFAULT_OPTION).createDialog("Failed"); 
            dialog.setAlwaysOnTop(true); 
            dialog.setVisible(true);        // Show the error dialog
            dialog.dispose();               // Delete the dialog from memory

            canvas = null;  // Discard possibly damaged Canvas
            onNewClick();   // Create a new, clean Canvas
        }
    }

    protected void onSaveClick() {
        // Get canvas' current filename
        File filename = canvas.filename();
        
        // Open that file for writing
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            bw.write(MAGIC_COOKIE + '\n');  // Save a cookie (to recognize Paint files)
            bw.write(FILE_VERSION + '\n');  // Save the file version (for later use)
            canvas.save(bw);                // Save the painting
            
        // If an exception occurred, report it to the user
        } catch (Exception e) {
            JDialog dialog = 
                new JOptionPane("Unable to open " + filename + '\n' + e, 
                    JOptionPane.ERROR_MESSAGE,
                    JOptionPane.DEFAULT_OPTION).createDialog("Failed"); 
            dialog.setAlwaysOnTop(true); 
            dialog.setVisible(true);        // Show the error dialog
            dialog.dispose();               // Delete the dialog from memory
        }
    }

    protected void onSaveAsClick() {
        // Identify a default filename
        File filename = canvas.filename();

        // Create a file chooser
        final JFileChooser fc = new JFileChooser(filename);
        FileFilter paintFiles = new FileNameExtensionFilter("Paint files", EXTENSION);
        fc.addChoosableFileFilter(paintFiles);
        fc.setFileFilter(paintFiles);  // Show CSE1325 Paint files only by default
        
        // Run the dialog and return the button clicked
        int result = fc.showSaveDialog(this);
        
        // If user clicked OK, get the filename
        if (result == JFileChooser.APPROVE_OPTION) {
            filename = fc.getSelectedFile();

            // If the filename is missing / incorrect, append it
            if(!filename.getPath().toUpperCase().endsWith(EXTENSION.toUpperCase()))
                filename = new File(filename.getPath() + '.' + EXTENSION);

            // Set canvas' current filename to this, then save to current filename
            canvas.filename(filename);
            onSaveClick();
        }
    }
    
    protected void onQuitClick() {   // Exit the program
        if(okToExit()) dispose(); //System.exit(0);
    }
    
    protected void onLineClick() {   // Draw connected lines
        canvas.penMode(Canvas.PenMode.LINE);
        lineButton.setSelected(true); // This does NOT trigger an event
    }

    protected void onTextClick() {   // Create a text entry
        String text = JOptionPane.showInputDialog(this, "Select text");
        if(text != null) {
            canvas.setText(text);
            canvas.penMode(Canvas.PenMode.TEXT);
        }
        textButton.setSelected(true); // This does NOT trigger an event
    }
    protected void onFontClick() {  // Select new Font
        JLabel lName = new JLabel("Font Name");
        JComboBox<String> comboFonts = new JComboBox<>(
            GraphicsEnvironment.getLocalGraphicsEnvironment()
                               .getAvailableFontFamilyNames());
        comboFonts.setSelectedItem(canvas.getFontName());

        JLabel lStyle = new JLabel("<html><br></html>");
        JCheckBox bold    = new JCheckBox("Bold",    (canvas.getFontStyle() & Font.BOLD)   != 0);
        JCheckBox italics = new JCheckBox("Italics", (canvas.getFontStyle() & Font.ITALIC) != 0);
        
        JLabel lSize = new JLabel("<html><br>Font Size</html>");
        SpinnerModel fontSizeModel = new SpinnerNumberModel(canvas.getFontSize(), 1, 384, 1);
        JSpinner size = new JSpinner(fontSizeModel);
        
        int button = JOptionPane.showConfirmDialog(this, new Object[]{
            lName, comboFonts, lStyle, bold, italics, lSize, size},
            "Edit Font", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if(button == JOptionPane.OK_OPTION)
            canvas.setFont(new Font((String) comboFonts.getSelectedItem(),
                                    ((bold   .isSelected()) ? Font.BOLD   : Font.PLAIN)
                                  + ((italics.isSelected()) ? Font.ITALIC : Font.PLAIN),
                                    (Integer) size.getValue()));
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
          + "<p>Color Swatch and Text icons by Stickers per the Flatiron License</p>"
          + "<p><font size=-2>https://www.flaticon.com/free-icon/swatches_2954691</p>"
          + "<p><font size=-2>https://www.flaticon.com/free-icon/text_3856192</p>"
          + "<p>Line and Line Width icons by Freepik per the Flatiron License</p>"
          + "<p><font size=-2>https://www.flaticon.com/free-icon/diagonal-line_815497</p>"
          + "<p><font size=-2>https://www.flaticon.com/free-icon/width_764608</p>"
          + "<p>Some icons from Freepik per the Freepik Terms of Use (June 2019)</p>"
          + "<p>-- File I/O icons by Kiranshastry</p>"
          + "<p>-- Brush icon by Good Ware</p>"
          + "<p><font size=-2>https://www.freepik.com</p>"
          + "</html>");

        JOptionPane.showMessageDialog(this, new Object[]{logo, title, artists},
            "About", JOptionPane.PLAIN_MESSAGE);
    }

    protected boolean okToExit() {  // Returns true if OK to exit
        if (canvas == null) return true;     // OK to exit if no Canvas
        if (!canvas.isDirty()) return true;  // OK to exit if canvas already saved
        int choice = JOptionPane.showOptionDialog(this,
            "Save before discarding canvas?",
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
    
    private JToggleButton lineButton;
    private JToggleButton textButton;

}
