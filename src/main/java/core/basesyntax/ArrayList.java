package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] arrayElements;
    private int size;

    public ArrayList() {
        arrayElements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == arrayElements.length - 1) {
            resize();
        }
        arrayElements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        if (size == arrayElements.length - 1) {
            resize();
        }
        System.arraycopy(arrayElements, index, arrayElements, index + 1, size - index);
        arrayElements[index] = value;
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
        indexCheck(index);
        return (T) arrayElements[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        arrayElements[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T removedElement = (T) arrayElements[index];
        System.arraycopy(arrayElements, index + 1, arrayElements, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < arrayElements.length; i++) {
            if (element == null ? arrayElements[i] == element : element.equals(arrayElements[i])) {
                System.arraycopy(arrayElements, i + 1, arrayElements, i, size - i);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("Elements not found" + element);
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
        Object[] newArray = new Object[arrayElements.length + arrayElements.length / 2];
        System.arraycopy(arrayElements, 0, newArray, 0, size);
        arrayElements = newArray;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound " + index);
        }
    }

    private void indexCheck(int index) {
        if (isEmpty() || index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " not found");
        }
    }
}
