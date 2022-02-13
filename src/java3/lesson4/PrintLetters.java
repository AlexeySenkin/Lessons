package java3.lesson4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrintLetters {
    private final Object monitor = new Object();
    private int currentNum;
    private final int letterCount;
    private final int printCount;

    public PrintLetters(int printCount) {
        this.letterCount = Letter.values().length;
        this.printCount = printCount;
    }

    public void start() {
        ExecutorService executorService = Executors.newFixedThreadPool(letterCount);
        for (int i = 0; i < letterCount; i++) {
            int threadNum = i;
            executorService.execute(() -> {
                printLetter(letterCount, threadNum, printCount);
            });
        }
        executorService.shutdown();
    }

    public void printLetter(int threadCount, int threadNum, int printCount) {
        synchronized (monitor) {
            try {
                for (int i = 0; i < printCount; i++) {
                    while (currentNum != threadNum) {
                        monitor.wait();
                    }
                    System.out.print(Letter.values()[currentNum]);
                    if (currentNum < threadCount - 1) {
                        currentNum = currentNum + 1;
                    }
                    else {
                        currentNum = 0;
                    }
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
