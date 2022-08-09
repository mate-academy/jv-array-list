package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initCapacity) {
        if (initCapacity <= 0) {
            throw new ArrayListIndexOutOfBoundsException("Illegal argument to create array "
                    + initCapacity);
        }
        elements = new Object[initCapacity];
    }

    @Override
    public void add(T value) {
        resizeIfFull();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, size);
        resizeIfFull();
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
        checkEqualsIndex(index, size);
        checkIndex(index, size);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkEqualsIndex(index, size);
        resizeIfFull();
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkEqualsIndex(index, size);
        T removedElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = 0;
        boolean noElement = false;
        for (Object s : elements) {
            if (element == null && s == null) {
                noElement = true;
                break;
            } else if (s == null) {
                index++;
                continue;
            } else if (s.equals(element)) {
                noElement = true;
                break;
            }
            index++;
        }
        if (!noElement) {
            throw new NoSuchElementException("There are no more elements remaining!");
        }
        checkIndex(index, size);
        T removedElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index);
        size--;
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

    private void resizeIfFull() {
        if (elements.length == size) {
            Object[] newArray = new Object[elements.length + (elements.length >> 1)];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    private void checkIndex(int index, int size) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Passed index is invalid " + index);
        }
    }

    private void checkEqualsIndex(int index, int size) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Passed index is invalid " + index);
        }
    }
}
