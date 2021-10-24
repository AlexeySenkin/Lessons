package lesson6;

public final class Cat extends Animal {

    public Cat(String name, String color, int age) {
        super(name,color,age,200,0);
    }

    @Override
    public String toString() {
        return "Кот {" +
                "кличка='" + name + '\'' +
                ", окрас='" + color + '\'' +
                ", возраст=" + age +
                ", ограничение на бег=" + maxRunDistance +
                ", не умеет плавать" +
                '}';
    }
}
