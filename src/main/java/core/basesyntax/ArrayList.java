package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double INCREASE_MULTIPLIER = 1.5;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkSize();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Choose index less or equal than " + size
                    + "of ArrayList<T>. This index incorrect " + index);
        }
        checkSize();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int additionalLength = list.size();
        for (int i = 0; i < additionalLength; i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkTheIndex(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkTheIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkTheIndex(index);
        T oldValue = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elements[i] || element != null && element.equals(elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("ArrayList don`t consist the element " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean checkSize() {
        if (size == elements.length) {
            insureDefaultInternalCapacity();
        }
        return false;
    }

    private void checkTheIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Choose index less or equal than " + size
                    + "of ArrayList<T>. This index incorrect "
                    + index);
        }
    }

    private void insureDefaultInternalCapacity() {
        T[] newArray = (T[]) new Object[elements.length
                + (int)(elements.length * INCREASE_MULTIPLIER)];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }
}
