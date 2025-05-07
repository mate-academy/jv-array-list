package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private Object[] elementsData;
    private int actualSize;

    public ArrayList() {
        elementsData = new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        add(value, actualSize);
    }

    @Override
    public void add(T value, int index) {
        checkIndexBounds(index, "Cannot add element to index: "
                + index + ", list size: " + actualSize);
        ensureCapacity();
        if (index != actualSize) {
            System.arraycopy(elementsData, index, elementsData, index + 1, actualSize - index);
        }
        elementsData[index] = value;
        actualSize++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i != list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexBounds(index + 1, "Cannot get element with index: "
                + index + ", list size: " + actualSize);
        return (T) elementsData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexBounds(index + 1, "Cannot set value to the "
                        + "element with index: " + index + ", list size: " + actualSize);
        elementsData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexBounds(index + 1, "Cannot remove element with index: "
                + index + ", list size: " + actualSize);
        return removeByIndex(index);
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i != actualSize; i++) {
            Object existing = elementsData[i];
            if (existing == element || element != null && element.equals(existing)) {
                return removeByIndex(i);
            }
        }
        throw new NoSuchElementException("That element does not exist");
    }

    @Override
    public int size() {
        return actualSize;
    }

    @Override
    public boolean isEmpty() {
        return actualSize == 0;
    }

    private void checkIndexBounds(int index, String message) {
        if (index > actualSize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(message);
        }
    }

    private T removeByIndex(int index) {
        T removed = (T) elementsData[index];
        int numMoved = elementsData.length - index - 1;
        System.arraycopy(elementsData, index + 1, elementsData, index, numMoved);
        actualSize--;
        return removed;
    }

    private void ensureCapacity() {
        int minCapacity = actualSize + 1;
        if (minCapacity >= elementsData.length) {
            Object[] newArray = new Object[calculateNewCapacity(minCapacity)];
            System.arraycopy(elementsData, 0, newArray, 0, elementsData.length);
            elementsData = newArray;
        }
    }

    private static int calculateNewCapacity(int oldCapacity) {
        return oldCapacity * 3 / 2;
    }
}
