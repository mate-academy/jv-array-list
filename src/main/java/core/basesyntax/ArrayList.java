package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_RATE = 1.5;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
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
        final T removed = get(index);
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        int index = getIndex(element);
        if (index == -1) {
            throw new NoSuchElementException("There is no element such element");
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throwIndexOutOfBounds(index);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            throwIndexOutOfBounds(index);
        }
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            grow();
        }
    }

    private void grow() {
        Object[] newArray = new Object[(int) (elements.length * GROW_RATE)];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }

    private int getIndex(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || elements[i] != null && elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    private void throwIndexOutOfBounds(int index) {
        throw new ArrayListIndexOutOfBoundsException("Wrong index" + index + "! "
                + "Index value must be less than size");
    }
}
