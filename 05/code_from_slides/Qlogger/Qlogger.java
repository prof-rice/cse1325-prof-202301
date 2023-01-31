package Qlogger;
public class Qlogger {
    public static void log(String s) {if(enabled) System.err.println(s);}
    public static void enable() {enabled = true;}
    public static void disable() {enabled = false;}
    private static boolean enabled = false;
}
