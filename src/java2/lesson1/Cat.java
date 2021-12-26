package java2.lesson1;

public class Cat implements Participant {
    protected final String name;
    protected final int maxRunDistance;
    protected final int maxJumpValue;

    public Cat(String name, int maxRunDistance, int maxJumpValue) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpValue = maxJumpValue;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", maxRunDistance=" + maxRunDistance +
                ", maxJumpValue=" + maxJumpValue +
                '}';
    }

    public int getMaxRunDistance() {
        return maxRunDistance;
    }

    public int getMaxJumpValue() {
        return maxJumpValue;
    }
    public boolean Jump (int value) {
        if (value <= maxJumpValue) {
            System.out.println(this.name + " прыгнул!");
            return true;
        } else {
            System.out.println(this.name + " не смог прыгнуть!");
            return false;
        }
    };

    public boolean Run (int distance) {
        if (distance <= maxRunDistance) {
            System.out.println(this.name + " пробежал!");
            return true;

        } else {
            System.out.println(this.name + " не смог пробежать!");
            return false;
        }

    }
}
