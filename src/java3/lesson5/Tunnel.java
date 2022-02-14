package java3.lesson5;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private final Semaphore maxTraffic;

    public Tunnel(int length, Semaphore maxTraffic) {
        this.length = length;
        this.description = "Тоннель " + length + " метров";
        this.maxTraffic = maxTraffic;
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                maxTraffic.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                maxTraffic.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}