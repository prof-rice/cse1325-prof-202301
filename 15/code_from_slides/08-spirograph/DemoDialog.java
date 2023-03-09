import javax.swing.JOptionPane;
import javax.swing.JLabel;

public class DemoDialog {
    public static void main(String[] args) {
        Canvas canvas = new Canvas();
        JLabel label = new JLabel(
            "<html><font size=+2>The above is a JPanel-based Canvas!</font></html>");
        JOptionPane.showMessageDialog(null, new Object[]{canvas, label}, 
            "JOptionPane Dialog", JOptionPane.PLAIN_MESSAGE);
    }

}
