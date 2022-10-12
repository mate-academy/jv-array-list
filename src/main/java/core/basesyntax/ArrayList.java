package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

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
        if (checkIndexBounds(index)) {
            throw new ArrayListIndexOutOfBoundsException("Cannot add element to index: "
                    + index + ", list size: " + actualSize);
        }
        ensureCapacity();
        actualSize++;
        if (index != actualSize) {
            System.arraycopy(elementsData, index, elementsData, index + 1, actualSize - index);
        }
        elementsData[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i != list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (checkIndexBounds(index + 1)) {
            throw new ArrayListIndexOutOfBoundsException("Cannot get element with index: "
                    + index + ", list size: " + actualSize);
        }
        return (T) elementsData[index];
    }

    @Override
    public void set(T value, int index) {
        if (checkIndexBounds(index + 1)) {
            throw new ArrayListIndexOutOfBoundsException("Cannot set value to the "
                    + "element with index: "
                    + index + ", list size: " + actualSize);
        }
        elementsData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (checkIndexBounds(index + 1)) {
            throw new ArrayListIndexOutOfBoundsException("Cannot remove element with index: "
                    + index + ", list size: " + actualSize);
        }

        return removeByIndex(index);
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i != actualSize; i++) {
            if (Objects.equals(elementsData[i], element)) {
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

    private T removeByIndex(int index) {
        T removed = (T) elementsData[index];
        int numMoved = elementsData.length - index - 1;
        System.arraycopy(elementsData, index + 1, elementsData, index, numMoved);
        actualSize--;
        return removed;
    }

    private boolean checkIndexBounds(int index) {
        return index > actualSize || index < 0;
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
