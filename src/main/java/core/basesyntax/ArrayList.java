package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elements;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            increaseSize();
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (size == elements.length) {
            increaseSize();
        }
        if (index == size) {
            add(value);
        } else {
            isIndexCorrect(index);
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
        isIndexCorrect(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexCorrect(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexCorrect(index);
        T element = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || elements[i] != null && elements[i].equals(element)) {
                return remove(i);
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

    private void increaseSize() {
        Object[] increaseSizeLine = new Object[elements.length + (elements.length / 2)];
        System.arraycopy(elements, 0, increaseSizeLine, 0, elements.length);
        elements = increaseSizeLine;
    }

    private boolean isIndexCorrect(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is not correct");
        }
        return false;
    }
}
