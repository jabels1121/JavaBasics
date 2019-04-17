package concurrency.executors;

import java.util.Random;

import static concurrency.threadlesson.ColorScheme.BLUE;
import static concurrency.threadlesson.ColorScheme.GREEN;

public class GCDRunnable extends Random implements Runnable {

    @Override
    public void run() {
        String threadDescription = Thread.currentThread().getName();

        System.out.println(BLUE + "Starting " + threadDescription);
        for (int i = 0; i < 10000000; i++) {
            int a = nextInt();
            int b = nextInt();
            if (!Thread.interrupted()) {
                if (i % 10000 == 0) {
                    int gcd = computeGCD(a, b);
                    if (gcd > 5) {
                        System.out.println(GREEN + "Running in " +
                                threadDescription + ". The GCD of " + a + " and " + b + " is " + gcd);
                    }
                }
            }
            else System.out.println(BLUE+"Thread was interrupted!");
        }
        System.out.println(BLUE + "Leaving from " + threadDescription + ".");
    }

    private int computeGCD(int num1, int num2) {
        if (num2 == 0) {
            return num1;
        } else {
            return computeGCD(num2, num1 % num2);
        }
    }
}
