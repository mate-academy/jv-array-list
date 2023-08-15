package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int position;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        position = 0;
    }

    @Override
    public void add(T value) {
        if (isFull()) {
            grow();
        }
        elements[position++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexToAdd(index);
        if (isFull()) {
            grow();
        }
        System.arraycopy(elements, index, elements, index + 1, size() - index);
        elements[index] = value;
        position++;
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
            System.arraycopy(elements, index + 1, elements, index, position - (index + 1));
        }
        elements[--position] = null;
        return toRemove;
    }

    @Override
    public T remove(T element) {
        return remove(getIndexByValue(element));
    }

    @Override
    public int size() {
        return position;
    }

    @Override
    public boolean isEmpty() {
        return size() <= 0;
    }

    private boolean isFull() {
        return size() >= elements.length;
    }

    private void grow() {
        int newLength = size() + (size() >> 1);
        Object[] tempArr = new Object[newLength];
        System.arraycopy(elements, 0, tempArr, 0, size());
        elements = tempArr;
    }

    private void checkIndexToAdd(int index) {
        if (index > size() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private void checkIndexToGet(int index) {
        if (index >= size() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private int getIndexByValue(T value) {
        for (int i = 0; i < size(); i++) {
            T current = (T) elements[i];
            if ((current != null && current.equals(value)) || current == value) {
                return i;
            }
        }
        throw new NoSuchElementException("No such value in list");
    }
}
