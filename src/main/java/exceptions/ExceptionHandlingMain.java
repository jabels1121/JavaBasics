package exceptions;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionHandlingMain {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(System.in);
        PrintWriter writer = null;
        boolean continueLoop = true;

        do {
            try {
                System.out.println("Please enter numerator ");
                int numerator = scanner.nextInt();
                System.out.println("Please enter denominator ");
                int denominator = scanner.nextInt();
                System.out.println(divide(numerator, denominator));
                writer = new PrintWriter(new FileWriter("out.txt"));
                writer.println("Result = " + divide(numerator, denominator));
                writer.close();
                if (continueLoop) {
                    throw new RuntimeException("Runtime exception");
                }
                continueLoop = false;
            } catch (ArithmeticException | InputMismatchException e) {
                System.out.println("Exception: " + e);
                scanner.nextLine();
                System.out.println("Only integer non-zero values allowed");
            } catch (IOException e) {
                System.out.println("Unable to open file");
                e.printStackTrace();
            } finally {
                System.out.println("Finally block called");
                writer.close();
            }
        } while (continueLoop);
    }

    private static int divide(int numerator, int denominator) throws ArithmeticException, NullPointerException{
        return numerator / denominator;
    }

}
