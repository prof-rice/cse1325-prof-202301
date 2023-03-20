package shapes;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.BasicStroke;

import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Polyline extends Shape {
    public Polyline() {}
    public Polyline(Line line) {
        addLine(line);
    }

    // File I/O
    public static String id() {return "Polyline";}  // The class to instance

    public Polyline(BufferedReader br, String fileVersion) throws IOException {
        // The id is expected to have been read by the instancer
        int size = Integer.parseInt(br.readLine());
        while(size-- > 0) {
            String lineID = br.readLine();
            if(!lineID.equals(Line.id()))
                throw new IOException("Missing Line ID: " + lineID);
            lines.add(new Line(br, fileVersion));
        }
    }

    @Override
    public void save(BufferedWriter bw) throws IOException {
        bw.write(id() + "\n");  // Identifies which class to instance
        bw.write("" + lines.size() + '\n'); 
        for(Line line : lines) line.save(bw);
    }

    public void addLine(Line line) {
        lines.add(line);
    }
    public void addPoint(int x, int y, Color color, BasicStroke stroke) {
        if(lines.isEmpty()) {
            lines.add(new Line(x, y, x, y, color, stroke));
        } else {
            Line lastLine = lines.get(lines.size()-1);
            Line line = new Line(lastLine.x2, lastLine.y2, x, y, color, stroke);
            lines.add(line);
        }
    }   
    
    public void paintComponent(Graphics g) {
        for(Line line : lines) line.paintComponent(g);
    }
    
    protected ArrayList<Line> lines = new ArrayList<>();
}
