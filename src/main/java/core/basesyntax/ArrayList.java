package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final double GROW_VALUE = 1.5;
    private int maxSize = 10;
    private int actualSize = 0;
    private Object[] array = new Object[maxSize];
    private Object[] tempArray;

    @Override
    public void add(T value) {
        if (actualSize == maxSize) {
            grow();
        }
        array[actualSize] = value;
        actualSize++;
    }

    @Override
    public void add(T value, int index) {
        if (index > actualSize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index doesn't exist");
        }
        if (actualSize == maxSize) {
            grow();
        }
        tempArray = new Object[maxSize];
        System.arraycopy(array, 0, tempArray, 0, index);
        tempArray[index] = value;
        System.arraycopy(array, index, tempArray, index + 1, actualSize - index);
        actualSize++;
        array = tempArray;
    }

    @Override
    public void addAll(List<T> list) {
        int requiredSize = maxSize - actualSize + list.size();
        while (requiredSize > maxSize) {
            grow();
        }
        tempArray = new Object[maxSize];
        System.arraycopy(array, 0, tempArray, 0, actualSize);
        System.arraycopy(toArray(list), 0, tempArray, actualSize, list.size());
        actualSize = actualSize + list.size();
        array = tempArray;
    }

    @Override
    public T get(int index) {
        if (index >= actualSize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index doesn't exist");
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= actualSize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index doesn't exist");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= actualSize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index doesn't exist");
        }
        final T removedItem = (T) array[index];
        tempArray = new Object[maxSize];
        actualSize--;
        System.arraycopy(array, 0, tempArray, 0, index);
        System.arraycopy(array, index + 1, tempArray, index, actualSize - index);
        array = tempArray;
        return removedItem;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if ((array[i] != null && array[i].equals(element))
                    || (array[i] == null && element == null)) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("There is no such element in array");
    }

    @Override
    public int size() {
        return actualSize;
    }

    @Override
    public boolean isEmpty() {
        return actualSize == 0;
    }

    public void grow() {
        maxSize = (int) (maxSize * GROW_VALUE);
        tempArray = new Object[maxSize];
        System.arraycopy(array, 0, tempArray, 0, actualSize);
        array = new Object[maxSize];
        System.arraycopy(tempArray, 0, array, 0, actualSize);
    }

    public T[] toArray(List<T> list) {
        Object[] array = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return (T[]) array;
    }
}
