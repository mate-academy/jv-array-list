package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ARRAY_START_POSITION = 0;
    private Object[] listOfData;
    private int size;

    public ArrayList() {
        listOfData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkLength();
        listOfData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        checkLength();
        copy(index, index + 1);
        listOfData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        rangeCheck(index);
        return (T) listOfData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheck(index);
        listOfData[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        T value = (T)listOfData[index];
        copy(index + 1, index);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        int index = getIndex(element);
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] grow() {
        Object[] temp = new Object[size + (size >> 1)];
        copyToGrow(listOfData, temp);
        return temp;
    }

    private void checkLength() {
        if (size == listOfData.length) {
            listOfData = grow();
        }
    }

    private void copy(int sourcePosition, int destinationPosition) {
        System.arraycopy(listOfData, sourcePosition,
                listOfData, destinationPosition, size - sourcePosition);
    }

    private void copyToGrow(Object[] soursArray, Object[] destArray) {
        System.arraycopy(soursArray, ARRAY_START_POSITION,
                destArray, ARRAY_START_POSITION, size);
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds");
        }
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bounds");
        }
    }

    private int getIndex(T element) {
        for (int i = 0; i < listOfData.length; i++) {
            if (element == listOfData[i]
                    || listOfData[i] != null
                    && listOfData[i].equals(element)) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }
}
