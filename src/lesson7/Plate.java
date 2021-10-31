package lesson7;

public class Plate {
    protected int volume;

    public Plate(int volume) {
        this.volume = volume;
    }



    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void addFood(int foodValue) {
        volume = volume + foodValue;
    }

    @Override
    public String toString() {
        return "Plate{" +
                "volume=" + volume +
                '}';
    }
}
