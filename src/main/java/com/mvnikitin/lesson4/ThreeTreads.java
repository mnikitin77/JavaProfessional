package com.mvnikitin.lesson4;

import static java.lang.Thread.*;

public class ThreeTreads {

    private final Object mon = new Object();
    private volatile char currentLetter = 'A';

    public static void main(String[] args) {

        ThreeTreads app = new ThreeTreads();

        Thread t1 = new Thread(() -> {
            app.printLetter('A', 'B');
        });

        Thread t2 = new Thread(() -> {
            app.printLetter('B', 'C');
        });

        Thread t3 = new Thread(() -> {
            app.printLetter('C', 'A');
        });

        t3.start();
        t2.start();
        t1.start();
        //t2.start();
        //t3.start();
    }

    private void printLetter(char current, char next) {
        System.out.println(Thread.currentThread().getName() + " before synchronized()");
        synchronized (mon) {
            System.out.println(Thread.currentThread().getName() + " after synchronized()");
            try {
                for (int i = 0 ; i < 3; i++) {
                    while (currentLetter != current) {
                        System.out.println(Thread.currentThread().getName() + " wait");
                        mon.wait();
                    }
                    System.out.println(currentLetter);
                    currentLetter = next ;
                    System.out.println(Thread.currentThread().getName() + " notify all");
                    mon.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
