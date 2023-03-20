import javax.imageio.ImageIO;

public class ShowImageIOInfo {
  static public void main(String args[]) throws Exception {
    System.out.println("All Available Image Reader Formats\n"
                     + "==================================");
    String names[] = ImageIO.getReaderFormatNames();
    for (int i = 0; i < names.length; ++i) {
      System.out.println("reader " + names[i]);
    }

    System.out.println("\nAll Available Image Writer Formats\n"
                     + "==================================");
    names = ImageIO.getWriterFormatNames();
    for (int i = 0; i < names.length; ++i) {
      System.out.println("writer " + names[i]);
    }
  }
}
