package shapes;

import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

public class Text extends Shape {
    public Text(String text, int x, int y, Color color, Font font) {
        super();
        this.text = text;
        this.color = color;
        this.font = font;
        this.x = x;
        this.y = y;
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
