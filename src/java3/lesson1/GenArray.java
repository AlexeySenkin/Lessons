package java3.lesson1;

import java.util.ArrayList;

public class GenArray<T> {
    private final T[] array;

    public GenArray(T... array) {
        this.array = array;
    }

    public void changeArrayElementsPosition(int firstPosition, int secondPosition) {
         T a = this.array[firstPosition];
         this.array[firstPosition] = array[secondPosition];
         this.array[secondPosition] = a;
    }

    public ArrayList<T> getArrayList() {
        ArrayList<T> arList = new ArrayList<>();
        for (int i = 0; i < this.array.length; i++) {
            arList.add(array[i]);
        }
        return arList;
    }

    public void printArray() {
        for (int i = 0; i < this.array.length; i++) {
            System.out.print(this.array[i] + " ");
        }
    }

}
