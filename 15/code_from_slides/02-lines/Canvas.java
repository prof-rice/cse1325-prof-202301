import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

class Line {
    Line(int x1, int y1, int x2, int y2) {
        this.x1 = x1; this.y1 = y1;
        this.x2 = x2; this.y2 = y2;
    }
    final int x1, y1, x2, y2;
}

public class Canvas extends JPanel {
    public Dimension getPreferredSize() {
        return new Dimension(250,200);                 // Suggest canvas size
    }                                                  //   as 250x200 pixels
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);                // Give JPanel a turn
        Graphics2D g = (Graphics2D) graphics.create(); // Clone and cast 
        Line[] lines = new Line[] {
            new Line( 50,150, 50, 75),                 // |
            new Line(200,150,200, 75),                 //     |
            new Line( 50, 75,200, 75),                 //  ---
            new Line( 50,150,200,150),                 //  ___
            new Line( 50, 75,200,150),                 //   \  
            new Line( 50,150,200, 75),                 //   /
            new Line( 50, 75,125, 25),                 //  /    
            new Line(200, 75,125, 25),                 //    \      
        };
        for(Line l : lines) 
            g.drawLine(l.x1, l.y1, l.x2, l.y2);        // Draw lines
    }  
}

