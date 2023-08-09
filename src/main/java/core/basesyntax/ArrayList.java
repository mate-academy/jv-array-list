package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static Object[] valus;
    private int size;

    public ArrayList() {
        valus = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        valus[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Bounds: " + size);
        }
        growIfArrayFull();
        System.arraycopy(valus, index, valus, index + 1, size - index);
        valus[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            valus[size] = list.get(i);
            size = size + 1;
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Bounds: " + size);
        }
        return (T) valus[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Bounds: " + size);
        }
        valus[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Bounds: " + size);
        }
        T removeElement = (T) valus[index];
        System.arraycopy(valus, index + 1, valus, index, size - index - 1);
        size--;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (valus[i] == element || valus[i] != null && valus[i].equals(element)) {
                System.arraycopy(valus, i + 1, valus, i, size - i);
                size--;
                return element;
            }
        }
        if (valus[size - 1] != element) {
            throw new NoSuchElementException("There is no element: " + element);
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfArrayFull() {
        if (valus.length == size) {
            Object[] growValus = new Object[(int) (valus.length * 1.5)];
            System.arraycopy(valus, 0, growValus, 0, valus.length);
            valus = growValus;
        }
    }
}
