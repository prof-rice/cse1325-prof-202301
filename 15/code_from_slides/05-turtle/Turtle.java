import java.awt.Graphics;
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
        for(Line line : lines) line.paintComponent(g);
    }
    
    private double x, y, angle;
    private Pen pen;
    private ArrayList<Line> lines = new ArrayList<>();
}
    
