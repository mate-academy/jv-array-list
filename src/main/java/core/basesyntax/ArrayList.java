package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT = 10;
    private static final double STANDART_GROW = 1.5;
    private int size;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT];
    }

    @Override
    public void add(T value) {
        capacityEnsures();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Can't add element on position " + index);
        }
        capacityEnsures();
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
        capacityChecking(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        capacityChecking(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        capacityChecking(index);
        final T temp = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return temp;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elements[i] == element)
                    || (elements[i] != null
                    && elements[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can't found element by value " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void capacityEnsures() {
        if (size == elements.length) {
            int capacity = (int) (elements.length * STANDART_GROW);
            T[] tempArray = (T[]) new Object[capacity];
            System.arraycopy(elements, 0, tempArray, 0, size);
            elements = tempArray;
        }
    }

    private void capacityChecking(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("This index out of bounds " + index);
        }
    }
}
