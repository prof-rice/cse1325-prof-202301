package shapes;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public class Image extends Shape {
    public Image(String imageFile, int x, int y, ImageObserver obs) {
        this.x = x;
        this.y = y;
        this.obs = obs;
        image = null;
        try {
            image = ImageIO.read(new File(imageFile));
        } catch (IOException e) {
            System.err.println("Failed to load " + imageFile + ":\n  " + e);
        }
    }
    public void paintComponent(Graphics graphics) {
        if(image != null)
            graphics.drawImage(image, x, y, obs);
    }
    private int x;
    private int y;
    private BufferedImage image;
    private ImageObserver obs;
}
