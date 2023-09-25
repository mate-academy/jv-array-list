package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] data;

    public ArrayList() {
        data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (isCapacityFull()) {
            extendCapacity();
        }
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexValidationForAdd(index);
        if (isCapacityFull()) {
            extendCapacity();
        }
        if (index == size) {
            add(value);
        } else {
            for (int i = size - 1; i >= index; i--) {
                data[i + 1] = data[i];
            }
            data[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexValidation(index);
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexValidation(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexValidation(index);
        final T removedObject = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
        return removedObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (data[i] == element
                    || data[i] != null && data[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("no such element in ArrayList");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void extendCapacity() {
        int newCapacity = (int) (data.length * 1.5);
        T[] dataWithNewCapacity = (T[]) new Object[newCapacity];
        for (int i = 0; i < data.length; i++) {
            dataWithNewCapacity[i] = data[i];
        }
        data = dataWithNewCapacity;
    }

    private void checkIndexValidation(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private void checkIndexValidationForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private boolean isCapacityFull() {
        return size == data.length;
    }
}
