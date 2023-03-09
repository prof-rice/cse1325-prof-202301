import javax.swing.JFrame;

public class Demo extends JFrame {
    public Demo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new Canvas());  // Put the drawing surface in the window
        pack();             // Auto-resize to Canvas.getPreferredSize()
        setVisible(true);   // Show the main window
    }
    public static void main(String[] args) {
        new Demo();
    }
}

