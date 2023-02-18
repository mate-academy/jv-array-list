package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int SIZE_MULTIPLIER = 2;
    private static final int NON_EXISTENT_INDEX = -1;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            elements = grow();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkAddIndex(index);
        if (size == elements.length) {
            elements = grow();
        }
        System.arraycopy(elements, index,
                elements, index + 1,
                size - index);
        elements[index] = value;
        size++;
    }

    public T[] grow() {
        T[] elements = (T[]) new Object[size * SIZE_MULTIPLIER];
        System.arraycopy(this.elements, 0, elements, 0, size);
        return elements;
    }

    public void checkAddIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Your index was " + index
                    + ", but size is " + size);
        }
    }

    public void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Your index was " + index
                    + ", but size is " + size);
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
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
        return removalLogic(index);
    }

    @Override
    public T remove(T element) {
        int index = findIndexByElement(element);
        if (index >= 0) {
            return removalLogic(index);
        }
        throw new NoSuchElementException("There is no such element ["
                + element + "] in the list");
    }

    private T removalLogic(int index) {
        size--;
        T element = elements[index];
        System.arraycopy(elements, index + 1,
                elements, index,
                size - index);
        elements[size] = null;
        return element;
    }

    private int findIndexByElement(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
            return NON_EXISTENT_INDEX;
        }
        for (int i = 0; i < size; i++) {
            if (element.equals(elements[i])) {
                return i;
            }
        }
        return NON_EXISTENT_INDEX;
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
