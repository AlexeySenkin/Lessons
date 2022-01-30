package java3.lesson1;

import java3.lesson1.fruits.Apple;
import java3.lesson1.fruits.Box;
import java3.lesson1.fruits.Orange;

import java.util.ArrayList;

public class HomeWorkApp1 {

    public static final float ORANGE_WEIGHT = 1.5F;
    public static final float APPLE_WEIGHT = 1F;

    public static void main(String[] args) {
        GenArray<Integer> iArray1 = new GenArray<>(1, 2, 3, 4, 5);
        System.out.print("Исходный массив объектов: ");
        iArray1.printArray();
        iArray1.changeArrayElementsPosition(1,2);
        System.out.print("Измененный массив объектов: ");
        iArray1.printArray();
        System.out.print("Измененный arrayList: ");
        System.out.println(iArray1.getArrayList());

        GenArray<String> iArray2 = new GenArray<>("1", "2", "3", "4", "5");
        System.out.print("Исходный массив объектов: ");
        iArray2.printArray();
        iArray2.changeArrayElementsPosition(1,2);
        System.out.print("Измененный массив объектов: ");
        iArray2.printArray();
        System.out.print("Измененный arrayList: ");
        System.out.println(iArray2.getArrayList());


        Box<Orange> boxOrange1 = new Box<>(new ArrayList<>());
        boxOrange1.addToBox(new Orange(ORANGE_WEIGHT));

        Box<Orange> boxOrange2 = new Box<>(new ArrayList<>());
        boxOrange2.addToBox(new Orange(ORANGE_WEIGHT));
        boxOrange2.addToBox(new Orange(ORANGE_WEIGHT));

        Box<Orange> boxOrange3 = new Box<>(new ArrayList<>());
        boxOrange3.addToBox(new Orange(ORANGE_WEIGHT));
        boxOrange3.addToBox(new Orange(ORANGE_WEIGHT));
        boxOrange3.addToBox(new Orange(ORANGE_WEIGHT));

        Box<Apple> boxApple1 = new Box<>(new ArrayList<>());
        boxApple1.addToBox(new Apple(APPLE_WEIGHT));

        Box<Apple> boxApple2 = new Box<>(new ArrayList<>());
        boxApple2.addToBox(new Apple(APPLE_WEIGHT));
        boxApple2.addToBox(new Apple(APPLE_WEIGHT));

        Box<Apple> boxApple3 = new Box<>(new ArrayList<>());
        boxApple3.addToBox(new Apple(APPLE_WEIGHT));
        boxApple3.addToBox(new Apple(APPLE_WEIGHT));
        boxApple3.addToBox(new Apple(APPLE_WEIGHT));

        System.out.println("Вес 1 коробки = " + boxOrange1.getWeight());
        System.out.println("Вес 2 коробки = " + boxOrange2.getWeight());
        System.out.println("Вес 3 коробки = " + boxOrange3.getWeight());

        System.out.println("Вес 4 коробки = " + boxApple1.getWeight());
        System.out.println("Вес 5 коробки = " + boxApple2.getWeight());
        System.out.println("Вес 6 коробки = " + boxApple3.getWeight());

        System.out.println(boxOrange2.compare(boxOrange1));
        System.out.println(boxOrange2.compare(boxApple3));

    }
}
