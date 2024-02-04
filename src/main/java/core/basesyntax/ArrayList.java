package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private int capacity = DEFAULT_CAPACITY;
    private Object[] array;

    public ArrayList() {
        array = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        growIfArrayFull();
        size++;
        checkBounds(index);

        for (int i = array.length - 1; i > index; i--) {
            array[i] = array[i - 1];
        }

        array[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        int listSize = list.size();
        ensureCapacity(listSize);
        int counter = 0;

        for (int i = size; i < size + listSize; i++) {
            array[i] = list.get(counter++);
        }

        size += listSize;
    }

    private void ensureCapacity(int listLength) {
        while (!(capacity > size + listLength)) {
            grow();
        }
    }

    private void growIfArrayFull() {
        if (size == capacity) {
            grow();
        }
    }

    private void grow() {
        capacity = (int) (capacity * 1.5);
        array = Arrays.copyOf(array, capacity);
    }

    @Override
    public T get(int index) {
        checkBounds(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkBounds(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkBounds(index);
        size--;
        T objectRemoved = (T) array[index];

        for (int i = index; i < array.length - 1; i++) {
            array[i] = array[i + 1];
        }

        array[array.length - 1] = null;
        return objectRemoved;
    }

    @Override
    public T remove(T element) {
        T itemRemoved = null;
        boolean isRemoved = false;

        for (int i = 0; i < array.length; i++) {
            if (!isRemoved
                    && ((array[i] == null
                    && element == null)
                    || array[i] != null
                    && array[i].equals(element))) {
                itemRemoved = (T) array[i];
                remove(i);
                isRemoved = true;
            }
        }

        if (!isRemoved) {
            throw new NoSuchElementException("Element " + element.toString() + " does not exist.");
        }
        return itemRemoved;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkBounds(int index) {
        if (index != 0 && (index > size - 1 || index < 0)) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds.");
        }
    }
}
