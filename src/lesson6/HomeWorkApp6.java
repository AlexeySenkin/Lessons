package lesson6;

import java.util.Random;
import java.util.Scanner;

public class HomeWorkApp6 {
    public static void main(String[] args) {

        Random random;

        System.out.println("Домашнее задание 6");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество животных");
        while (!scanner.hasNextInt()) {
            System.out.println("Количество животный должно быть целочисленное!");
            scanner.next();
        }
        Animal[] animals = new Animal[scanner.nextInt()];


        for (int i = 0; i < animals.length; i++) {
            if (Math.toIntExact(Math.round(Math.random())) == 1) {
                animals[i] = new Cat("Cat" + i,"red", 5);
            } else {
                animals[i] = new Dog("Doc" + i,"black",5,"dogType" + i);
            }
        }

        for (int i = 0; i < animals.length; i++) {
            animals[i].printInfo();
        }





//        Animal animal1 = new Cat("Zevs","red", 5);
//        Animal animal2 = new Cat("Tor", "red", 5);
//        Animal animal3 = new Cat("Aika", "gray", 4);
//        Animal animal4 = new Cat("Leia","motley",4);
//        Animal animal5 = new Dog("Una","black",3,"sheepdog");
//        animal1.run(150);
//        animal1.swim(20);
//        animal5.run(200);
//        animal5.swim(50);
//        System.out.println(animal1.toString());
//        System.out.println(animal5.toString());




    }
}
