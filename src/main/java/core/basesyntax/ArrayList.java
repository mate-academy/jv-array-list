package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double INCREASED = 1.5;
    private static final int CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[CAPACITY];
    }

    @Override
    public void add(T value) {
        if (isFull()) {
            grow();
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexToAdd(index);
        if (isFull()) {
            grow();
        }
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
        checkIndexToGet(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexToGet(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexToGet(index);
        T toRemove = (T) elements[index];
        if (!isFull()) {
            System.arraycopy(elements, index + 1, elements, index, size - (index + 1));
        }
        elements[--size] = null;
        return toRemove;
    }

    @Override
    public T remove(T element) {
        return remove(getIndexByValue(element));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size >= elements.length;
    }

    private void grow() {
        Object[] tempArr = new Object[(int) (elements.length * INCREASED)];
        System.arraycopy(elements, 0, tempArr, 0, size);
        elements = tempArr;
    }

    private void checkIndexToAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private void checkIndexToGet(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private int getIndexByValue(T value) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (elements[i] == value || value != null
                    && value.equals(elements[i])) {
                return i;
            }
        }
        throw new NoSuchElementException("No such value in list");
    }
}
