import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;

public class Canvas extends JPanel {
    public Dimension getPreferredSize() {
        return new Dimension(800,640);                 // Suggest canvas size
    }                                                  //   as 800x640 pixels
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);                // Give JPanel a turn
        Graphics2D g = (Graphics2D) graphics.create(); // Clone and cast
        
        int pointSize =  24;  // How big each letter should be
        int ySpacing  =  25;  // Use these variables for controlling layout
        int yGrouping = 120;  //   of the text on the canvas
        
        int[] fontStyle = new int[]
            {Font.PLAIN, Font.BOLD, Font.ITALIC, Font.BOLD|Font.ITALIC};
            
        String[] fontLabel = new String[]
            {"PLAIN", "BOLD", "ITALIC", "BOLD ITALIC"};
        
        for(int x = 0; x < 4; ++x) {
            
            g.setFont(new Font(Font.SERIF, fontStyle[x], pointSize));
            g.drawString(fontLabel[x] + " Serif", 25, ySpacing*(x+1));           

            g.setFont(new Font(Font.SANS_SERIF, fontStyle[x], pointSize));
            g.drawString(fontLabel[x] + " Sanserif", 25, ySpacing*(x+1) + yGrouping);           

            g.setFont(new Font(Font.DIALOG, fontStyle[x], pointSize));
            g.drawString(fontLabel[x] + " Dialog", 25, ySpacing*(x+1) + 2*yGrouping);           

            g.setFont(new Font(Font.DIALOG_INPUT, fontStyle[x], pointSize));
            g.drawString(fontLabel[x] + " Dialog_input", 25, ySpacing*(x+1) + 3*yGrouping);           

            g.setFont(new Font(Font.MONOSPACED, fontStyle[x], pointSize));
            g.drawString(fontLabel[x] + " Monospaced", 25, ySpacing*(x+1) + 4*yGrouping);           
        }
    }  
}

