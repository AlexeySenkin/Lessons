package lesson5;

public class HomeWorkApp5 {
    public static void main(String[] args) {

        Employee[] employees = new Employee[5];
        employees[0] = new Employee("Василий Иванов", "мужской",30,"программист",495111112,"IvanovVV@mail.ru",100000);
        employees[1] = new Employee("Петр Петров", "мужской",25,"начальник",495111113,"PetrovPP@mail.ru",150000);
        employees[2] = new Employee("Елена Иванова", "женский",27,"заместитель директора",495999998,"IvanovaEE@mail.ru",700000);
        employees[3] = new Employee("Мария Петрова", "женский",40,"директор",495999999,"PenrovaMM@mail.ru",1000000);
        employees[4] = new Employee("Сидор Сидоров", "мужской",60,"сторож",495111111,"",10000);

        System.out.println("Перечень сотрудников старше 40 лет:");
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getAge() >= 40) {
                employees[i].printInfo();
            }
        }

    }
}
