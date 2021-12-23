package java2.lesson1;

public class Wall implements Obstacle {
    protected final int value;

    public Wall(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Wall{" +
                "value=" + value +
                '}';
    }
}
