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
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) valus[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        valus[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removeElement = (T) valus[index];
        System.arraycopy(valus, index + 1, valus, index, size - index - 1);
        size--;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (valus[i] == element || valus[i] != null && valus[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no element: " + element);
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

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Bounds: " + size);
        }
    }
}
