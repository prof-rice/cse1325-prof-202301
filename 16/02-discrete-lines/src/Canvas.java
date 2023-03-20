import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;

import shapes.Shape;
import shapes.Polyline;
import shapes.Line;

public class Canvas extends JPanel {
    private final static int border = 25;

    public Canvas() {
        super();
        
        isDirty = false; // No data to start
        
        // White background to our paintings by default
        setBackground(new java.awt.Color(255, 255, 255));
        
        // Call onMouseClicked when the mouse is clicked
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                onMouseClicked(event);
            }
        });
        clickInProgress = false;
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(1024,768);
    }
    
    public void save() {
        isDirty = false;
    }

    // /////////////////////////////////////////////////////////////////
    // Listeners
    
    public void onMouseClicked(MouseEvent event) {
        // Single click with primary button
        if ((event.getButton() == MouseEvent.BUTTON1) &&
            (event.getClickCount() == 1)) {
            if(!clickInProgress) { // first click
                x1 = event.getX() - xOffset;
                y1 = event.getY() - yOffset;
                clickInProgress = true;
            } else { // second click
                shapes.add(new Polyline(
                    new Line(x1, y1, 
                             event.getX() - xOffset,
                             event.getY() - yOffset)
                ));
                repaint(); // request call to paintComponent
                isDirty = true;  // unsaved data now present
                clickInProgress = false;
            }
        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics.create();
             

        // Move origin to center
        java.awt.Rectangle size = getBounds();
        xOffset = size.width / 2;
        yOffset = size.height/2;
        g.translate(xOffset, yOffset);
        
        // Paint all shapes in the ArrayList
        for(Shape shape : shapes)
            shape.paintComponent(g);
    }
    
    // ////////////////////////////////////////////////////////////////
    // Attributes, Getters and Setters
    public boolean isDirty() {
        return isDirty;
    }

    private int x1, y1; // Location of previous click
    private int xOffset, yOffset; // Amount of translation to center the origin
    private boolean clickInProgress; // true after first click
     
    protected ArrayList<Shape> shapes = new ArrayList<>();
    private boolean isDirty;
}
