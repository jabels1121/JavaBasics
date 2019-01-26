package collections.arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ArrayLauncher {

    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] myArray = new int[6];
        int[] bArr = {0, 100, 200, 300, 400, 500};

        for (int i = 0; i < myArray.length; i++) {
            System.out.println("Enter next element");
            try {
                myArray[i] = Integer.parseInt(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        sort(myArray);
        for (int i : myArray) {
            System.out.println(i);
        }
    }

    public static int[] sort(int[] arr) {
        int counter = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
                counter++;
            }
        }
        System.out.println("----" + counter);
        return arr;
    }
    // inner cycle:
    // 10, 5, 16, 4  (j = 0; i = 0)
    // 5, 10, 16, 4  (j = 1; i = 0)
    // 5, 10, 4, 16

}
