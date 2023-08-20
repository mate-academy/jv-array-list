package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData = new Object [DEFAULT_CAPACITY];
    private int size = 0;

    @Override
    public void add(T value) {

        if (size == elementData.length) {
            grow(size);
        }
        elementData[size] = value;
        size++;

    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, 0, size);
        if (size == elementData.length) {
            grow(size);
        }

        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = value;
        size++;

    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i),size);

        }
    }

    @Override
    public T get(int index) {
        checkIndex(index, 0, size - 1);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, 0, size - 1);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, 0, size - 1);
        final T t = (T)elementData[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        }
        elementData[--size] = null;

        return t;

    }

    @Override
    public T remove(T element) {

        int index = -1;
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element) {
                checkIndex(i, 0, size - 1);
                remove(i);
                index = i;
                i = size;
            } else if (elementData[i] != null && elementData[i].equals(element)) {
                checkIndex(i, 0, size - 1);
                remove(i);
                index = i;
                i = size;
            }
        }

        if (index == -1) {
            throw new NoSuchElementException("No such element present");
        } else {
            return element;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    private void checkIndex(int index, int min, int max) {
        if (index < min || index > max) {
            throw new core.basesyntax.ArrayListIndexOutOfBoundsException("Invalid index " + index);
        }
    }

    private void grow(int minCapacity) {
        minCapacity *= 1.5;
        String[] extendedArray = new String[minCapacity];
        System.arraycopy(elementData,0,extendedArray,0,elementData.length);
        elementData = extendedArray;
    }
}
