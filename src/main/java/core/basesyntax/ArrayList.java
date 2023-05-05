package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_MAX_SIZE = 10;
    private static final int MIN_SIZE = 0;
    private T[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        data = (T[]) new Object[DEFAULT_MAX_SIZE];
        size = MIN_SIZE;
    }

    @Override
    public void add(T value) {
        growIfFull();
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkAddRange(index);
        growIfFull();
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
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
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        checkRange(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        checkRange(index);
        T value = data[index];
        if (size - 1 > index) {
            System.arraycopy(data, index + 1, data, index, size - index - 1);
        }
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementsAreEqual(data[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(element.toString() + ": doesn't exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean elementsAreEqual(T first, T second) {
        return first == second || first != null && first.equals(second);
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        int oldSize = data.length;
        int newSize = oldSize + (oldSize >> 1);
        if (newSize < 0) {
            newSize = Integer.MAX_VALUE;
        }
        T[] dataCopy = data.clone();
        data = (T[]) new Object[newSize];
        System.arraycopy(dataCopy, 0, data, 0, oldSize);
    }

    private void growIfFull() {
        if (size == data.length) {
            grow();
        }
    }

    private void checkAddRange(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Illegal index: " + index);
        }
    }

    private void checkRange(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Illegal index: " + index);
        }
    }
}
