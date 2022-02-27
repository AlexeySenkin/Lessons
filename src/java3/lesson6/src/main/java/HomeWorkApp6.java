import java.util.Arrays;

public class HomeWorkApp6 {

    public static void main(String[] args) {
        int[] array = {4,3,2,1};
        HomeWorkApp6 homeWorkApp6 = new HomeWorkApp6();
        System.out.println("Исходный массив целых чисел: " + Arrays.toString(array));
        try {
            System.out.println("Новый массив целых чисел: " + Arrays.toString(homeWorkApp6.checkArrayForFour(array)));
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }

        String message = homeWorkApp6.checkArrayForOneOrFour(array) ? "есть цифры" : "нет цифр";
        System.out.println("В исходном массиве " + message + " 1 и 4");

    }

    public int[] checkArrayForFour(int[] array) throws RuntimeException {
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == 4) {
                return Arrays.copyOfRange(array, i + 1, array.length);
            }
        }
        throw new RuntimeException("В исходном массиве нет цифры 4!!!");
    }

    public boolean checkArrayForOneOrFour(int[] array) {
        boolean isOne = false;
        boolean isFour = false;
        for (int j : array) {
            if (j == 1) {
                isOne = true;
            } else if (j == 4) {
                isFour = true;
            }
        }
        return (isOne && isFour);
    }
}
