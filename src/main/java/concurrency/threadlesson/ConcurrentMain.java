package concurrency.threadlesson;

import static concurrency.threadlesson.ColorScheme.GREEN;
import static concurrency.threadlesson.ColorScheme.MAGENTA;
import static concurrency.threadlesson.ColorScheme.YELLOW;

public class ConcurrentMain {


    public static void main(String[] args) {

        SimpleThread thr1 = new SimpleThread();
        SimpleThread thr2 = new SimpleThread();


        thr1.start();

        System.out.println("Hello from MAIN!");

        thr2.start();
        thr2.interrupt();

        Thread thr3 = new Thread(new SimpleRunner());
        thr3.start();

        new Thread(() -> System.out.println("Hello from lambda Runnable!!!")).start();
    }


}

class SimpleThread extends Thread {

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                System.out.println(YELLOW + "WARNING - " + currentThread().getName()
                        + " was interrupted");
            }
            System.out.println(MAGENTA + "INFO - " + currentThread().getName()
                    + " - " + i);
        }
    }

}

class SimpleRunner implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                System.out.println(YELLOW + "WARNING - " + Thread.currentThread().getName()
                        + " was interrupted");
            }
            System.out.println(GREEN + "INFO - Runnable - " + Thread.currentThread().getName()
                    + " - " + i);
        }
    }
}