package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            resize();
        }
        elementData[size] = value;
        ++size;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of range");
        }
        if (size == elementData.length) {
            resize();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        ++size;
    }

    @Override
    public void addAll(List<T> list) {
        if (size + list.size() > elementData.length) {
            int newSize = size + list.size() + (size >> 1);
            Object[] newArr = new Object[newSize];
            System.arraycopy(elementData, 0, newArr, 0, size);
            elementData = (T[]) newArr;
        }
        for (int i = 0; i < list.size(); ++i) {
            elementData[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? elementData[i] == null : element.equals(elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element to remove");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        int newSize = size + (size >> 1);
        Object[] newArr = new Object[newSize];
        System.arraycopy(elementData, 0, newArr, 0, size);
        elementData = (T[]) newArr;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of range");
        }
    }
}
