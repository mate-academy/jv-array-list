package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_OF_SIZE = 10;
    private int size;
    private Object[] elements;

    public ArrayList () {
        elements = new Object[DEFAULT_OF_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            elements = increaseInSize(elements);
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Cannot add element " + value +
                    ". Index " + index + " is not correct");
        }
        if (size == elements.length) {
            elements = increaseInSize(elements);
        }
        if (index == size) {
            add(value);
        } else {
            System.arraycopy(elements, index, elements, index + 1, size - index);
            elements[index] = value;
            size++;
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
        if (isIndexCorrect(index)) {
            throw new ArrayListIndexOutOfBoundsException("Cannot find element. " +
                    "Index " + index + " is not correct");
        }
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (isIndexCorrect(index)) {
            throw new ArrayListIndexOutOfBoundsException("Cannot find element. " + value +
                    " Index " + index + " is not correct");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (isIndexCorrect(index)) {
            throw new ArrayListIndexOutOfBoundsException("Cannot remove element. " +
                    "Index " + index + " is not correct");
        }
        T element = (T) elements[index];
        if (index != size - 1) {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        }
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size - 1; i++) {
            if (elements[i] == element || (elements[i] != null && elements[i].equals(element))) {
                System.arraycopy(elements, i + 1, elements, i, size - 1);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("Element " + element + " doesn`t exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] increaseInSize (Object[] elements) {
        Object[] increaseInSizeLine = new Object[elements.length + (elements.length / 2)];
        System.arraycopy(elements, 0, increaseInSizeLine, 0, elements.length);
        return increaseInSizeLine;
    }

    private boolean isIndexCorrect (int index) {
        return index < 0 || index >= size;
    }
}
