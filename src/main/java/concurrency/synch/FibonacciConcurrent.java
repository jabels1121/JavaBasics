package concurrency.synch;

public class FibonacciConcurrent {

    private static int previous = 0;
    private static int current = 1;

    public static void main(String[] args) {

        Thread thr1 = new Thread(new FibonacciRunner());
        Thread thr2 = new Thread(new FibonacciRunner());

        thr1.start();
        thr2.start();
    }

    private static synchronized void calcNext() {
        int next = previous + current;
        previous = current;
        current = next;
        System.out.println(current);
    }

    private static class FibonacciRunner implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 15; i++) {
                calcNext();
            }
        }
    }

}
