package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ARRAY_BEGINNING = 0;

    private int size;
    private Object[] elementStore;

    ArrayList() {
        size = 0;
        elementStore = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        elementStore[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (indexIsLegal(index) || index == size) {
            size++;
            growIfArrayFull();
            System.arraycopy(elementStore, index, elementStore,
                    nextIndex(index), calculateElementsToCopy(index));
            set(value, index);
        } else {
            throw createErrorWithIndex(index);
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    private int calculateElementsToCopy(int index) {
        return size - index;
    }

    private ArrayListIndexOutOfBoundsException createErrorWithIndex(int index) {
        return new ArrayListIndexOutOfBoundsException("Index: [" + index
                + "] is out of bounds for list size [" + size + "]!");
    }

    @Override
    public T get(int index) {
        if (indexIsLegal(index)) {
            return (T) elementStore[index];
        } else {
            throw createErrorWithIndex(index);
        }
    }

    private void growIfArrayFull() {
        if (isFull()) {
            int newCapacity = size + size / 2;
            Object[] oldStore = new Object[size];
            System.arraycopy(elementStore, ARRAY_BEGINNING, oldStore, ARRAY_BEGINNING, size);
            elementStore = new Object[newCapacity];
            System.arraycopy(oldStore, ARRAY_BEGINNING, elementStore, ARRAY_BEGINNING, size);
        }
    }

    private int nextIndex(int index) {
        return index + 1;
    }

    @Override
    public T remove(int index) {
        T oldValue;
        if (indexIsLegal(index)) {
            oldValue = (T) elementStore[index];
            if (isFull()) {
                elementStore[index] = null;
                size--;
                return oldValue;
            }
            System.arraycopy(elementStore, nextIndex(index),
                    elementStore, index, calculateElementsToCopy(index));
            elementStore[size] = null;
            size--;
            return oldValue;
        } else {
            throw createErrorWithIndex(index);
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elementStore[i] == element)
                    || (elementStore[i] != null && elementStore[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public void set(T value, int index) {
        if (indexIsLegal(index)) {
            elementStore[index] = value;
        } else {
            throw createErrorWithIndex(index);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == elementStore.length;
    }

    private boolean indexIsLegal(int index) {
        return index > -1 && index < size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(elementStore[0]);
        for (int i = 1; i < elementStore.length; i++) {
            if (i < size) {
                sb.append(", ").append(elementStore[i]);
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
