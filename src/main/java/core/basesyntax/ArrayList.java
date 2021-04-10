package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private T[] elements;
    private int currentCapacity;
    private int size = 0;

    public ArrayList() {
        this.elements = (T[]) new Object[INITIAL_CAPACITY];
    }

    public void extendArray() {
        int newCapacity = Math.round((size * 3) / 2 + 1);
        T[] extendedArray = (T[]) new Object[newCapacity];
        System.arraycopy(elements, 0, extendedArray, 0, size);
        elements = extendedArray;
    }

    @Override
    public void add(T value) {
        if ((size + 1) < currentCapacity) {
            elements[size] = value;
            size++;
        }
        if ((size + 1) >= currentCapacity) {
            extendArray();
            elements[size] = value;
            size++;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index != size) {
            checkIndex(index);
        }
        if ((size + 1) < currentCapacity) {
            System.arraycopy(elements, index, elements, index + 1, size - index);
            elements[index] = value;
            size++;
        }
        if ((size + 1) >= currentCapacity) {
            extendArray();
            System.arraycopy(elements, index, elements, index + 1, size - index);
            elements[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        if ((size + list.size()) < currentCapacity) {
            for (int j = 0; j < list.size(); j++) {
                add(list.get(j));
            }
        }
        while ((size + list.size()) < currentCapacity) {
            extendArray();
        }
        for (int j = 0; j < list.size(); j++) {
            add(list.get(j));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T deletedElement = elements[index];
        int numMoved = size - index - 1;
        System.arraycopy(elements, index + 1, elements, index, numMoved);
        elements[--size] = null;
        return deletedElement;
    }

    @Override
    public T remove(T element) {
        int countOfEqualElements = 0;
        for (int i = 0; i < size; i++) {
            if (element == elements[i] || (elements[i] != null && elements[i].equals(element))) {
                remove(i);
                countOfEqualElements++;
                break;
            } else {
                continue;
            }
        }
        if (countOfEqualElements == 0) {
            throw new NoSuchElementException("There is no such element");
        }
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public boolean checkIndex(int index) {
        if (index < size && index >= 0) {
            return true;
        }
        throw new ArrayListIndexOutOfBoundsException("Index is not valid");
    }
}
