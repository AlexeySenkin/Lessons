package lesson6;

public final class Dog extends Animal {

    private final String type;


    public Dog(String name, String color, int age, String type) {
        super(name,color,age);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", age=" + age +
                ", type='" + type + '\'' +
                '}';
    }
}
