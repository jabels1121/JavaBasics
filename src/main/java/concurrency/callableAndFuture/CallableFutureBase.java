package concurrency.callableAndFuture;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableFutureBase {

    public static void main(String[] args) {

        List<Future<Double>> futures = new ArrayList<>();
        ExecutorService executorService = Executors.newCachedThreadPool();


        for (int i = 0; i < 30; i++) {
            futures.add(executorService.submit(() -> {
                Timer timer = new Timer();
                Random r = new Random();
                timer.start = Instant.now();
                try {
                    int millis = r.nextInt(10000);
                    if (millis > 3000) throw new RuntimeException("Thread is running to long. Terminating...");
                    Thread.sleep(millis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timer.end = Instant.now();
                return timer.timeInSeconds();
            }));
        }

        executorService.shutdown();

        futures.parallelStream()
                .map(f -> {
                    try {
                        return f.get();
                    } catch (InterruptedException | ExecutionException e) {
                        System.out.println(e.getCause());
                        //e.printStackTrace();
                    }
                    return 0.0;
                }).forEach(t -> System.out.println("Thread execution time " + t));

    }
}

class Timer {

    public Instant start;
    public Instant end;

    public double timeInSeconds() {
        return Duration.between(start, end).toMillis() / 1000.0;
    }

}