package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_LIST_SIZE = 10;
    private T[] values;
    private int currentListSize = 0;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_LIST_SIZE];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        values[currentListSize++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkAdditionRange(index);
        growIfArrayFull();
        System.arraycopy(values, index, values, index + 1, currentListSize - index);
        values[index] = value;
        currentListSize++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexRange(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexRange(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexRange(index);
        T removeElement = values[index];
        currentListSize--;
        System.arraycopy(values,index + 1,values,index,currentListSize - index);
        return removeElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < values.length; i++) {
            if (element != null && element.equals(values[i]) || element == values[i]) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no value you looking for");
    }

    @Override
    public int size() {
        return currentListSize;
    }

    @Override
    public boolean isEmpty() {
        return currentListSize == 0;
    }

    public void growIfArrayFull() {
        if (currentListSize == values.length) {
            int oldCapacity = values.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            values = Arrays.copyOf(values, newCapacity);
        }
    }

    private void checkAdditionRange(int index) {
        if (index < 0 || index > currentListSize) {
            throw new ArrayListIndexOutOfBoundsException(index
                    + " is out of bounds for adding to the list");
        }
    }

    private void checkIndexRange(int index) {
        if (index < 0 || index >= currentListSize) {
            throw new ArrayListIndexOutOfBoundsException(index + " is out of bounds");
        }
    }
}
