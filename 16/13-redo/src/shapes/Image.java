package shapes;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.imageio.ImageIO;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Image extends Shape {
    public Image(String imageFile, int x, int y, ImageObserver obs) {
        this.x = x;
        this.y = y;
        this.imageFile = imageFile;
        this.obs = obs;
        image = null;
        try {
            image = ImageIO.read(new File(imageFile));
        } catch (IOException e) {
            System.err.println("Failed to load " + imageFile + ":\n  " + e);
        }
    }
    
    public Image(String imageFile, int x, int y, ImageObserver obs, BufferedImage image) {
        this.imageFile = imageFile;
        this.x = x;
        this.y = y;
        this.obs = obs;
        this.image = image;
    }
    
    // File I/O
    public static String id() {return "Image";}
    public Image(BufferedReader br, String fileVersion) throws IOException {
        // The id is expected to have been read by the instancer
        this(br.readLine(), Integer.parseInt(br.readLine()),
                            Integer.parseInt(br.readLine()), null);
    }
    public void save(BufferedWriter bw) throws IOException {
        bw.write(id() + "\n");      // Identifies which object to instance
        bw.write(imageFile + "\n"); // The image will be reloaded on recreation
        bw.write("" + x + '\n');
        bw.write("" + y + '\n');
        // The observer will be null on recreation
    }

    
    public void paintComponent(Graphics graphics) {
        if(image != null)
            graphics.drawImage(image, x, y, obs);
    }
    private int x;
    private int y;
    private String imageFile;
    private BufferedImage image;
    private ImageObserver obs;
}
