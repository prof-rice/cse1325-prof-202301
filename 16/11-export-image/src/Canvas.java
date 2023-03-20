import javax.swing.JPanel;

import javax.imageio.ImageIO;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Font;

import java.awt.image.BufferedImage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;

import shapes.Line;
import shapes.Shape;
import shapes.Polyline;
import shapes.Text;
import shapes.Image;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class Canvas extends JPanel {

    public enum PenMode {LINE, IMAGE, TEXT};

    public Canvas() {
        this(new File("untitled.paint"));
    }

    public Canvas(File filename) {
        super();

        this.filename = filename;
        this.imagename = null;
        isDirty = false;            // No data to start
        rubberBand = null;          // No rubber band yet
        color = Color.RED;          // Start with red color
        font = new Font(Font.SERIF, Font.BOLD, 24); 
        stroke = new BasicStroke(); // Default stroke
        penMode = PenMode.LINE;     // Draw lines
        
        // White background to our paintings by default
        setBackground(new java.awt.Color(255, 255, 255));
        
        // Call onMouseClicked when the mouse is clicked
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                onMouseClicked(event);
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent event) {
                onMouseMoved(event);
            }
        });
        
        clickInProgress = false;
    }
    
    // File I/O members
    public static String id() {return "Canvas";}
    public Canvas(BufferedReader br, String fileVersion) throws IOException {
        this();
        
        // The id is expected to have been read by the instancer
        color = new Color(Integer.parseInt(br.readLine()));
        stroke = new BasicStroke(Float.parseFloat(br.readLine()));
        int size = Integer.parseInt(br.readLine());
        while(size-- > 0) {
            String shapeID = br.readLine();
            if      (shapeID.equals(Polyline.id()))  shapes.add(new Polyline(br, fileVersion));
            else if (shapeID.equals(Text.id()))      shapes.add(new Text(br, fileVersion));
            else if (shapeID.equals(Image.id()))     shapes.add(new Image(br, fileVersion));
            else throw new IOException("Unknown shape ID: " + shapeID);

/*
            Fails because id() is not a "constant String expression"  :-(  
            switch(shapeID) {
                case Polyline.id() -> shapes.add(new Polyline(br, fileVersion));
                case Text.id()     -> shapes.add(new Text(br, fileVersion));
                case Image.id()    -> shapes.add(new Image(br, fileVersion));
                default: throw IOException("Unknown shape ID: " + shapeID);
            }
*/
        }
    }

    public void save(BufferedWriter bw) throws IOException {
        bw.write(id() + "\n");  // Identifies which object to instance
        bw.write("" + color.getRGB() + '\n');           // Save the current color...      
        bw.write("" + stroke.getLineWidth() + '\n');   // ... and width
        bw.write("" + shapes.size() + '\n');           // Save the number of shapes
        for(Shape shape : shapes) shape.save(bw);      // Tell each shape to save itself
        isDirty = false;  // Success! No dirty data remains
    }

    public void export(File file, String extension) throws IOException {
        java.awt.Rectangle r = getBounds();
        BufferedImage bufferedImage = 
            new BufferedImage(r.width, r.height, BufferedImage.TYPE_INT_ARGB);
        paint(bufferedImage.getGraphics());
        ImageIO.write(bufferedImage, extension, file);
    }

    // Methods
    public Dimension getPreferredSize() {
        return new Dimension(1024,768);
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

                if(penMode == PenMode.TEXT) {
                    shapes.add(new Text(text, x1, y1, color, font));
                    isDirty = true;  // unsaved data now present
                    repaint(); // request call to paintComponent
                } else if(penMode == PenMode.IMAGE) {
                    if(image != null) {
                        shapes.add(new Image(imagename, x1, y1, this, image));
                        isDirty = true;  // unsaved data now present
                        repaint(); // request call to paintComponent
                    }
                } else {
                    clickInProgress = true;
                }
            } else { // second click
                shapes.add(new Polyline(
                    new Line(x1, y1, 
                             event.getX() - xOffset,
                             event.getY() - yOffset,
                             color, stroke)
                ));
                repaint(); // request call to paintComponent
                isDirty = true;  // unsaved data now present
                rubberBand = null; // No more rubber banding
                clickInProgress = false;
            }
        }
    }

    public void onMouseMoved(MouseEvent event) {
        // Single click with primary button
        if(penMode == PenMode.TEXT) {
            rubberBand = new Text(text,
                             event.getX() - xOffset,
                             event.getY() - yOffset,
                             color, font);
            clickInProgress = false;
            repaint(); // request call to paintComponent
        } else if(penMode == PenMode.IMAGE) {
            if(image != null)
                rubberBand = new Image(imagename,
                             event.getX() - xOffset,
                             event.getY() - yOffset,
                             this, image);
            clickInProgress = false;
            repaint(); // request call to paintComponent
        } else if(clickInProgress) { // second click pending
            rubberBand = new Polyline(
                    new Line(x1, y1, 
                             event.getX() - xOffset,
                             event.getY() - yOffset,
                             color, stroke)
            );
            repaint(); // request call to paintComponent
        }
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics.create();
             

        // Move origin to center
        java.awt.Rectangle size = getBounds();
        xOffset = size.width / 2;
        yOffset = size.height / 2;
        g.translate(xOffset, yOffset);
        
        // Paint all shapes in the ArrayList
        for(Shape shape : shapes)
            shape.paintComponent(g);
            
        // Paint the temporary shape that "rubber bands" a pending line
        if(rubberBand != null) rubberBand.paintComponent(g);
    }
    
    // ////////////////////////////////////////////////////////////////
    // Attributes, Getters and Setters
    public File filename() {return filename;}
    public void filename(File filename) {this.filename = filename;}
    
    public void imagefile(File imageFile) throws IOException {
        image = null;
        image = ImageIO.read(imageFile);
        this.imagename = imageFile.getCanonicalPath();
    }
    
    public boolean isDirty() {
        return isDirty;
    }
    
    private PenMode penMode;      // Drawing lines, text, etc.
    public void penMode(PenMode newPenMode) {
        penMode = newPenMode;
        rubberBand = null;
        repaint();
    }
    
    public Color color() {return color;}
    public void color(Color newColor) {color = newColor;}
    
    public float width() {return stroke.getLineWidth();}
    public void width(float newWidth) {
        stroke = new BasicStroke(newWidth, 
            stroke.getEndCap(), stroke.getLineJoin(), stroke.getMiterLimit(), 
            stroke.getDashArray(), stroke.getDashPhase());
    }

    private int x1, y1; // Location of previous click
    private int xOffset, yOffset; // Amount of translation to center the origin
    private Color color; // Color for upcoming lines to be drawn
    private BasicStroke stroke; // width and dash pattern
    private boolean clickInProgress; // true after first click

    private String text;          //
    public void setText(String text) {
        this.text = text;
    }

    private Font font;
    public void setFont(Font font) {
        this.font = font;
    }
    public String getFontName() {
        return font.getFontName();
    }
    public int getFontStyle() {
        return font.getStyle();
    }
    public int getFontSize() {
        return font.getSize();
    }
    
    protected ArrayList<Shape> shapes = new ArrayList<>();
    
    private File filename;
    private String imagename;
    private BufferedImage image;
    protected Shape rubberBand;
    private boolean isDirty;
}
