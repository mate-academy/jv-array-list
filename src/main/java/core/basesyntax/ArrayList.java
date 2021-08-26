package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private int capacity = 10;
    private int size = 0;
    private Object[] elements = new Object[capacity];

    @Override
    public void add(T value) {
        checkGrow();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexAddRemove(index);
        checkGrow();
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
        checkIndexGetSet(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexGetSet(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexAddRemove(index);
        T removedItem = get(index);
        int length = size == capacity ? size - index - 1 : size - index;
        System.arraycopy(elements, index + 1, elements, index, length);
        elements[--size] = null;
        return removedItem;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == null ? element == null
                    : elements[i].equals(element)) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        capacity += capacity >> 1;
        Object[] newArray = new Object[capacity];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }

    private void checkIndexAddRemove(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("ArrayListIndexOutOfBoundsException");
        }
    }

    private void checkIndexGetSet(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("ArrayListIndexOutOfBoundsException");
        }
    }

    private void checkGrow() {
        if (size == capacity) {
            grow();
        }
    }
}
