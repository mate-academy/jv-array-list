package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] arrayOfObjects;

    public ArrayList() {
        this.arrayOfObjects = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == arrayOfObjects.length) {
            arrayOfObjects = increaseCapacity();
        }
        arrayOfObjects[size++] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        validIndex(index);
        if (size == arrayOfObjects.length) {
            arrayOfObjects = increaseCapacity();
        }
        System.arraycopy(arrayOfObjects, index, arrayOfObjects, index + 1, size - index);
        arrayOfObjects[index] = value;
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
        validIndex(index);
        return (T) arrayOfObjects[index];
    }

    @Override
    public void set(T value, int index) {
        validIndex(index);
        arrayOfObjects[index] = value;
    }

    @Override
    public T remove(int index) {
        validIndex(index);
        T element = (T) arrayOfObjects[index];
        System.arraycopy(arrayOfObjects, index + 1, arrayOfObjects, index, size - 1 - index);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((arrayOfObjects[i] != null && arrayOfObjects[i].equals(element))
                    || arrayOfObjects[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element present");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] increaseCapacity() {
        int oldCapacity = arrayOfObjects.length;
        int newCapacity = oldCapacity + oldCapacity / 2;
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(arrayOfObjects, 0, newArray, 0, oldCapacity);
        return newArray;
    }

    private void validIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index: " + index + " is invalid");
        }
    }
}
