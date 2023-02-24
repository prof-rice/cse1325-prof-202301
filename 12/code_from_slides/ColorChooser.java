import java.awt.*;
import javax.swing.*;

public class ColorChooser extends JDialog {
    public ColorChooser(Frame frame, String title) {
        super(frame, title); // calling JDialog constructor
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close on X click

        GridBagLayout ccLayout = new GridBagLayout();
        ccLayout.columnWidths = new int[] {100, 50, 100};
        ccLayout.rowHeights   = new int[] {30, 30, 30, 30, 30, 50, 50};
        
        setLayout(ccLayout);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(250, 320);
        
        // Create and place the labels at the top of the lists
        JLabel available = new JLabel("Available");
        add(available, constraints(0, 0, 1, 1));     // 1x1 label at (0, 0)
        
        JLabel selected = new JLabel("Selected");
        add(selected, constraints(2, 0, 1, 1));      // 1x1 label at (2, 0)

        // Create and place the lists
        String availColors[] = {"Fuchsia", "Orange", "Pink", "Purple"};
        JList<String> availableList = new JList<>(availColors);
        availableList.setSelectedIndex(0);
        add(availableList, constraints(0, 1, 1, 4)); // 1x4 list at (0, 1)
        
        String selColors[] = {"Green", "Red", "Yellow"};
        JList<String> selectedList = new JList<>(selColors);
        selectedList.setSelectedIndex(2);
        add(selectedList, constraints(2, 1, 1, 4));  // 1x4 list at (2, 1)
        
        // Create and place the buttons between the lists
        JButton allRight = new JButton(">>");
        add(allRight, constraints(1, 1, 1, 1));      // 1x1 button at (1, 1)
        
        JButton right = new JButton(">");
        add(right, constraints(1, 2, 1, 1));         // 1x1 button at (1, 2)
        
        JButton left = new JButton("<");
        add(left, constraints(1, 3, 1, 1));          // 1x1 button at (1, 3)
        
        JButton allLeft = new JButton("<<");
        add(allLeft, constraints(1, 4, 1, 1));       // 1x1 button at (1, 4)

        // Create and place the action buttons at the bottom
        JButton save = new JButton("Save");
        //System.out.println(save.getPreferredSize());
        save.setPreferredSize(new Dimension(68, 70)); 
        GridBagConstraints gbc = constraints(0, 5, 1, 2); // 1x2 button at (0, 5)
        gbc.fill = GridBagConstraints.VERTICAL;  // Expand to fill vertical space
        add(save, gbc);
        
        JButton revert = new JButton("Revert");
        add(revert, constraints(2, 5, 1, 1));        // 1x1 button at (2, 5)
        
        JButton cancel = new JButton("Cancel");
        add(cancel, constraints(2, 6, 1, 1));        // 1x1 button at (2, 6)
    }
    private GridBagConstraints constraints(int col, int row, int cspan, int rspan) {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.gridy = row;
        constraint.gridx = col;
        constraint.gridheight = rspan;
        constraint.gridwidth = cspan;
        constraint.anchor = GridBagConstraints.CENTER;
        return constraint;
    }
}

