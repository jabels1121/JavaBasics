package concurrency.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static concurrency.threadlesson.ColorScheme.BLUE;
import static concurrency.threadlesson.ColorScheme.RED;

public class SynchronizedBuffer {


    private static final Lock monitor = new ReentrantLock();
    private static final Condition canRead = monitor.newCondition();
    private static final Condition canWrite = monitor.newCondition();
    private static int buffer = 0;
    private static boolean isEmpty = true;

    public static void main(String[] args) {

        new Thread(SynchronizedBuffer::blockingWrite).start();
        new Thread(SynchronizedBuffer::blockingRead).start();

    }

    private static void blockingWrite() {
        for (int i = 0; i < 15; i++) {
            monitor.lock();
            try {
                buffer++;
                isEmpty = false;
                System.out.println(RED + "Writer produced: " + buffer);
                canRead.signal();
                try {
                    canWrite.await(5, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                monitor.unlock();
            }
        }
    }

    private static void blockingRead() {
        for (int i = 0; i < 15; i++) {
            monitor.lock();
            try {
                int readValue = buffer;
                isEmpty = true;
                System.out.println(BLUE + "Reader reads from buffer: " + readValue);
                canWrite.signal();
                try {
                    canRead.await(5, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                monitor.unlock();
            }
        }
    }
}
