package concurrency.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import static concurrency.threadlesson.ColorScheme.RED;

public class Launcher {

    private static final int POOL_SIZE = 2;

    public static void main(String[] args) throws InterruptedException {
        boolean isDeamon = true;
        System.out.println(RED + "Starting main thread");
        GCDRunnable r = new GCDRunnable();

        //runInOneThread(r);
        runWithExecutors(r, isDeamon);
        System.out.println(RED + "Leaving the main thread");
    }

    private static void runInOneThread(GCDRunnable r) {
        Thread th = new Thread(r);
        th.setDaemon(true);
        th.start();
        th.interrupt();
    }

    private static void runWithExecutors(GCDRunnable r, boolean isDaemon) throws InterruptedException {

        ThreadFactory factory = r1 -> {
            Thread thread = new Thread(r1);
            if (isDaemon) {
                thread.setDaemon(true);
            }
            return thread;
        };

        ExecutorService executorService =
                Executors.newFixedThreadPool(POOL_SIZE);
        for (int i = 0; i < 5; i++) {
            executorService.execute(r);
        }
        executorService.shutdown();

        executorService.awaitTermination(30, TimeUnit.SECONDS);
    }

}
