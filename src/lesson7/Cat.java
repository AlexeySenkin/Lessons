package lesson7;

public class Cat {
    protected final String name;
    protected int appetite;
    protected boolean wellfed;

    public Cat(String name, int appetite, boolean wellfed) {
        this.name = name;
        this.appetite = appetite;
        this.wellfed = wellfed;
    }

    public Cat() {
        this(null,0, false);
    }



    public String getName() {
        return name;
    }

    public int getAppetite() {
        return appetite;
    }

    public void setAppetite(int appetite) {
        this.appetite = appetite;
    }

    public boolean isWellfed() {
        return wellfed;
    }

    public void setWellfed(boolean wellfed) {
        this.wellfed = wellfed;
    }

    public boolean eatingFromPlate(Plate plate) {
        if (appetite <= plate.getVolume()) {
            plate.setVolume(plate.getVolume() - appetite);
            wellfed = true;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", appetite=" + appetite +
                ", wellfed=" + wellfed +
                '}';
    }
}
