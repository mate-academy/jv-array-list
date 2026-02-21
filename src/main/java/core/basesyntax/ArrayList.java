package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private int currentMaxSize = 10;
    private Object[] dataArray = new Object[currentMaxSize];
    private int filledElements = 0;

    @Override
    public void add(T value) {
        add(value, filledElements);
    }

    @Override
    public void add(T value, int index) {
        if (!isValidAddIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("index cant be lower than 0 "
                    + "or value cant be added to the non-initialized part of array!");
        }

        if (filledElements == dataArray.length) {
            expandArray();
        }

        System.arraycopy(dataArray, index, dataArray, index + 1, filledElements - index);
        dataArray[index] = value;
        filledElements++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        if (!isInArrayBounds(index)) {
            throw new ArrayListIndexOutOfBoundsException("the value in index you have entered "
                    + "is not reachable!");
        }
        return (T) dataArray[index];
    }

    @Override
    public void set(T value, int index) {
        if (!isInArrayBounds(index)) {
            throw new ArrayListIndexOutOfBoundsException("the value in index you have entered "
                    + "is not reachable!");
        }

        dataArray[index] = value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        if (!isInArrayBounds(index)) {
            throw new ArrayListIndexOutOfBoundsException("the value in index you have entered "
                    + "is not reachable!");
        }

        final T removed = (T) dataArray[index];
        System.arraycopy(dataArray, index + 1, dataArray, index, filledElements - index - 1);
        filledElements--;
        dataArray[filledElements] = null;
        return removed;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(T element) {
        for (int i = 0; i < filledElements; i++) {
            if (element == null ? dataArray[i] == null : element.equals(dataArray[i])) {
                final T removed = (T) dataArray[i];
                System.arraycopy(dataArray, i + 1, dataArray, i, filledElements - i - 1);
                filledElements--;
                dataArray[filledElements] = null;
                return removed;
            }
        }
        throw new NoSuchElementException("There is no such element!");
    }

    @Override
    public int size() {
        return filledElements;
    }

    @Override
    public boolean isEmpty() {
        return filledElements == 0;
    }

    private void expandArray() {
        int newCapacity = currentMaxSize + (currentMaxSize >> 1);
        Object[] newArray = new Object[newCapacity];

        System.arraycopy(dataArray, 0, newArray, 0, filledElements);

        dataArray = newArray;
        currentMaxSize = newCapacity;
    }

    private boolean isInArrayBounds(int index) {
        return index >= 0 && index < filledElements;
    }

    private boolean isValidAddIndex(int index) {
        return index >= 0 && index <= filledElements; // <= для конца массива
    }
}
