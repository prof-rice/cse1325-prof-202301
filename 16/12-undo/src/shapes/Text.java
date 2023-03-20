package shapes;

import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Text extends Shape {
    public Text(String text, int x, int y, Color color, Font font) {
        super();
        this.text = text;
        this.color = color;
        this.font = font;
        this.x = x;
        this.y = y;
    }
    
    // File I/O
    public static String id() {return "Text";}
    public Text(BufferedReader br, String fileVersion) throws IOException {
        // The id is expected to have been read by the instancer
        this.text = br.readLine();
        this.x = Integer.parseInt(br.readLine());
        this.y = Integer.parseInt(br.readLine());
        this.color = new Color(Integer.parseInt(br.readLine()));
        String fontName = br.readLine();
        this.font = (fontName.equals("null")) ? null
                  : new Font(fontName, Integer.parseInt(br.readLine()),
                                       Integer.parseInt(br.readLine()));
    }
    public void save(BufferedWriter bw) throws IOException {
        bw.write(id() + "\n");   // Identifies which object to instance
        bw.write(text + "\n");
        bw.write("" + x + '\n');
        bw.write("" + y + '\n');
        bw.write("" + color.getRGB() + '\n');        
        if(font != null) {
            bw.write(font.getFontName() + "\n");
            bw.write("" + font.getStyle() + '\n');
            bw.write("" + font.getSize() + '\n');
        } else {
            bw.write("null\n");
        }
    }

    // Drawing text
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(color);
        if(font != null) g.setFont(font);
        g.drawString(text, x, y);
    }
    
    private String text;
    private final Color color;
    private Font font;
    private int x, y;
}
