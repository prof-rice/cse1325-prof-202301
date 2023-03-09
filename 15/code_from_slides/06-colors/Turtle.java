import java.awt.Graphics;
import java.awt.Color;

import java.util.ArrayList;

public class Turtle {
    public enum Pen {UP, DOWN};
    
    public void pen(Pen newPen) {pen = newPen;}
    public void turn(double degrees) {
        angle += Math.toRadians(degrees);
        angle = Math.atan2(Math.sin(angle), Math.cos(angle)); // normalize
    }
    public void forward(double distance) {
        double x2 = x + distance*Math.cos(angle);
        double y2 = y + distance*Math.sin(angle);
        if(pen == Pen.DOWN) {
            Line line = new Line((int)x, (int)y, (int)x2, (int)y2);
            lines.add(line);
        }
        x = x2;
        y = y2;
    }
    
    public void paintComponent(Graphics g) {
        int colorIndex = 0;
        for(Line line : lines) {
            g.setColor(colors[colorIndex]);
            colorIndex = (colorIndex < 11) ? colorIndex + 1 : 0;
            line.paintComponent(g);
        }
    }
    
    private double x, y, angle;
    private Pen pen;
    private ArrayList<Line> lines = new ArrayList<>();
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
    
