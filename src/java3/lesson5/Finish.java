package java3.lesson5;

import java.util.concurrent.CyclicBarrier;

public class Finish extends Stage {

    private final CyclicBarrier waitForFinish;

    private static String winnerName = "";

    public Finish(CyclicBarrier waitForFinish) {
        this.length = 0;
        this.description = "Финиш";

        this.waitForFinish = waitForFinish;

    }
    @Override
    public void go(Car c) {
        try {
            if (winnerName.isBlank()) {
                winnerName = c.getName();
                System.err.println(c.getName() + " победил в гонке!!!");
                //System.out.println(c.getName() + " победил в гонке!!!");
            } else {
                System.out.println(c.getName() + " финиширует ");
            }
            waitForFinish.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
