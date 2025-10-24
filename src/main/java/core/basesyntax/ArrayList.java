package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPASITY = 10;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPASITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        changeSizeOfArray();

        elements[size] = value;
        size++;
    }

    private void changeSizeOfArray() {
        if (size == elements.length) {
            final int dividedBy = 2;
            int newCapacity = elements.length + elements.length / dividedBy;
            Object[] newArray = new Object[newCapacity];

            for (int i = 0; i < size; i++) {
                newArray[i] = elements[i];
            }

            elements = newArray;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("invalid index");
        }

        changeSizeOfArray();

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int listSize = list.size();

        for (int i = 0; i < listSize; i++) {
            changeSizeOfArray();

            elements[size++] = list.get(i);
        }
    }

    private void checkIndex(int ind) {
        if (ind < 0 || ind >= size) {
            throw new ArrayListIndexOutOfBoundsException("invalid index");
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        checkIndex(index);

        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);

        elements[index] = value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);

        T oldElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;

        return oldElement;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(T element) {
        int index = -1;

        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    index = i;
                    break;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {
                    index = i;
                    break;
                }
            }
        }

        if (index == -1) {
            throw new NoSuchElementException("element not found");
        }

        T removedElement = (T) elements[index];

        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;

        return removedElement;
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
