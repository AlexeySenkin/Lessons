package lesson6;

public final class Dog extends Animal {

    private final String type;

    public Dog(String name, String color, int age, String type) {
        super(name,color,age,500,10);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Собака {" +
                "кличка '" + name + '\'' +
                ", цвет '" + color + '\'' +
                ", возраст " + age +
                ", может пробежать " + maxRunDistance + " м " +
                ", может проплыть " + maxSwimDistance + " м " +
                ", порода '" + type + '\'' +
                '}';
    }
}
