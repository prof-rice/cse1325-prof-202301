import java.util.ArrayList;

public class Spirograph {
    public Spirograph(double innerRingRadius, double wheelRadius, double penRadius) {
        this.R = innerRingRadius;
        this.r = wheelRadius;
        this.p = penRadius;
    }
    
    public ArrayList<Line> draw(double tStart, double tEnd, int count) {
        double t = tStart;
        double delta = (tStart - tEnd) / (double) count;
        ArrayList<Line> lines = new ArrayList<>();
        int xLast = (int) (fx(t) * scale);
        int yLast = (int) (fy(t) * scale);
        while(count-- > 0) {
            int xNew = (int) (fx(t) * scale);
            int yNew = (int) (fy(t) * scale);
            lines.add(new Line(xLast, yLast, xNew, yNew));
            xLast = xNew;
            yLast = yNew;
            t += delta;
        }
        return lines;
    }
    
    public void setScale(double scale) {
        this.scale = scale;
    }
    
    // Spirograph equations courtesy of Linux Gazette, 
    //     https://linuxgazette.net/133/luana.html
    //   t is the angle (radians) through which the wheel 
    // has rotated within the ring

    private double fx(double t) {
        return (double)((R-r)*Math.cos(t)+p*Math.cos((R-r)*t/r));
    }
    
    private double fy(double t) {
        return (double)((R-r)*Math.sin(t)+p*Math.sin((R-r)*t/r));
    }

    private double R; // Inner radius of the ring
    private double r; // Radius of the wheel
    private double p; // Radius of the pen in the wheel

    private double scale = 1.0; // How big or small to make the drawing
}

