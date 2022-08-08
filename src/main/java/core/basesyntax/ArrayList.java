package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elementData;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkForResize();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkRangeForAdd(index);
        checkForResize();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
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
        if (index >= 0 && index < size) {
            return (T) elementData[index];
        }
        throw new ArrayListIndexOutOfBoundsException("Cannot get the element "
                + "because index " + index + " is invalid");
    }

    @Override
    public void set(T value, int index) {
        checkIndexValidation(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexValidation(index);
        Object[] dataList = elementData;
        T oldValue = (T) dataList[index];
        fastRemove(dataList, index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        final Object[] dataList = elementData;
        final int size = this.size;
        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (dataList[i] == null) {
                    remove(i);
                    return element;
                }
            } else {
                if (element.equals(dataList[i])) {
                    remove(i);
                    return element;
                }
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkRangeForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is invalid");
        }
    }

    private void checkForResize() {
        if (size == elementData.length) {
            elementData = Arrays.copyOf(elementData, (int) (elementData.length * 1.5));
        }
    }

    private void checkIndexValidation(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Cannot remove element because "
                    + "index " + index + " is invalid");
        }
    }

    private void fastRemove(Object[] dataList, int index) {
        int newSize = size - 1;
        if (newSize > index) {
            System.arraycopy(dataList, index + 1, dataList, index, newSize - index);
        }
        dataList[size = newSize] = null;
    }
}
