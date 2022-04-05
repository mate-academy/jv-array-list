package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFOLT = 10;
    private int size;
    private T[] arrays;

    public ArrayList() {
        arrays = (T[]) new Object[DEFOLT];
    }

    @Override
    public void add(T value) {
        sizeTest();
        arrays[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("add(T value, int index)");
        }
        sizeTest();
        System.arraycopy(arrays, index, arrays, index + 1, size - index);
        arrays[index] = value;
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
        checkExeption(index, size);
        return arrays[index];
    }

    @Override
    public void set(T value, int index) {
        checkExeption(index, size);
        sizeTest();
        arrays[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("remove(int index)");
        }
        T result = arrays[index];
        removeElement(arrays, index);
        size--;
        return result;
    }

    @Override
    public T remove(T element) {
        sizeTest();
        return removeElement(arrays, element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void removeElement(T[] arrays, int index) {
        int remainingElements = size - index - 1;
        System.arraycopy(arrays, index + 1, arrays, index, remainingElements);
    }

    public T removeElement(T[] arrays, T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (arrays[i] == element || arrays[i] != null && arrays[i].equals(element)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException();
        }
        T result = arrays [index];
        int lastElements = arrays.length - (index + 1);
        System.arraycopy(arrays, index + 1, arrays, index, lastElements);
        size--;
        return result;
    }

    private void sizeTest() {
        if (size == arrays.length) {
            growUp();
        }
    }

    private void growUp() {
        int newSize = size + (size / 2);
        T[] newArray = (T[]) new Object[newSize];
        System.arraycopy(arrays, 0, newArray, 0, arrays.length);
        arrays = newArray;
    }

    private void checkExeption(int index, int length) {
        if (index >= length || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
    }
}
