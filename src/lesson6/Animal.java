package lesson6;

public abstract class Animal {
    protected final String name;
    protected final String color;
    protected final int age;

    public Animal(String name, String color, int age) {
       this.name = name;
       this.color = color;
       this.age = age;
   }

    public Animal() {
        this(null,null,0);
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
        System.out.println(name + " пробежал " + distance + " м");
    }

    public void swim(int distance) {
        System.out.println(name + " проплыл " + distance + " м");
    }

}

