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
        if (size == elementData.length) {
            growElementData();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == elementData.length) {
            growElementData();
        }
        rangeCheck(index, true);
        System.arraycopy(elementData, index,
                elementData, index + 1,
                size - index);
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
        rangeCheck(index, false);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheck(index, false);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index, false);
        T oldValue = (T) elementData[index];
        int newSize;
        if ((newSize = size - 1) > index) {
            System.arraycopy(elementData, index + 1, elementData, index, newSize - index);
        }
        elementData[size = newSize] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Value does not exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growElementData() {
        int oldCapacity = elementData.length;
        int futureCapacity = elementData.length * 2;
        Object[] newElementData = new Object[futureCapacity];
        if (oldCapacity > 0) {
            System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
            elementData = newElementData;
        } else {
            elementData = new Object[Math.max(DEFAULT_CAPACITY, size + 1)];
        }
    }

    private void rangeCheck(int index, boolean isAdd) {
        if ((index > size - (isAdd ? 0 : 1) && size != 0) || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: "
                    + index + " more then size " + size);
        }
    }
}
