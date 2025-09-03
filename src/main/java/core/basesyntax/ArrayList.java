package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elementsData;

    public ArrayList() {
        elementsData = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void expandCapacity() {
        if (size == elementsData.length) {
            int newCapacity = (int) (elementsData.length * 1.5);
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(elementsData, 0, newArray, 0, elementsData.length);
            elementsData = newArray;
        }
    }

    @Override
    public void add(T value) {
        expandCapacity();
        elementsData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index:" + index + "Size" + size);
        }
        expandCapacity();
        System.arraycopy(elementsData, index, elementsData, index + 1, size - index);
        elementsData[index] = value;
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
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index:" + index + "Size" + size);
        }
        return (T) elementsData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index:" + index + "Size" + size);
        }
        elementsData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index:" + index + "Size" + size);
        }

        final T oldValue = (T) elementsData[index];
        System.arraycopy(elementsData, index + 1, elementsData, index, size - index - 1);
        elementsData[size - 1] = null;
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementsData[i] == null && element == null) {
                return remove(i);
            }
            if (elementsData[i] != null && elementsData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found: " + element);
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
