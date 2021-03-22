package com.urise.webapp;

public class MainDeadlock {
    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();
        deadLock(lock1, lock2);
        deadLock(lock2, lock1);
    }

    private static void deadLock(Object lock1, Object lock2) {
        new Thread(() -> {
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + ": Holding object" + lock1.toString());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
                System.out.println(Thread.currentThread().getName() + ": Waiting object" + lock1.toString());
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + ": Holding object" + lock2.toString());
                }
            }
        }).start();
    }
}
