package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        sizeCheck();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        indexCheckForAdd(index);
        sizeCheck();
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
        indexCheck(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T removedObject = get(index);
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedObject;
    }

    @Override
    public T remove(T element) {
        int elementIndex = elementCheck(element);
        if (elementIndex == -1) {
            throw new NoSuchElementException("No such element");
        }
        return remove(elementIndex);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void sizeCheck() {
        if (size == elements.length) {
            grow(elements.length);
        }
    }

    private void grow(int capacity) {
        int newCapacity = capacity + capacity / 2;
        Object[] destinationArray = new Object[newCapacity];
        System.arraycopy(elements, 0, destinationArray, 0, size);
        elements = destinationArray;
    }

    private void indexCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        }
    }

    private void indexCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        }
    }

    private int elementCheck(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || elements[i] != null && elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }
}
