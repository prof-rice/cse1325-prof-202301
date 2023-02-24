import javax.swing.JFrame;           // for main window
import javax.swing.JOptionPane;      // for standard dialogs
// import javax.swing.JDialog;          // for custom dialogs (for alternate About dialog)
import javax.swing.JMenuBar;         // row of menu selections
import javax.swing.JMenu;            // menu selection that offers another menu
import javax.swing.JMenuItem;        // menu selection that does something
import javax.swing.JToolBar;         // row of buttons under the menu
import javax.swing.JButton;          // regular button
import javax.swing.JToggleButton;    // 2-state button
import javax.swing.BorderFactory;    // manufacturers Border objects around buttons
import javax.swing.Box;              // to create toolbar spacer
import javax.swing.UIManager;        // to access default icons
import javax.swing.JLabel;           // text or image holder
import javax.swing.ImageIcon;        // holds a custom icon
import javax.swing.SwingConstants;   // useful values for Swing method calls

import javax.imageio.ImageIO;        // loads an image from a file

import java.io.File;                 // opens a file
import java.io.IOException;          // reports an error reading from a file

import java.awt.BorderLayout;        // layout manager for main window
import java.awt.FlowLayout;          // layout manager for About dialog

import java.awt.Color;               // the color of widgets, text, or borders
import java.awt.Font;                // rich text in a JLabel or similar widget
import java.awt.image.BufferedImage; // holds an image loaded from a file

public class MainWin extends JFrame {
    public MainWin(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        
        // /////// ////////////////////////////////////////////////////////////////
        // M E N U
        // Add a menu bar to the PAGE_START area of the Border Layout

        JMenuBar menubar = new JMenuBar();
        
        JMenu     file       = new JMenu("File");
        JMenuItem anew       = new JMenuItem("New Game");
        JMenuItem quit       = new JMenuItem("Quit");
        JMenu     help       = new JMenu("Help");
        JMenuItem rules      = new JMenuItem("Rules");
        JMenuItem about      = new JMenuItem("About");
        
        anew .addActionListener(event -> onNewGameClick());
        quit .addActionListener(event -> onQuitClick());
        rules.addActionListener(event -> onRulesClick());
        about.addActionListener(event -> onAboutClick());

        
        file.add(anew);
        file.add(quit);
        help.add(rules);
        help.add(about);
        
        menubar.add(file);
        menubar.add(help);
        setJMenuBar(menubar);
        
        // ///////////// //////////////////////////////////////////////////////////
        // T O O L B A R
        // Add a toolbar to the PAGE_START region below the menu
        JToolBar toolbar = new JToolBar("Nim Controls");

        // Add a New Game stock icon
        JButton anewB  = new JButton(UIManager.getIcon("FileView.fileIcon"));
          anewB.setActionCommand("New Game");
          anewB.setToolTipText("Create a new game, discarding any in progress");
          anewB.setBorder(null);
          toolbar.add(anewB);
          anewB.addActionListener(event -> onNewGameClick());
        
        // A "horizontal strut" is just a space of the specified pixel width
        toolbar.add(Box.createHorizontalStrut(25));
        
        // Create the 3 buttons using the icons provided
        ImageIcon ii = new ImageIcon("button1.png");
        button1  = new JButton(new ImageIcon("button1.png"));
          button1.setActionCommand("Select one stick");
          button1.setToolTipText("Select one stick");
          toolbar.add(button1);
          button1.addActionListener(event -> onButtonClick(1));

        button2    = new JButton(new ImageIcon("button2.png"));
          button2.setActionCommand("Select two sticks");
          button2.setToolTipText("Select two sticks");
          toolbar.add(button2);
          button2.addActionListener(event -> onButtonClick(2));

        button3 = new JButton(new ImageIcon("button3.png"));
          button3.setActionCommand("Select three sticks");
          button3.setToolTipText("Select three sticks");
          toolbar.add(button3);
          button3.addActionListener(event -> onButtonClick(3));
        
        toolbar.add(Box.createHorizontalStrut(25));
        
        // Create a toggle button to enable or disable the computer player
        computerPlayer = new JToggleButton(new ImageIcon("freepik_robot.png"));
          computerPlayer.setActionCommand("Enable computer player");
          computerPlayer.setToolTipText("Enable computer to be Player 2");
          computerPlayer.setBorder(null);
          toolbar.add(computerPlayer);
          computerPlayer.addActionListener(event -> onComputerPlayerClick());

        // "Horizontal glue" expands as much as possible, pushing the "X" to the right
        toolbar.add(Box.createHorizontalGlue());
        
        // Create a custom Quit button (not available in Swing stock icons)
        JButton quitB  = new JButton("X");
          quitB.setActionCommand("Quit");
          quitB.setToolTipText("Exit game");
          quitB.setBorder(null);
          toolbar.add(quitB);
          quitB.addActionListener(event -> onQuitClick());
        toolbar.addSeparator();

        getContentPane().add(toolbar, BorderLayout.PAGE_START);
        
        
        // /////////////////////////// ////////////////////////////////////////////
        // S T I C K S   D I S P L A Y
        // Provide a text entry box to show the remaining sticks
        sticks = new JLabel();
        sticks.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(sticks, BorderLayout.CENTER);

        // S T A T U S   B A R   D I S P L A Y ////////////////////////////////////
        // Provide a status bar for game messages
        msg = new JLabel();
        add(msg, BorderLayout.PAGE_END);
        
        // Make everything in the JFrame visible
        setVisible(true);
        
        // Start a new game
        onNewGameClick();
    }
    
    // Listeners
    
    protected void onNewGameClick() {         // Create a new game
        nim = new Nim();
        setSticks();
        msg.setFont(new JLabel().getFont());    // Reset to default font
    }
    
    protected void onButtonClick(int button) {  // Select 1, 2, or 3 sticks from pile
        try {
            // Catch the "impossible" out of sticks exception
            nim.takeSticks(button);
            setSticks();
        } catch(Exception e) {
            sticks.setText("FAIL: " + e.getMessage() + ", start new game");
        }
    }
            
    protected void onComputerPlayerClick() {   // Enable / disable computer player
        setSticks();
        // Java Swing requires action to visually indicate enabled / disabled button
        computerPlayer.setBorder(computerPlayer.isSelected() ? BorderFactory.createLineBorder(Color.black) : null);
    }
    protected void onRulesClick() {             // Show the rules
        String s = "The Rules of Nim\n\nCopyright 2017-2023 by George F. Rice - CC BY 4.0\n\n" +
            "The two players alternate taking 1 to 3 sticks from the pile.\n" +
            "The goal is to force your opponent to take the last stick (called misère rules).\n" +
            "If the computer button is up, it's a two player game. If down, the computer is always Player 2.)";
        JOptionPane.showMessageDialog(this, s, "The Rules of Nim", JOptionPane.PLAIN_MESSAGE);
    }
    protected void onAboutClick() {                 // Display About dialog
        JLabel logo = null;
        try {
            BufferedImage myPicture = ImageIO.read(new File("128px-Pyramidal_matches.png"));
            logo = new JLabel(new ImageIcon(myPicture));
        } catch(IOException e) {
        }
        
        JLabel title = new JLabel("<html>"
          + "<p><font size=+4>Nim</font></p>"
          + "<p>Version 1.4J</p>"
           + "</html>",
          SwingConstants.CENTER);

        JLabel artists = new JLabel("<html>"
          + "<br/><p>Copyright 2017-2023 by George F. Rice</p>"
          + "<p>Licensed under Gnu GPL 3.0</p><br/>"
          + "<p>Logo by M0tty, licensed under CC BY-SA 3.0</p>"
          + "<p><font size=-2>https://commons.wikimedia.org/wiki/File:Pyramidal_matches.svg</font></p>"
          + "<p>Robot by FreePik.com, licensed for personal</p><p>and commercial purposes with attribution</p>"
          + "<p><font size=-2>https://www.freepik.com/free-vector/grey-robot-silhouettes_714902.htm</font></p>"
          + "</html>");
          
         JOptionPane.showMessageDialog(this, 
             new Object[]{logo, title, artists},
             "The Game of Nim",
             JOptionPane.PLAIN_MESSAGE
         );
     }

/*
    // This is an alternate About dialog using JDialog instead of JOptionPane
    
    protected void onAboutClick() {                 // Display About dialog
        JDialog about = new JDialog();
        about.setLayout(new FlowLayout());
        about.setTitle("The Game of Nim");
        
        try {
            BufferedImage myPicture = ImageIO.read(new File("128px-Pyramidal_matches.png"));
            JLabel logo = new JLabel(new ImageIcon(myPicture));
            about.add(logo);
        } catch(IOException e) {
        }
        
        JLabel title = new JLabel("<html>"
          + "<p><font size=+4>Nim</font></p>"
          + "</html>");
        about.add(title);

        JLabel artists = new JLabel("<html>"
          + "<p>Version 1.4J</p>"
          + "<p>Copyright 2017-2023 by George F. Rice</p>"
          + "<p>Licensed under Gnu GPL 3.0</p>"
          + "<p>Logo by M0tty, licensed under CC BY-SA 3.0</p>"
          + "<p><font size=-2>https://commons.wikimedia.org/wiki/File:Pyramidal_matches.svg</font></p>"
          + "<p>Robot by FreePik.com, licensed for personal</p><p>and commercial purposes with attribution</p>"
          + "<p><font size=-2>https://www.freepik.com/free-vector/grey-robot-silhouettes_714902.htm</font></p>"
          + "</html>");
        about.add(artists);

        JButton ok = new JButton("OK");
        ok.addActionListener(event -> about.setVisible(false));
        about.add(ok);
        
        about.setSize(450,400);
        about.setVisible(true);
     }
*/
    protected void onQuitClick() {System.exit(0);}   // Exit the game

    private void setSticks() {              // Update display, robot move
        // s collects the status message
        String s = "";
        
        // If the robot is enabled and it's their turn, move the robot
        if(nim.sticksLeft() > 0) {
            if(computerPlayer.isSelected() && nim.currentPlayer() == 2) {
                int move = 1;
                try {
                    move = nim.optimalMove();
                } catch(Exception e) {
                    System.err.println("Invalid optimal move: " + e.getMessage());
                }
                s += "Robot plays " + move + ", ";
                nim.takeSticks(move);
            }
        }
        
        // Report who's turn it is, or (if all sticks gone) who won
        
        if (nim.sticksLeft() > 0) {
            s += "Player " + nim.currentPlayer() + "'s turn";
        } else {
            s += "Player " + nim.currentPlayer() +  " wins!";
            msg.setFont(new Font("SansSerif", Font.BOLD, 18));
        }
        
        // Display the collected status on the status bar
        msg.setText(s);

        // Update the visual display of sticks
        s = "";
        for(int i=0; i<nim.sticksLeft(); ++i) s += ("| ");
        s += "  (" + (nim.sticksLeft()) + " sticks)";
        sticks.setText(s);

        // Set sensitivity of the human stick selectors so user can't make an illegal move
        button1.setEnabled(nim.sticksLeft() > 0);
        button2.setEnabled(nim.sticksLeft() > 1);
        button3.setEnabled(nim.sticksLeft() > 2);
    }
    
    private Nim nim;
    
    private JLabel sticks;                  // Display of sticks on game board
    private JLabel msg;                     // Status message display
    private JButton button1;                // Button to select 1 stick
    private JButton button2;                // Button to select 2 sticks
    private JButton button3;                // Button to select 3 sticks
    private JToggleButton computerPlayer;   // Button to enable robot

}
