package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;
    private static final double SIZE_GROWTH = 1.5;
    private T[] elementData;
    private int sizeCounter;

    public ArrayList() {
        this.elementData = (T[]) new Object[INITIAL_SIZE];
    }

    private void grow() {
        if (sizeCounter == elementData.length - 1) {
            int newSize = (int) (elementData.length * SIZE_GROWTH);
            Object[] bufferedData = new Object[newSize];
            System.arraycopy(elementData, 0, bufferedData, 0, elementData.length);
            elementData = (T[])new Object[newSize];
            System.arraycopy(bufferedData, 0, elementData, 0, elementData.length);
        }
    }

    private void indexChecking(int index) {
        if (index < 0 || index >= sizeCounter) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index");
        }
    }

    @Override
    public void add(T value) {
        grow();
        elementData[sizeCounter] = value;
        sizeCounter++;
    }

    @Override
    public void add(T value, int index) {
        if (index > sizeCounter || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index");
        }
        grow();
        Object[] bufferedData = new Object[elementData.length];
        System.arraycopy(elementData, index, bufferedData, 0, elementData.length - index);
        elementData[index] = value;
        System.arraycopy(bufferedData, 0, elementData, index + 1, elementData.length - index - 1);
        sizeCounter++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null) {
                break;
            } else {
                add(list.get(i));
            }
        }
    }

    @Override
    public T get(int index) {
        indexChecking(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexChecking(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexChecking(index);
        T oldValue = (T)elementData[index];
        try {
            System.arraycopy(elementData,
                    index + 1,
                    elementData,
                    index,
                    elementData.length - index - 1);
            sizeCounter--;
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Index is incorrect");
        }
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int indexOfElement = Arrays.asList(elementData).indexOf(element);
        if (indexOfElement > sizeCounter - 1 || indexOfElement < 0) {
            throw new NoSuchElementException("Index out of range");
        }
        return remove(indexOfElement);
    }

    @Override
    public int size() {
        return sizeCounter;
    }

    @Override
    public boolean isEmpty() {
        return sizeCounter == 0;
    }
}
