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
        System.out.println("Массив:");
        printArray(GetArray());
        System.out.println();

        System.out.println("3: Умножение на 2 элементов массива меньшие 6");
        int[] arr3 = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        System.out.println("Исходный массив:");
        printArray(arr3);
        System.out.println("Измененный массив:");
        printArray(mulArray(arr3));
        System.out.println();

        System.out.println("4: Заполнение диагоналей квадратного массива занчением 1");
        int size = 7;
        printArray(setDiagonalArray(size, size));
        System.out.println();

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
        int[] arr6 = {1,1,1,1,1,1,1,1,1,1};
        System.out.println("Исходный массив:");
        printArray(arr6);
        if (checkBalance(arr6)) {
            System.out.println("В массиве есть место равенства");
        } else {
            System.out.println("В массиве нет места равенства");
        }
        System.out.println();

        System.out.println("8: Смещение всех элементов массива на n позиций");
        int value;
        int[] arr7 = {1,2,3,4,5,6,7,8,9,0};
        System.out.println("Исходный массив:");
        printArray(arr7);
        value = 3;
        System.out.println("Измененный массив (сдвиг = " + value + "):");
        printArray(rollArray(arr7, value));
        value = -3;
        System.out.println("Измененный массив (сдвиг = " + value + "):");
        printArray(rollArray(arr7, value));
        System.out.println();

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
        int[] result = new int[arr.length];
        System.arraycopy(arr, 0, result, 0, result.length);
        for (int i = 0; i < result.length; i++) {
            if (result[i] == 1) {
                result[i] = 0;
            } else {
                result[i] = 1;
            }
        }
        return result;
    }

    public static int[] GetArray() {
        int[] result = new int[100];
        for (int i = 0; i < result.length; i++) {
            result[i] = i + 1;
        }
        return result;
    }

    public static int[] mulArray(int[] arr) {
        int[] result = new int[arr.length];
        System.arraycopy(arr, 0, result, 0, result.length);
        for (int i = 0; i < result.length; i++) {
            if (result[i] < 6) {
                result[i] = result[i] * 2;
            }
        }
        return result;
    }

    public static int[][] setDiagonalArray(int col, int row) {
        int[][] result = new int[col][row];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                if (i == j || (i + j == result[i].length - 1)) {
                    result[i][j] = 1;
                }
            }
        }
        return result;
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

    public static int getSumArray(int[] arr) {
        int result = 0;
        for (int v : arr) {
            result += v;
        }
        return result;
    }

    public static double getSumArray(double[] arr) {
        double result = arr[0];
        for (double v : arr) {
            result += v;
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
        if (arr.length == 0) {
            return false;
        } else {
            int[][] arrSum = new int[arr.length][2];
            arrSum[0][0] = 0;
            arrSum[0][1] = getSumArray(arr);
            System.out.println("Сумма элементов массива равна " + arrSum[0][1]);
            for (int i = 1; i < arr.length; i++) {
                arrSum[i][0] = arrSum[i - 1][0] + arr[i - 1];
                arrSum[i][1] = arrSum[0][1] - arrSum[i][0];
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


    public static int[] rollArray(int[] arr, int n) {
        int[] result = new int[arr.length];
        System.arraycopy(arr, 0, result, 0, result.length);
        if (result.length <= 1 || result.length == n) {
            return result;
        } else {
            int value;
            int countN = n % result.length;
            if (countN > 0) {
                for (int i = 0; i < countN; i++) {
                    value = result[result.length - 1];
                    for (int j = result.length - 1; j > 0; j--) {
                        result[j] = result[j - 1];
                    }
                    result[0] = value;
                    //printArray(result);
                }
            } else {
                for (int i = 0; i < countN * -1; i++) {
                    value = result[0];
                    for (int j = 0; j < result.length - 1; j++) {
                        result[j] = result[j + 1];
                    }
                    result[result.length - 1] = value;
                    //printArray(result);
                }
            }
            return result;
        }
    }

}
