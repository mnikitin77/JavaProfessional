package com.mvnikitin.lesson5;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    private Semaphore tunnelAvailability;

    public Tunnel(int capacity) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        tunnelAvailability = new Semaphore(capacity);
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                tunnelAvailability.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                tunnelAvailability.release();
                System.out.println(c.getName() + " закончил этап: " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
