package java3.lesson1.fruits;

import java.util.ArrayList;

public class Box<F extends Fruit> {

    private final ArrayList<F> fruits;

    public Box(ArrayList<F> fruits) {
        this.fruits = fruits;
    }

    public void addToBox(F fruit) {
        this.fruits.add(fruit);
    }

    public float getWeight() {
        if (this.fruits.size()==0) {
            return 0;
        } else {
            return this.fruits.get(0).getWeight() * this.fruits.size();
        }
    }

    public boolean compare(Box<?> box){
        return this.getWeight() == box.getWeight();
    }

    public void pourIntoOtherBox(Box<F> box){
        for (int i = 0; i < this.fruits.size(); i++) {
            box.addToBox(this.fruits.get(i));
        }
        this.fruits.clear();
    }

}
