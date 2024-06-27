package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double CAPACITY_INDEX = 1.5;
    private int arraySize;
    private T[] items;

    public ArrayList() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (arraySize == items.length) {
            resize();
        }
        items[arraySize++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        if (arraySize == items.length) {
            resize();
        }
        System.arraycopy(items, index, items, index + 1, arraySize++ - index);
        items[index] = value;
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
        return items[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        items[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T deletedObject = items[index];
        System.arraycopy(items, index + 1, items, index, --arraySize - index);
        return deletedObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < arraySize; i++) {
            if (element == null) {
                if (items[i] == null) {
                    return remove(i);
                }
            } else {
                if (element.equals(items[i])) {
                    return remove(i);
                }
            }
        }
        throw new NoSuchElementException("Element " + element + " non-existent");
    }

    @Override
    public int size() {
        return arraySize;
    }

    @Override
    public boolean isEmpty() {
        return arraySize == 0;
    }

    private T[] resize() {
        int newCapacity = (int) (items.length * CAPACITY_INDEX);
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(items, 0, newArray, 0, arraySize);
        items = (T[]) newArray;
        return items;
    }

    private void checkIndexForAdd(int index) {
        if (index > arraySize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index " + index + " non-existent");
        }
    }

    private void checkIndex(int index) {
        if (index >= arraySize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index " + index + " non-existent");
        }
    }
}
