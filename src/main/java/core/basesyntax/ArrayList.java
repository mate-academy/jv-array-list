package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;

    private int size;
    private Object[] elements = new Object[DEFAULT_CAPACITY];

    private void grow() {
        Object[] newElements = new Object[(int) (elements.length * GROWTH_FACTOR)];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = newElements;
    }

    private void grow(int minCapacity) {
        int oldCapacity = elements.length;
        if (minCapacity <= oldCapacity) {
            return;
        }
        int newCapacity = (int) Math.ceil(
                oldCapacity * Math.pow(GROWTH_FACTOR,
                        Math.ceil(Math.log((double) minCapacity
                        / oldCapacity) / Math.log(GROWTH_FACTOR)))
        );
        Object[] newElements = new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    private T removeValue(int index) {
        T removedElement = (T) elements[index];
        int valuesAfter = size - index - 1;
        System.arraycopy(elements, index + 1, elements, index, valuesAfter);
        elements[--size] = null;
        return removedElement;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index: "
                    + index + " out of collection size");
        }
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            grow();
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("index: "
                    + index + " out of collection size");
        }
        if (size == elements.length) {
            grow();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int requiredCapacity = size + list.size();
        grow(requiredCapacity);
        for (int i = 0; i < list.size(); i++) {
            elements[size++] = list.get(i);
        }
    }

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
    public T remove(int index) {
        checkIndex(index);
        return removeValue(index);
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null ? elements[i] == null : element.equals(elements[i]))) {
                return removeValue(i);
            }
        }
        throw new NoSuchElementException("element: "
                + element + ", does not exist in the collection");
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
