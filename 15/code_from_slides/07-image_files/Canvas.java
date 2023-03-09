import javax.swing.JPanel;
import javax.imageio.ImageIO;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

public class Canvas extends JPanel {
    public Canvas() {
        setBackground(Color.BLACK);
        String imageFile = "squirrel.jpg";
        try {
            image = ImageIO.read(new File(imageFile));
        } catch (IOException e) {
            throw new RuntimeException("Unable to load image from " + imageFile, e);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1024, 800);
    }
    
    /* Also available to control the layout
    
    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }
    @Override
    public Dimension getMaximumSize() {
        return getPreferredSize();
    }
    */        

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics); 
        Graphics2D g = (Graphics2D) graphics.create();
        
        int x = (getBounds().width  - image.getWidth())  / 2;
        int y = (getBounds().height - image.getHeight()) / 2;
        
        g.drawImage(image, x, y, this);
        
        String credits = "Image is in the public domain";
        String url = "https://www.hippopx.com/en/squirrel-curious-gutter-rodent-nager-cute-funny-248041";
        g.setColor(Color.WHITE);

        g.drawString(credits, 
            getBounds().width  - g.getFontMetrics().stringWidth(credits) - 10,
            getBounds().height - 2*g.getFontMetrics().getHeight() - 10);
        g.drawString(url, 
            getBounds().width  - g.getFontMetrics().stringWidth(url) - 10,
            getBounds().height - g.getFontMetrics().getHeight() - 10);
   }
    
    private BufferedImage image;
    
}
