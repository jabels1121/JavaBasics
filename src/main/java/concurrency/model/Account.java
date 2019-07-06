package concurrency.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {

    private String name;
    private Lock lock;
    private int sleepTime;

    public Account(String name, int sleepTime, int balance) {
        this.name = name;
        this.sleepTime = sleepTime;
        this.balance = balance;
        this.lock = new ReentrantLock();
    }

    public Account(String name, int balance) {
        this.name = name;
        this.balance = balance;
        this.lock = new ReentrantLock();
    }

    private int balance;

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    public void doSmth() {
        try {
            System.out.println(getName() + " начал делать что-то...\n....\n....\n....");
            Thread.sleep(getSleepTime());
            System.out.println(getName() + " закончил работу...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
