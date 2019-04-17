package concurrency.locks;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static concurrency.threadlesson.ColorScheme.GREEN;
import static concurrency.threadlesson.ColorScheme.RED;

public class WaitingQueue {

    public static void main(String[] args) {
        int operators = 5;
        int customers = 21;
        SemaphoreServiceDesk serviceDesk = new SemaphoreServiceDesk(operators, customers);
        ExecutorService executorService = Executors.newCachedThreadPool();


        IntStream.range(0, customers)
                .forEach(client -> executorService.submit(() -> {
                    serviceDesk.connect();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(GREEN + "Number of connected customers: " + serviceDesk.getCustomersConnected());
                    System.out.println(RED + "Number of clients it a queue: " + serviceDesk.getCustomersWaiting());
                }));
        executorService.shutdown();
        try {
            executorService.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class SemaphoreServiceDesk {
        private AtomicInteger connectedCustomers = new AtomicInteger();
        private AtomicInteger customersQueued;

        private Semaphore semaphore;

        public SemaphoreServiceDesk(int operatorsNum, int customersNumber) {
            semaphore = new Semaphore(operatorsNum);
            customersQueued = new AtomicInteger(customersNumber);
        }

        public void connect() {
            Random r = new Random();
            try {
                semaphore.acquire();
                connectedCustomers.incrementAndGet();
                customersQueued.decrementAndGet();
                Thread.sleep(r.nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                disconnect();
            }
        }

        private int getCustomersConnected() {
            return connectedCustomers.get();
        }

        private int getCustomersWaiting() {
            return customersQueued.get();
        }

        private void disconnect() {
            semaphore.release();
            connectedCustomers.decrementAndGet();
        }
    }

}
