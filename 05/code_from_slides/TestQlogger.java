import static Qlogger.Qlogger.log;

public class TestQlogger {
    public static void main(String[] args) {
        if(args.length > 0 && args[0].equals("--log")) Qlogger.Qlogger.enable();
        else System.out.println("Add '--log' argument to see logged messages");
        
        log("This is a logged message");
        System.out.println("This is a normal message");
   }
}
