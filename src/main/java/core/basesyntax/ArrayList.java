package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_INDEX = 0;
    private static final int INITIAL_CAPACITY = 10;
    private Object[] defaultArray = new Object[INITIAL_CAPACITY];
    private int sizeCounter = 0;
    private int newCapacity = 10;

    @Override
    public void add(T value) {
        if (sizeCounter >= newCapacity) {
            newCapacity = grow();
            increaseArray();
        }
        defaultArray[sizeCounter++] = value;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckerForAdding(index);
        sizeCounter++;
        if (sizeCounter >= newCapacity) {
            newCapacity = grow();
        }
        increaseArray(value, index);
    }

    private void increaseArray() {
        Object[] temp = defaultArray.clone();
        defaultArray = new Object[newCapacity];
        System.arraycopy(temp, DEFAULT_INDEX, defaultArray, DEFAULT_INDEX, sizeCounter);
    }

    private void increaseArray(T value, int index) {
        Object[] temp = defaultArray.clone();
        defaultArray = new Object[newCapacity];
        System.arraycopy(temp, DEFAULT_INDEX, defaultArray, DEFAULT_INDEX, index);
        defaultArray[index] = value;
        System.arraycopy(temp, index, defaultArray, index + 1, sizeCounter - index);
    }

    private void increaseArray(List<T> list) {
        Object[] tempList = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            tempList[i] = list.get(i);
        }
        Object[] temp = defaultArray.clone();
        defaultArray = new Object[newCapacity];
        System.arraycopy(temp, DEFAULT_INDEX, defaultArray, DEFAULT_INDEX, sizeCounter);
        System.arraycopy(tempList, DEFAULT_INDEX, defaultArray, sizeCounter, tempList.length);
        sizeCounter += list.size();
    }

    public int grow() {
        return newCapacity + (newCapacity / 2);
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
    public void addAll(List<T> list) {
        if (sizeCounter + list.size() > newCapacity) {
            newCapacity = grow();
        }
        increaseArray(list);
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
        if (index == sizeCounter - 1) {
            Object[] temp = defaultArray.clone();
            defaultArray = new Object[newCapacity];
            System.arraycopy(temp, DEFAULT_INDEX, defaultArray, DEFAULT_INDEX, index);
            sizeCounter--;
            return (T)temp[index];
        }
        Object[] temp = defaultArray.clone();
        defaultArray = new Object[newCapacity];
        System.arraycopy(temp, DEFAULT_INDEX, defaultArray, DEFAULT_INDEX, index);
        System.arraycopy(temp, index + 1, defaultArray, index, sizeCounter);
        sizeCounter--;
        return (T)temp[index];
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i <= sizeCounter; i++) {
            if (element == null) {
                sizeCounter--;
                return null;
            }
            if (element.equals(defaultArray[i])) {
                Object[] temp = defaultArray.clone();
                defaultArray = new Object[newCapacity];
                System.arraycopy(temp, DEFAULT_INDEX, defaultArray, DEFAULT_INDEX, i);
                System.arraycopy(temp, i + 1, defaultArray, i, sizeCounter);
                sizeCounter--;
                return (T)temp[i];
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

    @Override
    public ArrayList<T> clone() {
        try {
            return (ArrayList<T>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Can not clone", e);
        }
    }
}
