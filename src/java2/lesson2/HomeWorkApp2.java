package java2.lesson2;

public class HomeWorkApp2 {

    public static final int SIZE = 4;

    private static final String[][] correctArray = new String[][] {
            {"1","2","3","4"},
            {"2","3","4","5"},
            {"6","7","8","9"},
            {"0","1","2","3"}
    };
    private static final String[][] wrongArray1 = new String[][] {
            {"1","2","3","wrong"},
            {"2","wrong","4","5"},
            {"6","7","wrong","9"},
            {"0","1","2","wrong"}
    };
    private static final String[][] wrongArray2 = new String[][] {
            {"1","2","3","4"},
            {"2","3","4","5"},
            {"6","7","8","9"}
    };

    public static void main(String[] args) {
        try {
            String[][] array;
            array = correctArray;
            //array = wrongArray1;
            //array = wrongArray2;

            printArray(array);
            System.out.println("Сумма элементов массива: " + sumArray(array));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

    }


    public static void printArray(String[][] arr) {
        for (String[] ints : arr) {
            for (String anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    public static int sumArray(String[][] array) {
        int i = 0;
        int j = 0;
        int arraySum = 0;
        try {
            if (array.length != SIZE) {
                throw new MyArraySizeException();
            } else {
                for (String[] strings : array) {
                    if (strings.length != SIZE) {
                        throw new MyArraySizeException();
                    }
                }
            }
            for (i = 0; i < array.length; i++) {
               for (j = 0; j < array[i].length; j++) {
                   arraySum += Integer.parseInt(array[i][j]);
               }
            }
            return arraySum;
        } catch (NumberFormatException e) {
            throw new MyArrayDataException(i, j, array[i][j]);
        }
    }


}


