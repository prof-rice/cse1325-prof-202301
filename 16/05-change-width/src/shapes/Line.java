package shapes;

// This simple data structure represents a line
//    from (x1, y1) to (x2, y2)

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;

public class Line {
    public Line(int x1, int y1, int x2, int y2, Color color, BasicStroke stroke) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.stroke = stroke;
    }
    
    final int x1; // package private so Shape has visibility
    final int y1;
    final int x2;
    final int y2;
    final Color color;
    final BasicStroke stroke;
    
    public void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(color);
        g.setStroke(stroke);
        g.drawLine(x1, y1, x2, y2);
    }

}
    
