package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkingLength();
        elementData[size()] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == index) {
            add(value);
            return;
        }
        size++;
        checkingIndex(index);
        checkingLength();
        System.arraycopy(elementData, index, elementData, (index + 1), (size - index));
        elementData[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkingIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkingIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkingIndex(index);
        T oldValue = (T) elementData[index];
        System.arraycopy(elementData, (index + 1), elementData, index, (size - index - 1));
        size--;
        return oldValue;
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
        return size() == 0;
    }

    private void checkingLength() {
        if (elementData.length == size) {
            grow();
        }
    }

    private void grow() {
        Object[] bufferArray = new Object[elementData.length + (elementData.length / 2)];
        System.arraycopy(elementData, 0, bufferArray, 0, size);
        elementData = bufferArray;
    }

    private void checkingIndex(int index) {
        if (index >= size() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index + " is invalid for size " + size);
        }
    }

    private int getIndex(T value) {
        for (int i = 0; i < elementData.length; i++) {
            if (Objects.equals(elementData[i], value)) {
                return i;
            }
        }
        throw new NoSuchElementException("There is no such element " + value + " present.");
    }

}
