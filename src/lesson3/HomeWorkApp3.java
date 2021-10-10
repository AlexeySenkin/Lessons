package lesson3;

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
        printArray(setArray(arr2));
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

    public static int[] setArray(int[] arr) {
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
        for (int i = 0; i < result.length - 1; i++) {
            result[i] = initialValue;
        }
        return result;
    }
}
