package concurrency.producerconsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static concurrency.threadlesson.ColorScheme.GREEN;
import static concurrency.threadlesson.ColorScheme.RED;

public class ProducerConsumer {

    private static BlockingQueue<String> queue = new ArrayBlockingQueue<String>(5);

    public static void main(String[] args) {
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();


    }

    private static class Producer implements Runnable {
        String[] strings = {
                "1) wqrOIgYhB4bgVY1VLDev",
                "2) 9Z8SXz33l8Q5lcRGtZQk",
                "3) 6ThkpRw4XTC58kPXtA2u",
                "4) ze59H0XfMtZRz2XTjKBv",
                "5) MXg2KtTRhWOJHbCNi10q",
                "6) nUDHE433eB1angNpQXTs",
                "7) bk46ZwZdomzIAEkg6GNc",
                "8) DA5a2ZUxXfnSoGuMjO9D",
                "9) pQqu0Lp5OAryBGVZwraC",
                "10) sxoXV3gVrXNOeS1laaLD",
                "DONE"
        };

        @Override
        public void run() {
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void produce() throws InterruptedException {
            Random r = new Random();
            for (int i = 0; i< strings.length; i++) {
                queue.put(strings[i]);
                System.out.println(GREEN + "Producing " + strings[i] + ". Queue size is " + queue.size());
                Thread.sleep(r.nextInt(3000));
            }
        }
    }

    private static class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void consume() throws InterruptedException {
            Random r = new Random();
            while (true) {
                String message = queue.take();
                System.out.println(RED + "Consuming " + message + ". Que size is " + queue.size());
                if (!"DONE".equals(message)) {
                    Thread.sleep(r.nextInt(3000));
                } else  return;
            }
        }
    }

}
