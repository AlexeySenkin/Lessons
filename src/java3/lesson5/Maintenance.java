package java3.lesson5;

import java.util.concurrent.CyclicBarrier;

public class Maintenance extends Stage {

    private final CyclicBarrier waitForMaintenance;

    public Maintenance(CyclicBarrier waitForMaintenance) {
        this.length = 0;
        this.description = "Техническое обслуживание ";
        this.waitForMaintenance = waitForMaintenance;
    }
    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(c.getName() + " готов");
            waitForMaintenance.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
