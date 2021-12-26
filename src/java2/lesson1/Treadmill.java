package java2.lesson1;

public class Treadmill implements Obstacle{
    protected final int value;

    public Treadmill(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Treadmill{" +
                "value=" + value +
                '}';
    }
}
