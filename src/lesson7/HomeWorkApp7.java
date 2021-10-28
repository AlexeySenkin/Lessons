package lesson7;


import java.util.Scanner;

public class HomeWorkApp7 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество котов:");
        while (!scanner.hasNextInt()) {
            System.out.println("Количество котов должно быть целочисленное!");
            scanner.next();
        }
        Cat[] cats = new Cat[scanner.nextInt()];
        for (int i = 0; i < cats.length; i++) {
            cats[i] = new Cat("Cat" + i, Math.toIntExact(Math.round(Math.random() * 200)),false);
            System.out.println(cats[i].toString());
        }
        Plate plate = new Plate(1000);
        for (int i = 0; i < cats.length; i++) {
            if (cats[i].eatingFromPlate(plate)) {
                System.out.println(cats[i].getName() + " съел " + cats[i].getAppetite() + " еды. Остаток в тарелке " + plate.getVolume());
            }
            else {
                System.out.println(cats[i].getName()  + " не смог поесть. Аппетит у кота " + cats[i].getAppetite() + ", но в тарелке всего " + plate.getVolume() + " еды");
            }
            System.out.println(cats[i].toString() + "\n'");
        }

    }

}
