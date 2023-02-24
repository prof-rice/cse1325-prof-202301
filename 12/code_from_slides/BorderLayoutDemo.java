import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;

public class BorderLayoutDemo extends JFrame {
    public BorderLayoutDemo() {
    
        // NOTE: JFrame uses BorderLayout manager by default
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 160);
        
        // Create and configure 5 labels as content
        // The second JLabel parameter controls alignment of the label
        // The second add parameter selects the BorderLayout region
        JLabel pageStartLabel = new JLabel("PAGE_START", JLabel.CENTER);
        pageStartLabel.setOpaque(true);
        pageStartLabel.setBackground(Color.GREEN);
        add(pageStartLabel, BorderLayout.PAGE_START);

        JLabel lineStartLabel = new JLabel("LINE_START", JLabel.CENTER);
        lineStartLabel.setOpaque(true);
        lineStartLabel.setBackground(Color.RED);
        add(lineStartLabel,  BorderLayout.LINE_START); 

        JLabel centerLabel = new JLabel("CENTER", JLabel.CENTER);
        centerLabel.setOpaque(true);
        centerLabel.setBackground(Color.YELLOW);
        add(centerLabel, BorderLayout.CENTER);

        JLabel lineEndLabel = new JLabel("LINE_END", JLabel.CENTER);
        lineEndLabel.setOpaque(true);
        lineEndLabel.setBackground(Color.RED);
        add(lineEndLabel,  BorderLayout.LINE_END);

        JLabel pageEndLabel = new JLabel("PAGE_END", JLabel.CENTER);
        pageEndLabel.setOpaque(true);
        pageEndLabel.setBackground(Color.GREEN);
        add(pageEndLabel, BorderLayout.PAGE_END);

        setVisible(true);
    } 
    public static void main( String[] args ) {
        new BorderLayoutDemo();
    }
}
