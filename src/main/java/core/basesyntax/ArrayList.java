package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ARRAY_BEGINNING = 0;
    private static final int GROWTH_FACTOR = 2;

    private int size;
    private T[] elementStore;

    ArrayList() {
        elementStore = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        elementStore[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (isIndexLegal(index) || index == size) {
            size++;
            grow();
            System.arraycopy(elementStore, index, elementStore,
                    nextIndex(index), calculateElementsToCopy(index));
            set(value, index);
        } else {
            throw createErrorWithIndex(index);
        }
    }

    @Override
    public void addAll(List<T> list) {
        grow(list.size());
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (isIndexLegal(index)) {
            return elementStore[index];
        } else {
            throw createErrorWithIndex(index);
        }
    }

    @Override
    public T remove(int index) {
        T oldValue;
        if (isIndexLegal(index)) {
            oldValue = elementStore[index];
            if (isFull()) {
                elementStore[index] = null;
                size--;
                return oldValue;
            }
            System.arraycopy(elementStore, nextIndex(index), elementStore,
                    index, calculateElementsToCopy(index));
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
        throw new NoSuchElementException("There is no such element: "
                + element.toString() + " in list.");
    }

    @Override
    public void set(T value, int index) {
        if (isIndexLegal(index)) {
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

    private int calculateElementsToCopy(int index) {
        return size - index;
    }

    private ArrayListIndexOutOfBoundsException createErrorWithIndex(int index) {
        return new ArrayListIndexOutOfBoundsException("Index: [" + index
                + "] is out of bounds for list size [" + size + "]!");
    }

    private void copyDataStore(int newCapacity) {
        T[] newStore = (T[]) new Object[newCapacity];
        System.arraycopy(elementStore, ARRAY_BEGINNING, newStore, ARRAY_BEGINNING, size);
        elementStore = newStore;
    }

    private void grow() {
        if (isFull()) {
            int newCapacity = size + size / GROWTH_FACTOR;
            copyDataStore(newCapacity);
        }
    }

    private void grow(int requiredCapacity) {
        int newCapacity = size + requiredCapacity;
        copyDataStore(newCapacity);
    }

    private boolean isIndexLegal(int index) {
        return index > -1 && index < size;
    }

    private boolean isFull() {
        return size == elementStore.length;
    }

    private int nextIndex(int index) {
        return index + 1;
    }
}
