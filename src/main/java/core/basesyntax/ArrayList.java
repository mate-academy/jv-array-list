package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 10;
    private static final double COEFFICIENT = 1.5;
    private T[] elements;
    private int size;

    public ArrayList() {
        this.size = 0;
        elements = (T[]) new Object[CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            grow();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkAddRange(index);
        if (size == elements.length) {
            grow();
        }
        System.arraycopy(elements, index,
                elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkRange(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkRange(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkRange(index);
        T resulArray = (T) elements[index];
        System.arraycopy(elements, index + 1,
                elements, index, size - index - 1);
        size--;
        return resulArray;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element
                    || elements[i] != null
                    && elements[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can't find element in list"
                + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int calculateNewCapacity() {
        return (int) (elements.length * COEFFICIENT);
    }

    private void grow() {
        T[] newCapacityArray = (T[]) new Object[calculateNewCapacity()];
        System.arraycopy(elements, 0, newCapacityArray,
                0, elements.length);
        elements = newCapacityArray;
    }

    private void checkRange(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }

    private void checkAddRange(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }
}
