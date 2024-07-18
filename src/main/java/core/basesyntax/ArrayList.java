package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final double ADD_CAPACITY = 1.5;
    private static final int POSITION_ZERO = 0;
    private T [] defaultArray;
    private int listSize;

    public ArrayList() {
        defaultArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void isIndexInBounds(int index) {
        if (!(index >= 0 && index < listSize)) {
            throw new ArrayListIndexOutOfBoundsException("Index: "
                    + index
                    + " is out of bounds. "
                    + "Size: "
                    + listSize);
        }
    }

    private void isIndexInRangeInclusive(int index) {
        if (!(index >= 0 && index <= listSize)) {
            throw new ArrayListIndexOutOfBoundsException("Index: "
                    + index
                    + " is out of bounds. "
                    + "Size: "
                    + listSize);
        }
    }

    private void growList() {
        if (listSize == defaultArray.length) {
            Object [] extendedArray = new Object[(int) (defaultArray.length * ADD_CAPACITY)];
            System.arraycopy(defaultArray,
                    POSITION_ZERO,
                    extendedArray,
                    POSITION_ZERO,
                    defaultArray.length);
            defaultArray = (T[]) extendedArray;
        }
    }

    private void insertElement(Object [] array, Object value, int index) {
        isIndexInRangeInclusive(index);
        System.arraycopy(array,
                index, array,
                index + 1,
                defaultArray.length - index - 1);
        array[index] = value;
        listSize++;
    }

    private void removeElement(Object[] array, int index) {
        System.arraycopy(array, index + 1, array, index, defaultArray.length - index - 1);
        listSize--;
    }

    @Override
    public void add(T value) {
        growList();
        defaultArray[listSize++] = value;
    }

    @Override
    public void add(T value, int index) {
        growList();
        insertElement(defaultArray,value,index);
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        isIndexInBounds(index);
        final T returnedElement = defaultArray[index];
        return returnedElement;
    }

    @Override
    public void set(T value, int index) {
        isIndexInBounds(index);
        defaultArray[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexInBounds(index);
        final T removedElement = defaultArray[index];
        removeElement(defaultArray,index);
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < listSize; i++) {
            if (defaultArray[i] == element || element != null && element.equals(defaultArray[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found: " + element);
    }

    @Override
    public int size() {
        return listSize;
    }

    @Override
    public boolean isEmpty() {
        return listSize == 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(defaultArray);
    }
}

