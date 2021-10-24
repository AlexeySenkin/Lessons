package lesson6;

import java.util.Scanner;

public class HomeWorkApp6 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество животных:");
        while (!scanner.hasNextInt()) {
            System.out.println("Количество животный должно быть целочисленное!");
            scanner.next();
        }
        Animal[] animals = new Animal[scanner.nextInt()];

        for (int i = 0; i < animals.length; i++) {
            if (Math.toIntExact(Math.round(Math.random())) == 1) {
                animals[i] = new Cat("Cat" + i,"red", 5);
            } else {
                animals[i] = new Dog("Doc" + i,"black",5,"dogType");
            }
        }

        System.out.println("Ваши новые животные:");
        for (int i = 0; i < animals.length; i++) {
            animals[i].printInfo();
            animals[i].printRun(Math.toIntExact(Math.round(Math.random() * 500)));
            animals[i].printSwim(Math.toIntExact(Math.round(Math.random() * 10)));
            System.out.println();
        }

        int catCount = 0;
        int dogCount = 0;
        for (int i = 0; i < animals.length; i++) {
            if (animals[i] instanceof Cat) {
                catCount++;
            } else {
                dogCount++;
            }
        }
        System.out.println("У вас проживают " + catCount + " котов и " + dogCount + " собак!");

    }
}
