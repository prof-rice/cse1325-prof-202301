package shapes;

// This simple data structure represents a line
//    from (x1, y1) to (x2, y2)

import java.awt.Graphics;

public class Line {
    public Line(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    
    final int x1; // package private so Shape has visibility
    final int y1;
    final int x2;
    final int y2;
    
    public void paintComponent(Graphics g) {
        g.drawLine(x1, y1, x2, y2);
    }

}
    
