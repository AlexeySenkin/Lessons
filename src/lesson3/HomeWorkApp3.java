package lesson3;

import java.util.Arrays;

public class HomeWorkApp3 {
    public static void main(String[] args) {
        System.out.println("1: инвертирование массива");
        int[] arr = { 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 };
        System.out.println("Исходный массив:");
        printArray(arr);
        System.out.println("Измененный массив:");
        printArray(invertArray(arr));
        System.out.println();

        System.out.println("2: создание пустого челочисленного массива и заполнение массива значениями 1 2 3 4 5 6 7 8 … 100");
        int[] arr2 = new int[100];
        System.out.println("Массив:");
        printArray(GetArray(arr2));
        System.out.println();

        System.out.println("3: Умножение на 2 элементов массива меньшие 6");
        int[] arr3 = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        System.out.println("Исходный массив:");
        printArray(arr3);
        System.out.println("Измененный массив:");
        printArray(mulArray(arr3));
        System.out.println();

        System.out.println("4: Заполнение диагоналей квадратного массива занчением 1");
        int[][] arr4 = new int[7][7];
        printArray(setDiagonalArray(arr4));

        System.out.println("5: Создание одномерного массива длиной len с элементоми равными initialValue");
        int len = 10;
        int initialValue = 7;
        printArray(createArray(len,initialValue));
        System.out.println();

        System.out.println("6: Создание одномерного массива и нахождение в нем макс и мин элемента");
        double[] arr5 = createRandomArray(len);
        System.out.println("Исходный массив:");
        printArray(arr5);
        System.out.println("Мин элемент = " + getMinArray(arr5));
        System.out.println("Макс элемент = " + getMaxArray(arr5));
        System.out.println();

        System.out.println("7: Проверка наличия места, в котором сумма левой и правой части массива равны");
        int[] arr6 = createIntRandomArray(len);
        System.out.println("Исходный массив:");
        printArray(arr6);
        if (checkBalance(arr6)) {
            System.out.println("В массиве есть место равенства");
        } else {
            System.out.println("В массиве нет места равенства");
        }


    }
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void printArray(int[][] arr) {
        for (int[] ints : arr) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    public static void printArray(double[] arr) {
        for (double i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void printArray(double[][] arr) {
        for (double[] ints : arr) {
            for (double anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    public static int[] invertArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                arr[i] = 0;
            } else {
                arr[i] = 1;
            }
        }
        return arr;
    }

    public static int[] GetArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    public static int[] mulArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] = arr[i] * 2;
            }
        }
        return arr;
    }

    public static int[][] setDiagonalArray(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (i == j || (i + j == arr[i].length - 1)) {
                    arr[i][j] = 1;
                }
            }
        }
        return arr;
   }
    public static int[] createArray(int len, int initialValue) {
        int[] result = new int[len];
        Arrays.fill(result, initialValue);
        return result;
    }

    public static double getMinArray(double[] arr) {
        double result = arr[0];
        for (double v : arr) {
            result = Math.min(result, v);
        }
        return result;
    }

    public static double getMaxArray(double[] arr) {
        double result = arr[0];
        for (double v : arr) {
            result = Math.max(result, v);
        }
        return result;
    }

    public static double[] createRandomArray(int len) {
        double[] result = new double[len];
        for (int i = 0; i < result.length; i++) {
            result[i] = Math.round(Math.random() * 100);
        }
        return result;
    }

    public static int[] createIntRandomArray(int len) {
        int[] result = new int[len];
        for (int i = 0; i < result.length; i++) {
            result[i] = Math.toIntExact(Math.round(Math.random() * 100));
        }
        return result;
    }

    public static boolean checkBalance(int[] arr) {
        int sumL = 0;
        int sumR = 0;
        int[][] arrSum = new int[arr.length][2];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                sumL += arr[j];
            }
            for (int k = i; k < arr.length; k++) {
                sumR += arr[k];
            }
            arrSum[i][0] = sumL;
            arrSum[i][1] = sumR;
            sumL = 0;
            sumR = 0;
        }
        System.out.println("Суммы левой и правой части массива относительно каждого элемента:");
        printArray(arrSum);
        for (int[] ints : arrSum) {
            if (ints[0] == ints[1]) {
                return true;
            }
        }
        return false;
    }

}
