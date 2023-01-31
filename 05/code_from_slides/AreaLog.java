import static Qlogger.Qlogger.log;

public class AreaLog {
    // calculate area of a rectangle
    public static int area(int length, int width) {  
        // length and width must be positive
        if (length <= 0 || width <= 0) {
            log("Invalid length or width!");
            return 0; // Default value
        }
        return length*width;
    }
    public static void main(String[] args) {
        if(args.length > 0 && args[0].equals("--log")) Qlogger.Qlogger.enable();
        int length =  14;
        int width  = -10;

        System.out.println("Area of " + length + " x " + width + " is " 
            + area(length, width));
    }   
}

