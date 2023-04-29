import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Arrays;

public class Tables {
    public static String makeTable(ListIterator li) {
        StringBuffer result = new StringBuffer();
        int index = 0;
        while(li.hasNext()) {
            result.append(String.format("%-20s", li.next()));
            if(++index % 4 == 0) result.append("\n");
        }
        return result.toString();
    }
    public static void main(String[] args) {
        ArrayList<Double> doubles = new ArrayList<>(
            Arrays.asList(9.89220810671493,   1.4499235323765458, 1.108998788636839, 6.770956913460421,
                          1.3848713474470375, 6.764943358591444,  0.10069782000538985));
        System.out.println(makeTable(doubles.listIterator()));
    }
}
