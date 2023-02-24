import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.JButton;

import java.awt.BorderLayout;

public class ToolBarNav extends JFrame {
    public ToolBarNav(String title) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);

        JToolBar toolbar = new JToolBar("Navigation");

        JButton left  = new JButton("<");
        toolbar.add(left);

        JButton up    = new JButton("^");
        toolbar.add(up);

        JButton right = new JButton(">");
        toolbar.add(right);
        
        add(toolbar, BorderLayout.PAGE_START);
        setVisible(true); 
    }
    public static void main(String[] args) {
        new ToolBarNav("Tool Bar Demo");
    }
}
