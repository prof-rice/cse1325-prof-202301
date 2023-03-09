import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.BasicStroke;

class Canvas_simple extends JPanel {
    
    public Canvas_simple() {
        setBackground(Color.WHITE); // Default is (often) light grey
    }

    public Dimension getPreferredSize() {
        return new Dimension(750,600);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);                // Give JPanel a turn
        Graphics2D g = (Graphics2D) graphics.create(); // Clone and cast 
        g.setStroke(new BasicStroke(3));               // Make lines3 pixels wide
        
        Rectangle size = getBounds();                  // get canvas size
        final int oneThirdX  = 333 * size.width  / 1000;
        final int twoThirdsX = 666 * size.width  / 1000;
        final int halfX      = 500 * size.width  / 1000;
        final int oneThirdY  = 333 * size.height / 1000;
        final int twoThirdsY = 666 * size.height / 1000;
        final int halfY      = 500 * size.height / 1000;

        g.setColor(Color.RED);                   // red with constant
        g.drawLine(oneThirdX, twoThirdsY, twoThirdsX, twoThirdsY); // --
        
        g.setColor(new Color(0, 255, 0));        // green with int
        g.drawLine(oneThirdX, twoThirdsY,  halfX, oneThirdY);      // /
        
        g.setColor(new Color(0.0f, 0.0f, 1.0f)); // blue with double
        g.drawLine(twoThirdsX, twoThirdsY, halfX, oneThirdY);      // \ 
        
    }
}

public class Demo_simple  extends JFrame {
    public Demo_simple() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new Canvas_simple());  // Put the drawing surface in the window
        pack();                    // Auto-resize to Canvas.getPreferredSize()
        setVisible(true);          // Show the main window
    }
    public static void main(String[] args) {
        new Demo_simple();
    }
}

