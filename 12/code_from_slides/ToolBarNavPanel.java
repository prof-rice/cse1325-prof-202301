import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JButton;

import java.awt.BorderLayout;

public class ToolBarNavPanel extends JFrame {
    public ToolBarNavPanel(String title) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);

        JToolBar toolbar = new JToolBar("Navigation");

        JPanel panel = new JPanel();
        toolbar.add(panel);

        JButton left  = new JButton("<");
        panel.add(left);

        JButton up    = new JButton("^");
        panel.add(up);

        JButton right = new JButton(">");
        panel.add(right);
        
        add(toolbar, BorderLayout.PAGE_START);
        
        setVisible(true); 
    }
    public static void main(String[] args) {
        new ToolBarNavPanel("Tool Bar Demo");
    }
}
