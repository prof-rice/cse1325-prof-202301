import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Canvas extends JPanel {
    public Canvas() {
        turtle = new Turtle();
        turtle.pen(Turtle.Pen.DOWN);
        
        for(int i=1; i<300; i += 1) {
            turtle.forward(i); 
            turtle.turn(61);
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(750,600);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);                // Give JPanel a turn
        Graphics2D g = (Graphics2D) graphics.create(); // Clone and cast 
        Rectangle size = getBounds();                  // get canvas size
        g.translate(size.width / 2, size.height/2);    // move (0,0) to center
        turtle.paintComponent(g);                      // Paint the turtle 
    }
    
    private Turtle turtle;
}
