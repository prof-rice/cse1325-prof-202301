import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Canvas extends JPanel {
    public Dimension getPreferredSize() {
        return new Dimension(250,200);                 // Suggest canvas size
    }                                                  //   as 250x200 pixels
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);                // Give JPanel a turn
        Graphics2D g = (Graphics2D) graphics.create(); // Clone and cast        
        g.drawRect(25,20,200,150);                     // Draw a rectangle
    }  
}

