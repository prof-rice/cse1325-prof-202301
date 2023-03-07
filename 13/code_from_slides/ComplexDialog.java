// This dialog demonstrates a unified JOptionPane confirm dialog 
//   with an expanding list shown in a scrollable JLabel.

import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;

import java.awt.Dimension;

import java.util.ArrayList;

public class ComplexDialog {
    public static void showDialog() {
        try { 
            JLabel lname = new JLabel("Name");
            JTextField tname = new JTextField();
            
            JLabel lage = new JLabel("<html><br>Age</html>");
            SpinnerNumberModel snm = new SpinnerNumberModel(0, 0, 120, 1);
            JSpinner sage = new JSpinner(snm);
            
            ArrayList<String> hobbies = new ArrayList<>();
            JLabel lhobbies = new JLabel("<html><br>Hobbies</html>");
            JTextField addHobby = new JTextField("Add Hobbies Here");
 
            JLabel showHobbies = new JLabel();
            JScrollPane scrollHobbies = new JScrollPane(showHobbies);
            scrollHobbies.setPreferredSize(new Dimension(20, 100));
            
            addHobby.addActionListener(event -> {
                hobbies.add(addHobby.getText());
                String ul = "<html>\n";
                for(String s : hobbies) ul += "<p>" + s + "</p>\n";
                ul += "</html>";
                showHobbies.setText(ul);
                System.out.println(ul);
                addHobby.setText("");
            });
            
            Object[] objects = new Object[]{
                lname, tname,
                lage,  sage,
                lhobbies, addHobby,
                scrollHobbies};
                
            int n = JOptionPane.showConfirmDialog(null, objects,
                        "Define Person", JOptionPane.OK_CANCEL_OPTION);
            if (n == JOptionPane.OK_OPTION) {
                System.out.println(tname.getText() 
                    + " is age " + sage.getValue()
                    + " and has the following hobbies:");
                for(String s : hobbies) System.out.println("  " + s);
            }
        //} catch(NullPointerException e) {
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error in ComplexDialog", JOptionPane.ERROR_MESSAGE);
        }    
    }
    
    public static void main(String[] args) {
        showDialog();
    }

}
