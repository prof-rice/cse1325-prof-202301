import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PlotTemperatures extends JFrame {
    public PlotTemperatures (String filename) throws IOException {
        super("Temperatures");
        add(new Canvas(filename));
        setSize(365,200);
        setVisible(true);
    }
    public static void main(String[] args) throws IOException {
        if(args.length != 1) System.err.println("Usage: java PlotTemperatures file.txt");
        else new PlotTemperatures(args[0]);
    }
}

class Canvas extends JPanel {
    public Canvas(String filename) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while((line=br.readLine())!=null) 
                temps.add((int) Math.round(Double.parseDouble(line)));
        } catch(IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to read " + filename);
        }
    }
    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics.create();
        // Graphics g = graphics.create();
        
        g.setColor(Color.RED);
        double sum = temps.get(0);
        for(int i=1; i<temps.size(); ++i) {
            g.drawLine(i-1, 150 - temps.get(i-1), 
                       i,   150 - temps.get(i));
            sum += temps.get(i);
        }
        
        g.setColor(new Color(0x0064B1));
        g.drawString("Average Temp: " + (sum / temps.size()), 10, 120);
    }
    ArrayList<Integer> temps = new ArrayList<>();
}
    
