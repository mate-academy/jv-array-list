package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double INCREASE_INDEX = 1.5;
    private int size;
    private Object[] elementsData;

    public ArrayList() {
        elementsData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkSize();
        elementsData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
        } else {
            checkIndexCorrectness(index);
            checkSize();
            System.arraycopy(elementsData, index, elementsData,index + 1,size - index);
            elementsData[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            checkSize();
            elementsData[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIndexCorrectness(index);
        return (T) elementsData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexCorrectness(index);
        elementsData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexCorrectness(index);
        Object element = elementsData[index];
        if (size > index) {
            System.arraycopy(elementsData, index + 1, elementsData, index, --size - index);
        }
        elementsData[size] = null;
        return (T) element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element != null && element.equals(elementsData[i])
                    || elementsData[i] == null && element == null)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(element + " was not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkSize() {
        if (size == elementsData.length) {
            grow();
        }
    }

    private void grow() {
        int newSize = (int) (elementsData.length * INCREASE_INDEX);
        Object[] newArray = new Object[newSize];
        System.arraycopy(elementsData,0, newArray, 0, size);
        elementsData = newArray;
    }

    private void checkIndexCorrectness(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(index + " is incorrect input index");
        }
    }
}
