package shapes;

// This simple data structure represents a line
//    from (x1, y1) to (x2, y2)

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Line {
    public Line(int x1, int y1, int x2, int y2, Color color, BasicStroke stroke) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.stroke = stroke;
    }

    // File I/O members
    public static String id() {return "Line";}
    public Line(BufferedReader br, String fileVersion) throws IOException {
        x1 = Integer.parseInt(br.readLine());
        y1 = Integer.parseInt(br.readLine());
        x2 = Integer.parseInt(br.readLine());
        y2 = Integer.parseInt(br.readLine());
        color = new Color(Integer.parseInt(br.readLine()));
        stroke = new BasicStroke(Float.parseFloat(br.readLine()));
    }
    public void save(BufferedWriter bw) throws IOException {
        bw.write(id() + "\n");  // Identifies which object to instance
        bw.write("" + x1 + '\n');        
        bw.write("" + y1 + '\n');        
        bw.write("" + x2 + '\n');        
        bw.write("" + y2 + '\n');        
        bw.write("" + color.getRGB() + '\n');        
        bw.write("" + stroke.getLineWidth() + '\n');        
    }

    // Attributes    
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
    
