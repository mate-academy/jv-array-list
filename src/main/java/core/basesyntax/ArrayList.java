package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elementData;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            resize();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == elementData.length) {
            resize();
        }
        checkIndex(index, size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    private void resize() {
        Object[] newElement = new Object[elementData.length * 3 / 2];
        System.arraycopy(elementData, 0, newElement, 0, size);
        elementData = newElement;
    }

    public boolean checkIndex(int index, int size) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + " is out of bound");
        }
        return true;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index, size);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, size);
        elementData[index] = value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index, size);
        T removed = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if (element == elementData[i] || elementData[i] != null
                    && elementData[i].equals(element)) {
                System.arraycopy(elementData, i + 1, elementData, i, size - i);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("There is no such element: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
