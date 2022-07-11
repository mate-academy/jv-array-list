package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 5;
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

    private void growArray() {
        if (size == objects.length) {
            T[] arraySours = objects;
            objects = (T[])new Object[(int)
                    (Math.round(objects.length * STEP_INCREASING_ARRAY))];
            System.arraycopy(arraySours,0,objects,0,arraySours.length);
        }
    }

    private void checkOutOfBoundsException(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds value: "
                    + index);
        }
    }

    @Override
    public void add(T value) {
        growArray();
        objects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds value :"
                    + value + " with index " + index);
        }
        growArray();
        System.arraycopy(objects,index,objects,index + 1,objects.length - index - 1);
        objects[index] = value;
        size++;
    }

    @Override
    public String toString() {
        return "ArrayList{"
                + "objects="
                + Arrays.toString(objects)
                + ", size=" + size + '}';
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkOutOfBoundsException(index);
        return objects[index];
    }

    @Override
    public void set(T value, int index) {
        checkOutOfBoundsException(index);
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        checkOutOfBoundsException(index);
        T element = objects[index];
        System.arraycopy(objects,index + 1, objects,index, objects.length - index - 1);
        size--;
        return element;
    }

    @Override
    public T remove(T value) {
        for (int i = 0; i < size(); i++) {
            if (Objects.equals(objects[i], value)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such value: " + value);
    }
}
