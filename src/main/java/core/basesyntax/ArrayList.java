package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            increaseCapacity();
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        if (size == elements.length) {
            increaseCapacity();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
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
        checkBounds(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkBounds(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkBounds(index);
        T deletedElement = (T) elements[index];
        if (index != elements.length - 1) {
            System.arraycopy(elements, index + 1, elements, index, size - index);
        }
        size--;
        return deletedElement;
    }

    @Override
    public T remove(T element) {
        int index = getIndexByValue(element);
        if (index == -1) {
            throw new NoSuchElementException("There is no such element in the list");
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds " + index);
        }
    }

    private void checkBounds(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds " + index);
        }
    }

    private void increaseCapacity() {
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        Object[] copiedElements = new Object[newCapacity];
        System.arraycopy(elements, 0, copiedElements, 0, size);
        elements = copiedElements;
    }

    private int getIndexByValue(T value) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == value || (elements[i] != null && elements[i].equals(value))) {
                return i;
            }
        }
        return -1;
    }
}
