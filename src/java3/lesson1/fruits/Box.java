package java3.lesson1.fruits;

import java.util.ArrayList;

public class Box<F extends Fruit> {

    private final ArrayList<F> fruits;

    public Box(ArrayList<F> fruits) {
        this.fruits = fruits;
    }

    public int addToBox(F fruit) {
        this.fruits.add(fruit);
        return this.fruits.size();
    }

    public float getWeight() {
        return this.fruits.get(0).getWeight() * this.fruits.size();
    }

    public boolean compare(Box<?> box){
        return this.getWeight() == box.getWeight();
    }

}
