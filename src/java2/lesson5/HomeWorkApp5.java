package java2.lesson5;

import java.util.Arrays;

public class HomeWorkApp5 {
    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args) {
        System.out.printf ("Время выполнения операции %s мс%n",getOperationTime());
        try {
            System.out.printf ("Время выполнения операции в потоках %s мс%n",getThreadOperationTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static float getOperationTime() {
        float[] arr = new float[size];
        Arrays.fill(arr, 1);
        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + (float)(i / 5)) * Math.cos(0.2f + (float)(i / 5)) * Math.cos(0.4f + (float)(i / 2)));
        }
        return System.currentTimeMillis() - a;
    }

    public static float getThreadOperationTime() throws InterruptedException{
        float[] arr = new float[size];
        Arrays.fill(arr, 1);

        float[] a1 = new float[h];
        float[] a2 = new float[h];

        long a = System.currentTimeMillis();

        Thread thread1 = new Thread(() -> {
            System.arraycopy(arr, 0, a1, 0, h);
            for (int i = 0; i < a1.length; i++) {
                a1[i] = (float)(a1[i] * Math.sin(0.2f + (float)(i / 5)) * Math.cos(0.2f + (float)(i / 5)) * Math.cos(0.4f + (float)(i / 2)));
            }
        });

        Thread thread2 = new Thread(() -> {
            System.arraycopy(arr, h, a2, 0, h);
            for (int i = 0; i < a2.length; i++) {
                a2[i] = (float)(a2[i] * Math.sin(0.2f + (float)(i / 5)) * Math.cos(0.2f + (float)(i / 5)) * Math.cos(0.4f + (float)(i / 2)));
            }
        });

        thread1.start();
        thread2.start();

        for (Thread thread : Arrays.asList(thread1, thread2)) thread.join();

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        return System.currentTimeMillis() - a;
    }

}
