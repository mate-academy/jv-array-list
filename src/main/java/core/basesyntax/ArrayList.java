package core.basesyntax;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final double GROWTH_FACTOR = 1.5;
    private static final int MAX_LIST_SIZE = 10;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[MAX_LIST_SIZE];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        growIfArrayFull();
        size++;
        checkIndex(index);
        if (size == 2) {
            moveElementsToTheRight(1);
        } else if (size > 2) {
            for (int currentIndex = size - 2; currentIndex > index; currentIndex--) {
                moveElementsToTheRight(currentIndex);
            }
        }
        values[index] = value;
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
        for (int index = 0; index < size; index++) {
            if (element == values[index] || (element != null && element.equals(values[index]))) {
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
        if (size == values.length) {
            int newLength = (int) (values.length * GROWTH_FACTOR);
            T[] tempValues = values;
            values = (T[]) new Object[newLength];
            System.arraycopy(tempValues, 0, values, 0, size);
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
        while (nextElementIndex < size) {
            values[index++] = values[nextElementIndex++];
        }
        values[index] = null;
        size--;
        return element;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index + " is Out of bounds for this ArrayList!");
        }
    }
}
