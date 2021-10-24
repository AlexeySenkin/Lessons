package lesson6;

public abstract class Animal {
    protected final String name;
    protected final String color;
    protected final int age;

    protected final int maxRunDistance;
    protected final int maxSwimDistance;

    public Animal(String name, String color, int age,int maxRunDistance,int maxSwimDistance) {
       this.name = name;
       this.color = color;
       this.age = age;
       this.maxRunDistance = maxRunDistance;
       this.maxSwimDistance = maxSwimDistance;
   }

    public Animal() {
        this(null,null,0,0,0);
    }


    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getAge() {
        return age;
    }

    public void printInfo() {
        System.out.println(this.toString());
    }


    public void run(int distance) {
        if (maxRunDistance > distance) {
            System.out.println(name + " пробежал " + distance + " м");
        } else {
            System.out.println("Дистанция " + distance + " м для " + name + " больше ограничения");
        }
    }

    public void swim(int distance) {
        if (maxSwimDistance > distance) {
            System.out.println(name + " проплыл " + distance + " м");
        } else {
            System.out.println("Дистанция " + distance + " м для " + name + " больше ограничения");
        }
    }

}

