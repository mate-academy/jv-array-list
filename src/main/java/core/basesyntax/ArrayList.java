package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_RATE = 1.5;
    private static final Object[] EMPTY_ELEMENT_DATA = {};
    private Object[] elementData;
    private int size = 0;

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
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, size + 1);

        if (size == elementData.length) {
            growStorage();
        }

        for (int i = size - 1; i >= index; i--) {
            elementData[i + 1] = elementData[i];
        }

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
        final Object[] tmp = elementData;

        final T oldValue = (T)tmp[index];

        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }

        elementData[size - 1] = null;
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return remove(i);
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (elementData[i] != null && elementData[i].equals(element)) {
                    return remove(i);
                }
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
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range");
        }
    }
}
