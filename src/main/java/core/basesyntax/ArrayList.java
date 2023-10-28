package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final float INCREASE_CAPACITY_PERCENT = 1.5F;
    private static final int INCREASE_CAPACITY_ENSURE = 1;
    private T[] array;
    private int size;

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (checkCapacity(size + 1)) {
            ensureCapacity(size + 1);
        }
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkExclusiveIndex(index);
        if (checkCapacity(size + 1)) {
            ensureCapacity(size + 1);
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
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
        checkInclusiveIndex(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkInclusiveIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkInclusiveIndex(index);
        T removedElement = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && array[i] == null)
                    || (element != null && element.equals(array[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkInclusiveIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    private void checkExclusiveIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    private boolean checkCapacity(int index) {
        return index > array.length;
    }

    private void ensureCapacity(int minCapacity) {
        int newCapacity = (int) ((array.length * INCREASE_CAPACITY_PERCENT)
                + INCREASE_CAPACITY_ENSURE);
        T[] newArray = (T[])new Object[newCapacity];
        if (size >= 0) {
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }
}
