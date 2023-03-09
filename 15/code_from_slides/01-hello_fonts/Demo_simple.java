import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;

class Canvas_simple extends JPanel {
    public Dimension getPreferredSize() {
        return new Dimension(250,200);                 // Suggest canvas size
    }                                                  //   as 800x640 pixels
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);                // Give JPanel a turn
        Graphics2D g = (Graphics2D) graphics.create(); // Clone and cast
        
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        g.drawString("Hello, World!",25,50);           // Print text to canvas

    }  
}

public class Demo_simple extends JFrame {
    public Demo_simple() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new Canvas_simple());  // Put the drawing surface in the window
        pack();             // Auto-resize to Canvas.getPreferredSize()
        setVisible(true);   // Show the main window
    }
    public static void main(String[] args) {
        new Demo_simple();
    }
}

