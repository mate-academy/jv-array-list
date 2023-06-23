package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_RATE = 1.5;
    private static final Object[] EMPTY_ELEMENT_DATA = {};
    private Object[] elementData;
    private int size;

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENT_DATA;
        } else {
            throw new IllegalArgumentException("Illegal capacity" + initialCapacity);
        }
    }

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (isStorageFull()) {
            growStorage();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, size + 1);

        if (size == elementData.length) {
            growStorage();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    private void growStorage() {
        int newCapacity = (int) (elementData.length * GROW_RATE);
        if (newCapacity <= 0) {
            throw new IllegalStateException("Exceeded maximum capacity");
        }
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index, size);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, size);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, size);
        final T oldValue = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elementData[i] == null && element == null)
                    || (elementData[i] != null && elementData[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isStorageFull() {
        return size == elementData.length;
    }

    private void checkIndex(int index, int size) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range");
        }
    }
}
