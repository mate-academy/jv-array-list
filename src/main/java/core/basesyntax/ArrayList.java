package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private boolean checkIndex(int index) {
        return index < size && index >= 0;
    }

    private void grow() {
        T[] data = elementData;
        elementData = (T[]) new Object[elementData.length * 3 / 2];
        System.arraycopy(data, 0, elementData, 0, size);
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        size++;
        if (size == elementData.length) {
            grow();
        }
        if (checkIndex(index)) {
            System.arraycopy(elementData, index,
                    elementData, index + 1,
                    size - index);
            elementData[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Can't add element by index");
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (checkIndex(index)) {
            return elementData[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Can't get element by index");
        }
    }

    @Override
    public void set(T value, int index) {
        if (checkIndex(index)) {
            elementData[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Can't set element by index");
        }
    }

    @Override
    public T remove(int index) {
        if (checkIndex(index)) {
            final T res = elementData[index];
            System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
            elementData[size - 1] = null;
            size--;
            return res;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Can't remove element by index");
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || elementData[i] != null
                     && elementData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element was not find");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
