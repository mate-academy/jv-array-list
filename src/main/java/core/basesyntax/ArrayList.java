package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MIN_CAPACITY = 10;
    private static final double GROWTH_COEFFICIENT = 1.5;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[MIN_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfFull();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index + " is out of bounds for length " + size + "!");
        }
        growIfFull();
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
        T oldValue = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (areElementsEquals(element, elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no " + element + " in this ArrayList!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfFull() {
        if (size == elements.length) {
            int newCapacity = (int) (elements.length * GROWTH_COEFFICIENT);
            T[] biggerArray = (T[]) new Object[newCapacity];
            System.arraycopy(elements, 0, biggerArray, 0, elements.length);
            elements = biggerArray;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index + " is out of bounds " + size + "!");
        }
    }

    private boolean areElementsEquals(T element, T otherElement) {
        return ((element == null && otherElement == null)
                || (element != null && element.equals(otherElement)));
    }
}
