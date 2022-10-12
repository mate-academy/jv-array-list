package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elementData;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (++size == elementData.length) {
            grow();
        }
        elementData[size - 1] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index != size) {
            rangeCheck(index);
        }
        if (++size == elementData.length) {
            grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
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
        rangeCheck(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        T element = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elementData[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(element + " do not find in this ArrayList");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        Object[] newDataArray = new Object[size + size / 2];
        System.arraycopy(elementData, 0, newDataArray, 0, elementData.length);
        elementData = newDataArray;
    }

    private void rangeCheck(int index) {
        if ((size != 0 || index != size) && (index >= size || index < 0)) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range: " + index);
        }
    }
}
