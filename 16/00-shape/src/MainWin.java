import javax.swing.WindowConstants;
import javax.swing.JFrame;

public class MainWin extends JFrame {
    public static final String TITLE = "CSE1325 Paint";
    public static final String VERSION = "0.0.0";

    public MainWin(String title) {
        super(title);
        setSize(1024, 768);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        add(new Canvas());
        setVisible(true);
    }        
}
