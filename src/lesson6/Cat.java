package lesson6;

public final class Cat extends Animal {


    public Cat(String name, String color, int age) {
        super(name,color,age);
    }


    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", age=" + age +
                '}';
    }
}
