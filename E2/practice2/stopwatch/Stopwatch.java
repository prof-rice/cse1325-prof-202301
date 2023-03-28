import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.JToggleButton;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import javax.swing.JLabel;

import javax.swing.Timer;

class Stopwatch extends JFrame {
    public Stopwatch() {
        // Configure main window
        super("Stopwatch");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        
        // Menu Bar
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("Control");
        
        JMenuItem reset = new JMenuItem("Reset");
        reset.addActionListener(event -> onResetClick());

        JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener(event -> dispose());

        file.add(reset);
        file.add(quit);
        menubar.add(file);
        setJMenuBar(menubar);
        
        // Tool Bar
        JToolBar toolbar = new JToolBar();
        start = new JToggleButton(new ImageIcon("start.png"));
        start.setSelectedIcon(new ImageIcon("pause.png"));
        start.setToolTipText("Start or stop the stopwatch");
        
        toolbar.add(start);
        getContentPane().add(toolbar, BorderLayout.PAGE_START);
        
        // Data Area
        data = new JLabel();
        getContentPane().add(data, BorderLayout.CENTER);
        
        // Status Area
        JLabel status = new JLabel("Play and Pause icons by Icons8");
        getContentPane().add(status, BorderLayout.PAGE_END);
        
        // Show window
        updateDisplay();
        setVisible(true);
        
        // Call onTimerTick() every tenth of a second
        new Timer(100, event -> onTimerTick()).start();
    }
    
    public void onTimerTick() {
        if(start.isSelected()) {
            time += 0.1;
            updateDisplay();
        }
    }
    
    public void onResetClick() {
        start.setSelected(false);
        time = 0;
        updateDisplay();
    }

    private void updateDisplay() {
        data.setText(String.format("<html><big>%.1f</big></html>", time));
    }
    
    public static void main(String[] args) {
        new Stopwatch();
    }
    JToggleButton start;
    private double time = 0;
    JLabel data;
}
