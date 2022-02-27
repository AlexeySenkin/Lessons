package java3.lesson5;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class HomeWorkApp5 {

    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(CARS_COUNT);

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Runnable barrierStartAction = () -> System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        Runnable barrierFinishAction = () -> System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");

        Race race = new Race(new Maintenance(new CyclicBarrier(CARS_COUNT, barrierStartAction)),
                            new Road(60),
                            new Tunnel(80, new Semaphore(Math.max(CARS_COUNT / 2, 1), true)),
                            new Road(40),
                            new Finish(new CyclicBarrier(CARS_COUNT, barrierFinishAction)));

        for (int i = 0; i < CARS_COUNT; i++) {
            executorService.execute(new Car(race, 20 + (int) (Math.random() * 10)));
        }
        executorService.shutdown();

    }


}