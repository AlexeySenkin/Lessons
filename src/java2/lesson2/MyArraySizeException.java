package java2.lesson2;

public class MyArraySizeException extends RuntimeException{

    public MyArraySizeException() {
        super("Размерность массива доложна быть равна 4 на 4!");

    }
}
