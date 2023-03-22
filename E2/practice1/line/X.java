import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class X extends JFrame {
    public X() {
        super("Free Response 4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(100,140);
        Canvas canvas = new Canvas();
        add(canvas); //, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        new X();
    }
}

class Canvas extends JPanel {

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics.create();     

        // Draw Shape
        g.setColor(Color.RED);
        g.drawLine(0,0,100,100);
        g.drawLine(0,100,100,0);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
        g.drawString("Exam 2",30,20);
    }  
}

