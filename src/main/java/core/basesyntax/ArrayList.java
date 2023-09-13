package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOT_FOUND = -1;
    private static final double GROW_FACTOR = 1.5;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        settingCapacity();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        settingCapacity();
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
        final T returnValue = elements[index];
        System.arraycopy(elements, index + 1, elements, index, --size - index);
        return returnValue;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index == ELEMENT_NOT_FOUND) {
            throw new NoSuchElementException("Element not found" + element);
        }
        remove(index);
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void settingCapacity() {
        int minCapacity = size + 1;
        int currentCapacity = elements.length;
        if (minCapacity > currentCapacity) {
            T[] newElements = (T[]) new Object[(int) (elements.length * GROW_FACTOR)];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }

    private void checkIndex(int index) {
        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index" + index + "is too large");
        }
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index" + index + "cannot be less than 0");
        }
    }

    private int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && elements[i] == null)
                    || (element != null && element.equals(elements[i]))) {
                return i;
            }
        }
        return ELEMENT_NOT_FOUND;
    }
}
