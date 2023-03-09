import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Canvas extends JPanel {
    private final static int border = 25;

    public Dimension getPreferredSize() {
        return new Dimension(250,200);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);                // Give JPanel a turn
        Graphics2D g = (Graphics2D) graphics.create(); // Clone and cast        
        Rectangle size = getBounds();                  // Current size of canvas
        g.drawRect(border, border, size.width - 2*border, size.height - 2*border);
    }  
}
