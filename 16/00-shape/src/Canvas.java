import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Font;

import java.util.ArrayList;

import shapes.Shape;
import shapes.Polyline;
import shapes.Image;
import shapes.Text;

public class Canvas extends JPanel {
    private final static int border = 25;

    public Canvas() {
        Polyline spiral = new Polyline();
        for(int i=0; i<360; ++i) {
            int x = (int) (i * Math.cos(Math.toRadians(10*i)));
            int y = (int) (i * Math.sin(Math.toRadians(10*i)));
            //System.out.println("(" + x + "," + y + ")");
            spiral.addPoint(x,y);
        }
        shapes.add(spiral);
        shapes.add(new Image("resources/duck.jpg", -220, -320, this));
        
        Font font = new Font(Font.SERIF, Font.BOLD, 32);
        shapes.add(new Text("You're a Duck, Fiorello!", -220, 350, font));
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(1024,768);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        // Move origin to center
        Rectangle size = getBounds();
        g.translate(size.width / 2, size.height/2);
        
        // Paint all shapes in the ArrayList
        for(Shape shape : shapes)
            shape.paintComponent(g);
    }
    
    protected ArrayList<Shape> shapes = new ArrayList<>();
}
