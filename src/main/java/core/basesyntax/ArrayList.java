package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_INDEX = 0;
    private static final int INITIAL_CAPACITY = 10;
    private Object[] defaultArray = new Object[INITIAL_CAPACITY];
    private int sizeCounter = 0;

    @Override
    public void add(T value) {
        if (sizeCounter == defaultArray.length) {
            defaultArray = grow(defaultArray);
        }
        defaultArray[sizeCounter++] = value;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckerForAdding(index);
        if (++sizeCounter == defaultArray.length) {
            defaultArray = grow(defaultArray);
        }
        System.arraycopy(defaultArray, index, defaultArray, index + 1, sizeCounter - index);
        defaultArray[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        if (sizeCounter + list.size() > defaultArray.length) {
            defaultArray = grow(defaultArray);
        }
        increaseArray(list);
    }

    private void increaseArray(List<T> list) {
        Object[] tempList = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            tempList[i] = list.get(i);
        }
        System.arraycopy(tempList, DEFAULT_INDEX, defaultArray, sizeCounter, tempList.length);
        sizeCounter += list.size();
    }

    public Object[] grow(Object[] array) {
        int arrayLength = defaultArray.length + (defaultArray.length / 2);
        Object[] temp = new Object[arrayLength];
        System.arraycopy(array, DEFAULT_INDEX, temp, DEFAULT_INDEX, sizeCounter);
        return temp;
    }

    private void rangeChecker(int index) {
        if (index < 0 || index >= sizeCounter) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + " out of bounds, for length " + size());
        }
    }

    private void rangeCheckerForAdding(int index) {
        if (index < 0 || index > sizeCounter) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + " out of bounds, for length " + size());
        }
    }

    @Override
    public T get(int index) {
        rangeChecker(index);
        return (T)defaultArray[index];
    }

    @Override
    public void set(T value, int index) {
        rangeChecker(index);
        defaultArray[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeChecker(index);
        T removedElement = (T) defaultArray[index];
        System.arraycopy(defaultArray, index + 1, defaultArray, index, sizeCounter - index - 1);
        sizeCounter--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < sizeCounter; i++) {
            if ((element != null && element.equals(defaultArray[i]))
                    || element == defaultArray[i]) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can not remove. The element "
                + "\"" + element + "\"" + " does not exist");
    }

    @Override
    public int size() {
        return sizeCounter;
    }

    @Override
    public boolean isEmpty() {
        return sizeCounter == DEFAULT_INDEX;
    }
}
