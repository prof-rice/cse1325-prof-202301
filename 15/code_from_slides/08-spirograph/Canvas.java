import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;

import java.util.ArrayList;

public class Canvas extends JPanel {
    public Canvas() {
        this(800, 100, 2, 80, 0, Math.PI * 2, 2001, null, 1.5f, 2);  
    }
    public Canvas(int canvasSize, 
                  double innerRingRadius, double wheelRadius, double penRadius,
                  double tStart, double tEnd, int count, 
                  Color lineColor, float lineWidth, double scale) {
        setBackground(Color.WHITE); // Default is (often) light grey
        this.canvasSize = (double) canvasSize;
        this.lineColor = lineColor;
        this.lineWidth = lineWidth;
        Spirograph s = new Spirograph(innerRingRadius, wheelRadius, penRadius);
        s.setScale(scale);
        lines = s.draw(tStart, tEnd, count);
    }

    public Dimension getPreferredSize() {
        return new Dimension((int) canvasSize, (int) canvasSize);
    } 
    
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);                // Give JPanel a turn
        Graphics2D g = (Graphics2D) graphics.create(); // Clone and cast 

        Rectangle size = getBounds();
        g.translate(size.width / 2, size.height / 2);  // Set origin to canvas center
        
        // Scale the vector image to the current canvas size
        double scale = Math.min(size.width, size.height) / canvasSize;

        // Now scale and draw the lines
        g.setStroke(new BasicStroke(lineWidth));
        int colorIndex = 0;
        for(Line l : lines) {
            g.setColor((lineColor != null) ? lineColor : colors[colorIndex]);
            colorIndex = (colorIndex < 11) ? colorIndex + 1 : 0;
            g.drawLine((int) (l.x1 * scale), (int) (l.y1 * scale), 
                       (int) (l.x2 * scale), (int) (l.y2 * scale));
        }
    }
    
    private ArrayList<Line> lines;
    private double canvasSize;
    private Color lineColor;
    private float lineWidth;
    
    private Color[] colors = new Color[] {
        new Color(255,   0,   0), // Red
        new Color(255, 127,   0), // Orange
        new Color(255, 255,   0), // Yellow
        new Color(127, 255,   0), // Light Green / Chartreuse
        new Color(  0, 255,   0), // Green
        new Color(  0, 255, 127), // Spring Green / Turquoise
        new Color(  0, 255, 255), // Cyan
        new Color(  0, 127, 255), // Light Blue / Azure
        new Color(  0,   0, 255), // Blue
        new Color(127,   0, 255), // Violet
        new Color(255,   0, 255), // Magenta / Pink
        new Color(255,   0, 127), // Light Red / Rose
    };
}

