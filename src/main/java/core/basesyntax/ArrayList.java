package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_INDEX = 0;
    private static final int INITIAL_CAPACITY = 10;
    private Object[] defaultArray = new Object[INITIAL_CAPACITY];
    private int sizeCounter = 0;
    private int arrayLength = 10;

    @Override
    public void add(T value) {
        if (sizeCounter >= arrayLength) {
            arrayLength = grow();
            defaultArray = increaseArray(defaultArray);
        }
        defaultArray[sizeCounter++] = value;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckerForAdding(index);
        sizeCounter++;
        if (sizeCounter >= arrayLength) {
            arrayLength = grow();
        }
        defaultArray = increaseArray(defaultArray,value, index);
    }

    @Override
    public void addAll(List<T> list) {
        if (sizeCounter + list.size() > arrayLength) {
            arrayLength = grow();
        }
        defaultArray = increaseArray(list);
    }

    private Object[] increaseArray(Object[] array) {
        Object[] temp = new Object[arrayLength];
        System.arraycopy(array, DEFAULT_INDEX, temp, DEFAULT_INDEX, sizeCounter);
        return temp;
    }

    private Object[] increaseArray(Object[] array, T value, int index) {
        Object[] temp = new Object[arrayLength];
        System.arraycopy(array,DEFAULT_INDEX, temp, DEFAULT_INDEX, index);
        System.arraycopy(array, index, temp, index + 1, sizeCounter - index);
        temp[index] = value;
        return temp;
    }

    private Object[] increaseArray(List<T> list) {
        Object[] tempList = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            tempList[i] = list.get(i);
        }
        Object[] temp = new Object[arrayLength];
        System.arraycopy(defaultArray, DEFAULT_INDEX, temp, DEFAULT_INDEX, sizeCounter);
        System.arraycopy(tempList, DEFAULT_INDEX, temp, sizeCounter, tempList.length);
        sizeCounter += list.size();
        return temp;
    }

    public int grow() {
        return arrayLength + (arrayLength / 2);
    }

    private void rangeChecker(int index) {
        if ((index < 0 || index >= sizeCounter) && index != DEFAULT_INDEX) {
            throw new ArrayListIndexOutOfBoundsException("Out of bounds");
        }
    }

    private void rangeCheckerForAdding(int index) {
        if ((index < 0 || index > sizeCounter) && index != DEFAULT_INDEX) {
            throw new ArrayListIndexOutOfBoundsException("Out of bounds");
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
        if (index == sizeCounter) {
            sizeCounter++;
        }
        defaultArray[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeChecker(index);
        T removedElement = (T) defaultArray[index];
        if (index == sizeCounter - 1) {
            System.arraycopy(defaultArray, DEFAULT_INDEX, defaultArray,
                    DEFAULT_INDEX, sizeCounter - 1);
            sizeCounter--;
            return removedElement;
        }
        System.arraycopy(defaultArray, index + 1, defaultArray, index, sizeCounter - index);
        sizeCounter--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i <= sizeCounter; i++) {
            if (element == null) {
                sizeCounter--;
                return null;
            }
            if (element.equals(defaultArray[i])) {
                T removedElement = (T)defaultArray[i];
                System.arraycopy(defaultArray, i + 1, defaultArray, i,sizeCounter - i);
                sizeCounter--;
                return removedElement;
            }
        }
        throw new NoSuchElementException("No such element");
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
