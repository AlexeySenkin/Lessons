package java2.lesson1;

import java.util.Scanner;

public class HomeWorkApp1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество участников:");
        while (!scanner.hasNextInt()) {
            System.out.println("Количество участников должно быть целое!");
            scanner.next();
        }
        Participant[] participants = new Participant[scanner.nextInt()];
        for (int i = 0; i < participants.length; i++) {
            if (Math.toIntExact(Math.round(Math.random() * 2)) == 2) {
                participants[i] = new Cat("Cat" + i,500, 3);
            } else if (Math.toIntExact(Math.round(Math.random() * 2)) == 1) {
                participants[i] = new Robot("Robot" + i,1000,2);
            } else {
                participants[i] = new Human("Human" + i,100,1);
            }
        }

        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Введите количество препятствий:");
        while (!scanner2.hasNextInt()) {
            System.out.println("Количество препятствий должно быть целое!");
            scanner2.next();
        }
        Obstacle[] obstacles = new Obstacle[scanner2.nextInt()];
        for (int i = 0; i < obstacles.length; i++) {
            if (Math.toIntExact(Math.round(Math.random())) == 1) {
                obstacles[i] = new Wall(2);
            } else {
                obstacles[i] = new Treadmill(200);
            }
        }

        System.out.println("Ваши участники :");
        for (int i = 0; i < participants.length; i++) {
            System.out.println(participants[i].toString());
        }
        System.out.println("Список препятствий :");
        for (int i = 0; i < obstacles.length; i++) {
            System.out.println(obstacles[i].toString());
        }

        System.out.println("Начинаем!!!");

        boolean isRun = true;
        boolean isJump = true;

        for (int i = 0; i < participants.length; i++) {
            for (int j = 0; j < obstacles.length; j++) {
                if (obstacles[j] instanceof Wall) {
                    isJump = participants[i].Jump(obstacles[j].getValue());
                } else
                    if (obstacles[j] instanceof Treadmill) {
                        isRun = participants[i].Run(obstacles[j].getValue());
                    }
                if (isRun == false || isJump == false) {
                    System.out.println("Участник выбывает!!!");
                    break;
                }

            }
        }

    }
}
