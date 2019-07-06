package concurrency;

import concurrency.model.Account;

import javax.naming.InsufficientResourcesException;
import java.util.concurrent.TimeUnit;

public class Operations {

    public static void main(String[] args) throws InsufficientResourcesException {
        final Account a = new Account("Ays account", 1500, 2000);
        final Account b = new Account("Bob account", 800, 1000);

        while (true) {

            new Thread(() -> {
                Thread.currentThread().setName(">>>THREAD-0");
                try {
                    checkTryLock(a, b);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(() -> {
                Thread.currentThread().setName("<<<THREAD-1");
                try {
                    checkTryLock(b, a);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();


            /*new Thread(() -> {
                try {
                    transfer(a, b, 500);
                } catch (InsufficientResourcesException | InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

            try {
                transfer(b, a, 300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }

    private static void transfer(Account acc1, Account acc2, int i) throws InsufficientResourcesException, InterruptedException {
        final int WAIT_SEC = 1;
        if (acc1.getLock().tryLock(WAIT_SEC, TimeUnit.SECONDS)) {
            String currentThreadName = Thread.currentThread().getName();
            System.out.println(currentThreadName + " lock the account - " + acc1.getName());
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                if (acc2.getLock().tryLock(WAIT_SEC, TimeUnit.SECONDS)) {
                    try {
                        System.out.println(currentThreadName + " lock the account - " + acc2.getName());
                        System.out.println("Transfer operation start...");
                        if (acc1.getBalance() < 0 && acc1.getBalance() < i) {
                            throw new InsufficientResourcesException();
                        }

                        acc1.withdraw(i);
                        System.out.println("From account - " + acc1.getName() + " withdraw " + i);

                        acc2.deposit(i);
                        System.out.println("To account - " + acc2.getName() + " deposit " + i);
                    } finally {
                        acc2.getLock().unlock();
                    }
                }
            } finally {
                acc1.getLock().unlock();
            }
        } else {
            System.out.println("Не удалось захватить объект");
        }

    }

    private static void checkTryLock(Account a, Account b) throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        if (a.getLock().tryLock(5000, TimeUnit.MILLISECONDS)) {
            try {
                System.out.println(threadName + " получил лок аккаунта - " + a.getName());
                a.doSmth();
                System.out.println(threadName + " пробует получить лок аккаунта - " + b.getName());
                if (b.getLock().tryLock(5000, TimeUnit.MILLISECONDS)) {
                    try {
                        System.out.println(threadName + " получил лок аккаунта - " + b.getName());
                        b.doSmth();
                    } finally {
                        b.getLock().unlock();
                    }
                } else {
                    System.out.println(threadName + " не удалось получить лок аккаунта - " + a.getName());
                }
            } finally {
                a.getLock().unlock();
            }
        } else {
            System.out.println(threadName + " не удалось получить лок аккаунта - " + a.getName());
        }

    }


}
