package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private int capacity;
    private Object[] elements;

    public ArrayList() {
        capacity = DEFAULT_CAPACITY;
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        ensureCapacity();
        System.arraycopy(elements, 0, elements, 1, size);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add((T) list.get(i));
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
        T removed = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return removed;
    }

    @Override
    public T remove(T element) {

        for (int i = 0; i < size; i++) {
            if (elements[i] == element || elements[i] != null && elements[i].equals(element)) {
                return remove(i);
            }

        }
        throw new NoSuchElementException("The element not founded. Please choose another element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (size >= capacity) {
            increaseCapacity();
        }
    }

    private void increaseCapacity() {
        capacity = (capacity * 3) / 2 + 1;
        Object[] newArray = new Object[capacity];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }

    private void checkIndex(int index) {
        if ((index > size - 1 || index < 0)) {
            getArrayListIndexOutOfBoundsException(index);
        }
    }

    private void checkIndexForAdd(int index) {
        if ((index > size || index < 0)) {
            getArrayListIndexOutOfBoundsException(index);
        }
    }

    private void getArrayListIndexOutOfBoundsException(int index) {

            throw new ArrayListIndexOutOfBoundsException("Wrong input data: Index - "
                    + index + " is out of boundaries");

    }
}

