package collections.autoboxing;

import java.util.Arrays;
import java.util.LinkedList;

public class CollectionMain {

    public static void main(String[] args) {
        String[] colors = {"Yellow", "Green", "Blue"};

        LinkedList<String> listColors = new LinkedList<>(Arrays.asList(colors));
        listColors.add("Black");

        colors = listColors.toArray(new String[listColors.size() - 1]);

        for (String s : colors) {
            System.out.println(s);
        }

    }

}
