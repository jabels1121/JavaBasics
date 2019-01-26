package collections.arrays;

import java.security.SecureRandom;

public class Dies {

    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        int[] frequency = new int[7];

        for (int i = 0; i < 1000; i++) {
            ++frequency[1 + random.nextInt(6)];
        }
        /*for (int i : frequency) {
            System.out.println(i);
        }*/
        multiDimArrayLauncher();
    }

    private static void multiDimArrayLauncher() {
        int[][] a = {{1, 2},
                {3, 4},
                {5, 6, 7, 8},
                {9, 10, 11}};

        for (int[] i : a) {
            for (int j = 0; j < i.length; j++) {
                System.out.print(i[j] + ", ");
            }
            System.out.println();
        }
        /*for (int i = 0; i < a.length; i++) {
            for (int j=0; j < a[i].length; j++) {
                System.out.print(a[i][j] + ", ");
            }
            System.out.println();
        }*/
    }

}
