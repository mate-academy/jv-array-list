package core.basesyntax;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private int maxListSize = 10;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[maxListSize];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        values[capacity++] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        growIfArrayFull();
        capacity++;
        checkIndex(index);
        if (capacity == 2) {
            moveElementsToTheRight(1);
        } else if (capacity > 2) {
            for (int currentIndex = capacity - 2; currentIndex > index; currentIndex--) {
                moveElementsToTheRight(currentIndex);
            }
        }
        values[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        T[] tempValues = (T[]) new Object[list.size()];
        for (int i = 0; i < tempValues.length; i++) {
            tempValues[i] = list.get(i);
            add(tempValues[i]);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T deletedValue = values[index];
        return searchAndHoldRemovedElement(deletedValue, index);
    }

    @Override
    public T remove(T element) {
        for (int index = 0; index < capacity; index++) {
            if (element == values[index]
                    || (element != null && element.equals(values[index]))) {
                return searchAndHoldRemovedElement(element, index);
            }
        }
        throw new NoSuchElementException("The element given was not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfArrayFull() {
        if (capacity == maxListSize) {
            maxListSize = (int) (maxListSize + maxListSize * 0.5);
            T[] tempValues = values;
            values = (T[]) new Object[maxListSize];
            System.arraycopy(tempValues, 0, values, 0, capacity);
        }
    }

    private void moveElementsToTheRight(int currentIndex) {
        T tempCurrentValueHolder = values[currentIndex];
        T tempPreviousValueHolder = values[currentIndex - 1];
        values[currentIndex + 1] = tempCurrentValueHolder;
        values[currentIndex] = tempPreviousValueHolder;
    }

    private T searchAndHoldRemovedElement(T element, int index) {
        int nextElementIndex = index + 1;
        while (nextElementIndex < capacity) {
            values[index++] = values[nextElementIndex++];
        }
        values[index] = null;
        capacity--;
        size--;
        return element;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= capacity) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is Out of bounds for this ArrayList!");
        }
    }
}
