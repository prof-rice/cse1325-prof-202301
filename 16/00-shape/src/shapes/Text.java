package shapes;

import java.awt.Graphics;
import java.awt.Font;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Text extends Shape {
    public Text(String text, int x, int y, Font font) {
        super();
        this.text = text;
        this.font = font;
        this.x = x;
        this.y = y;
    }
    
    // Drawing text
    @Override
    public void paintComponent(Graphics g) {
        if(font != null) g.setFont(font);
        g.drawString(text, x, y);
    }
    
    private String text;
    private Font font;
    private int x, y;
}
