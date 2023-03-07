package gui;

import store.Store;
import store.Customer;
import store.Option;
import store.Computer;
import store.Order;

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
import javax.swing.JComboBox;        // for selecting from lists
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
    public enum Record {CUSTOMER, OPTION, COMPUTER, ORDER};
    public MainWin(String title) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 480);
        
        // /////// ////////////////////////////////////////////////////////////////
        // M E N U
        // Add a menu bar to the PAGE_START area of the Border Layout

        JMenuBar menubar = new JMenuBar();
        
        JMenu     file       = new JMenu("File");
        JMenuItem quit       = new JMenuItem("Quit");

        JMenu     insert     = new JMenu("Insert");
        JMenuItem iCustomer  = new JMenuItem("Customer");
        JMenuItem iOption    = new JMenuItem("Option");
        JMenuItem iComputer  = new JMenuItem("Computer");
        
        JMenu     view       = new JMenu("View");
        JMenuItem vCustomer  = new JMenuItem("Customers");
        JMenuItem vOption    = new JMenuItem("Options");
        JMenuItem vComputer  = new JMenuItem("Computers");
        
        JMenu     help       = new JMenu("Help");
        JMenuItem about      = new JMenuItem("About");
        
        quit .addActionListener(event -> onQuitClick());

        iCustomer.addActionListener(event -> onInsertCustomerClick());
        iOption  .addActionListener(event -> onInsertOptionClick());
        iComputer.addActionListener(event -> onInsertComputerClick());

        vCustomer.addActionListener(event -> onViewClick(Record.CUSTOMER));
        vOption  .addActionListener(event -> onViewClick(Record.OPTION));
        vComputer.addActionListener(event -> onViewClick(Record.COMPUTER));

        about.addActionListener(event -> onAboutClick());

        file.add(quit);
        insert.add(iCustomer);
        insert.add(iOption);
        insert.add(iComputer);
        view.add(vCustomer);
        view.add(vOption);
        view.add(vComputer);
        help.add(about);
        
        menubar.add(file);
        menubar.add(insert);
        menubar.add(view);
        menubar.add(help);
        
        setJMenuBar(menubar);
 
        // ///////////// //////////////////////////////////////////////////////////
        // T O O L B A R
        // Add a toolbar to the PAGE_START region below the menu
        JToolBar toolbar = new JToolBar("ELSA Controls");

        // Create the 3 buttons using the icons provided
        JButton bAddCustomer = new JButton(new ImageIcon("gui/resources/add_customer.png"));
          bAddCustomer.setActionCommand("Insert Customer");
          bAddCustomer.setToolTipText("Insert Customer");
          toolbar.add(bAddCustomer);
          bAddCustomer.addActionListener(event ->onInsertCustomerClick());

        JButton bAddOption = new JButton(new ImageIcon("gui/resources/add_option.png"));
          bAddOption.setActionCommand("Insert Option");
          bAddOption.setToolTipText("Insert Option");
          toolbar.add(bAddOption);
          bAddOption.addActionListener(event -> onInsertOptionClick());

        JButton bAddComputer = new JButton(new ImageIcon("gui/resources/add_computer.png"));
          bAddComputer.setActionCommand("Insert Computer");
          bAddComputer.setToolTipText("Insert Computer");
          toolbar.add(bAddComputer);
          bAddComputer.addActionListener(event -> onInsertComputerClick());
        
        toolbar.add(Box.createHorizontalStrut(25));

        // Create the 3 buttons using the icons provided
        JButton bViewCustomers = new JButton(new ImageIcon("gui/resources/view_customers.png"));
          bViewCustomers.setActionCommand("View Customer");
          bViewCustomers.setToolTipText("View Customers");
          toolbar.add(bViewCustomers);
          bViewCustomers.addActionListener(event ->onViewClick(Record.CUSTOMER));

        JButton bViewOptions = new JButton(new ImageIcon("gui/resources/view_options.png"));
          bViewOptions.setActionCommand("View Options");
          bViewOptions.setToolTipText("View Options");
          toolbar.add(bViewOptions);
          bViewOptions.addActionListener(event -> onViewClick(Record.OPTION));

        JButton bViewComputers = new JButton(new ImageIcon("gui/resources/view_computers.png"));
          bViewComputers.setActionCommand("View Computers");
          bViewComputers.setToolTipText("View Computers");
          toolbar.add(bViewComputers);
          bViewComputers.addActionListener(event -> onViewClick(Record.COMPUTER));
        
        getContentPane().add(toolbar, BorderLayout.PAGE_START);
       
        
        // /////////////////////////// ////////////////////////////////////////////
        // D I S P L A Y
        // Provide a label to show data requested by the user
        display = new JLabel();
        display.setFont(new Font("SansSerif", Font.BOLD, 14));
        display.setVerticalAlignment(JLabel.TOP);
        add(display, BorderLayout.CENTER);

        // Make everything in the JFrame visible
        setVisible(true);
        
        // Start a new store
        store = new Store("ELSA Prime");
    }
    
    // Listeners

    protected void onQuitClick() {System.exit(0);}   // Exit the game
    
    protected void onInsertCustomerClick() {
        try { 
            store.add(new Customer(
                JOptionPane.showInputDialog(this, "Customer name", 
                    "New Customer", JOptionPane.QUESTION_MESSAGE),
                JOptionPane.showInputDialog(this, "Customer email", 
                    "New Customer", JOptionPane.QUESTION_MESSAGE)
            ));
            onViewClick(Record.CUSTOMER);
        } catch(NullPointerException e) {
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, e, 
                "Customer Not Created", JOptionPane.ERROR_MESSAGE);
        }    
    }
            
    protected void onInsertOptionClick() { 
        try { 
            store.add(new Option(
                JOptionPane.showInputDialog(this, "Option name", 
                    "New Option", JOptionPane.QUESTION_MESSAGE),
                (long) (100.0 * Double.parseDouble(
                    JOptionPane.showInputDialog(this, "Option cost", 
                        "New Option", JOptionPane.QUESTION_MESSAGE)))
            ));
            onViewClick(Record.OPTION);
        } catch(NullPointerException e) {
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, e, 
                "Customer Not Created", JOptionPane.ERROR_MESSAGE);
        }
    
    }
            
    protected void onInsertComputerClick() { 
        try { 
            Computer c = new Computer(
                JOptionPane.showInputDialog(this, "Computer name", 
                    "New Computer", JOptionPane.QUESTION_MESSAGE),
                JOptionPane.showInputDialog(this, "Computer model", 
                    "New Computer", JOptionPane.QUESTION_MESSAGE)
            );
            JComboBox<Object> cb = new JComboBox<>(store.options());
            int optionsAdded = 0; // Don't add computers with no options
            while(true) {
                int button = JOptionPane.showConfirmDialog(this, cb, 
                    "Another Option?", JOptionPane.YES_NO_OPTION);
                if(button != JOptionPane.YES_OPTION) break;
                c.addOption((Option) cb.getSelectedItem());
                ++optionsAdded;
            }
            if(optionsAdded > 0) {
                store.add(c);
                onViewClick(Record.COMPUTER);
            }
        } catch(NullPointerException e) {
        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, e, 
                "Computer Not Created", JOptionPane.ERROR_MESSAGE);
        }    
   
    }
                        
    protected void onViewClick(Record record) { 
        String header = null;
        Object[] list = null;
        if(record == Record.CUSTOMER) {
            header = "Our Beloved Customers";
            list = store.customers();
        }
        if(record == Record.OPTION) {
           header = "Options for our SuperComputers";
           list = store.options();
        }
        if(record == Record.COMPUTER) {
            header = "Computers for Sale - Cheap!";
            list = store.computers();
        }
        if(record == Record.ORDER) {
            header = "Orders Placed to Date";
            list = store.orders();
        }
        
        StringBuilder sb = new StringBuilder("<html><p><font size=+2>" 
                                  + header + "</font></p><br/>\n<ol>\n");
        for(Object i : list) sb.append("<li>" + i.toString().replaceAll("<","&lt;")
                                                            .replaceAll(">", "&gt;")
                                                            .replaceAll("\n", "<br/>") + "</li>\n");
        sb.append("</ol></html>");
        display.setText(sb.toString());
    }
            
    protected void onAboutClick() {                 // Display About dialog
        JLabel title = new JLabel("<html>"
          + "<p><font size=+4>ELSA</font></p>"
          + "</html>",
          SwingConstants.CENTER);

        JLabel subtitle = new JLabel("<html>"
          + "<p>Exceptional Laptops and Supercomputers Always</p>"
          + "</html>",
          SwingConstants.CENTER);

        JLabel version = new JLabel("<html>"
          + "<p>Version 0.2</p>"
          + "</html>",
          SwingConstants.CENTER);

        JLabel artists = new JLabel("<html>"
          + "<br/><p>Copyright 2017-2023 by George F. Rice</p>"
          + "<p>Licensed under Gnu GPL 3.0</p><br/>"
          
          + "<br/><p>Add Customer icon based on work by Dreamstate per the Flaticon License</p>"
          + "<p><font size=-2>https://www.flaticon.com/free-icon/user_3114957</font></p>"

          + "<br/><p>View Customers icon based on work by Ilham Fitrotul Hayat per the Flaticon License</p>"
          + "<p><font size=-2>https://www.flaticon.com/free-icon/group_694642</font></p>"

          + "<br/><p>Add Option icon based on work by Freepik per the Flaticon License</p>"
          + "<p><font size=-2>https://www.flaticon.com/free-icon/quantum-computing_4103999</font></p>"

          + "<br/><p>View Options icon based on work by Freepik per the Flaticon License</p>"
          + "<p><font size=-2>https://www.flaticon.com/free-icon/edge_8002173</font></p>"

          + "<br/><p>Add Computer icon based on work by Freepik per the Flaticon License</p>"
          + "<p><font size=-2>https://www.flaticon.com/free-icon/laptop_689396</font></p>"

          + "<br/><p>View Computers icon based on work by Futuer per the Flaticon License</p>"
          + "<p><font size=-2>https://www.flaticon.com/free-icon/computer-networks_9672993</font></p>"
 
          + "</html>");
          
         JOptionPane.showMessageDialog(this, 
             new Object[]{title, subtitle, version, artists},
             "ELSA",
             JOptionPane.PLAIN_MESSAGE
         );
     }

/*
    protected void onAboutClick() {                 // Display About dialog
        JLabel title = new JLabel("<html>"
          + "<p><font size=+4>ELSA</font></p>"
          + "<p>Exceptional Laptops and Supercomputers Always</p>"
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
             new Object[]{title, artists},
             "ELSA",
             JOptionPane.PLAIN_MESSAGE
         );
     }


    // This is an alternate About dialog using JDialog instead of JOptionPane
    
    protected void onAboutClick() {                 // Display About dialog
        JDialog about = new JDialog();
        about.setLayout(new FlowLayout());
        about.setTitle("ELSA");
        
        try {
            BufferedImage myPicture = ImageIO.read(new File("128px-Pyramidal_matches.png"));
            JLabel logo = new JLabel(new ImageIcon(myPicture));
            about.add(logo);
        } catch(IOException e) {
        }
        
        JLabel title = new JLabel("<html>"
          + "<p><font size=+4>ELSAim</font></p>"
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

    private Store store;                    // The current Elsa store    
    private JLabel display;                 // Display page of data
}
