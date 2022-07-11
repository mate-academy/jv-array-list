package core.basesyntax;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double STEP_INCREASING_ARRAY = 1.5;
    private T[] objects;
    private int size;

    public ArrayList() {

        objects = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        if (size == objects.length) {
            T[] arraySours = objects;
            objects = (T[])new Object[(int)
                    (Math.round(objects.length * STEP_INCREASING_ARRAY))];
            System.arraycopy(arraySours,0,objects,0,arraySours.length);
        }
    }
    private void checkOutOfBoundsException(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Out of bout exception for index: "
                    + index);
        }
    }
    @Override
    public void add(T value) {
        grow();
        objects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Out of bout exception ofr value :"
                    + value + " with index " + index);
        }
        grow();
        System.arraycopy(objects,index,objects,index + 1,objects.length - index - 1);
        objects[index] = value;
        size++;
    }

    @Override
    public String toString() {
        return "ArrayList{" +
                "objects=" + Arrays.toString(objects) +
                ", size=" + size +
                '}';
    }

    @Override
    public void addAll(List<T> list) {

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public void set(T value, int index) {

    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public T remove(T element) {
        return null;
    }



}
