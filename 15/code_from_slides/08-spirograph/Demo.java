import javax.swing.JFrame;

import java.awt.Color;

public class Demo extends JFrame {
    public Demo(Canvas canvas) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(canvas);        // Put the drawing surface in the window
        pack();             // Auto-resize to Canvas.getPreferredSize()
        setVisible(true);   // Show the main window
    }
    public static void main(String[] args) {    
        int    canvasSize       =  800;
        int    numLines         = 2001; 
        float  lineWidth        =    1.5f;
        Color  color            =   null;
        double scale            =    2.0;
        double innerRingRadius  =  100.0;
        double wheelRadius      =    2.0;
        double penRadius        =   80.0;
        double tStart           =    0.0;
        double tEnd             =    2.0 * Math.PI;

        String help = "usage: java Demo [options]\n"
                    + "  -h          print this text\n"
                    + "  -w  int     create window as w x w pixels:  " + canvasSize + "\n"
                    + "  -n  int     number of lines to generate:    " + numLines + "\n"
                    + "  -l  float   line widths:                    " + lineWidth + "\n"
                    + "  -c  double  line color (eg ff007f):         " 
                                        + ((color == null) ? "rainbow" : color.getRGB()) + "\n"
                    + "  -s  double  multiple image by this much:    " + scale + "\n"
                    + "  -R  double  inner radius of the ring:       " + innerRingRadius + "\n"
                    + "  -r  double  radius of the wheel:            " + wheelRadius + "\n"
                    + "  -p  double  radius of the pen in the wheel: " + penRadius + "\n"
                    + "  -t1 double  starting rotation in radians:   " + tStart + "\n"
                    + "  -t2 double  ending rotation in radians:     " + tEnd + "\n"
                    + "  -pi         multiply -t1 and -t2 by pi";
            
        boolean multiplyByPi = false;
        
        try {
            int index = 0;
            while(index < args.length) {
                switch(args[index++]) {
                    case "-h"  -> {System.out.println(help); System.exit(0);}
                    case "-w"  -> canvasSize      = Integer.parseInt(args[index++]);
                    case "-n"  -> numLines        = Integer.parseInt(args[index++]);
                    case "-l"  -> lineWidth       = Float.parseFloat(args[index++]);                    
                    case "-c"  -> color           = new Color(Integer.parseInt(args[index++], 16)); 
                    case "-s"  -> scale           = Double.parseDouble(args[index++]);
                    case "-R"  -> innerRingRadius = Double.parseDouble(args[index++]);
                    case "-r"  -> wheelRadius     = Double.parseDouble(args[index++]);
                    case "-p"  -> penRadius       = Double.parseDouble(args[index++]);
                    case "-t1" -> tStart          = Double.parseDouble(args[index++]);
                    case "-t2" -> tEnd            = Double.parseDouble(args[index++]);
                    case "-pi" -> multiplyByPi    = true;
                    default -> throw new IllegalArgumentException("Unknown argument: " + args[index-1]);
                }
            }
            if(multiplyByPi) {
                tStart *= Math.PI;
                tEnd   *= Math.PI;
            }
            new Demo(new Canvas(canvasSize,
                                innerRingRadius, wheelRadius, penRadius,
                                tStart, tEnd, numLines,
                                color, lineWidth, scale));
        } catch(IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }
    }
}
