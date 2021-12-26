package java2.lesson2;

public class MyArrayDataException extends RuntimeException {

    public MyArrayDataException(int row, int col, String value) {
        super("Элемент массива [" + row + "][" + col + "] '" + value + "' не целое число");
    }


}
