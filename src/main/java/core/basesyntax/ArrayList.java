package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final double INCREASE_CAPACITY_STEP = 1.5;
    private T[] objects;
    private int size;

    public ArrayList() {
        objects = (T[]) new Object[INITIAL_CAPACITY];
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
    public void addAll(List<T> list) {
        for (int j = 0; j < list.size(); j++) {
            add(list.get(j));
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
        for (int j = 0; j < size(); j++) {
            if (objects[j] == value || objects[j] != null && objects[j].equals(value)) {
                return remove(j);
            }
        }
        throw new NoSuchElementException("No such value: " + value);
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
                    (Math.round(objects.length * INCREASE_CAPACITY_STEP))];
            System.arraycopy(arraySours,0,objects,0,arraySours.length);
        }
    }

    private void checkOutOfBoundsException(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Out of bout exception for index: "
                    + index);
        }
    }
}
