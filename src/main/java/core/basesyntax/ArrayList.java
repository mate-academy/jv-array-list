package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_DATA = {};
    private T[] elements;
    private int size;

    public ArrayList() {
        this.elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (elements.length == size) {
            grow();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        if (elements.length == size) {
            grow();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        T[] listElements = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            listElements[i] = list.get(i);
        }
        System.arraycopy(listElements, 0, elements, size, listElements.length);
        size += listElements.length;
    }

    @Override
    public T get(int index) {
        rangeCheckForGet(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Insert impossible");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheckForAdd(index);
        T oldValue = elements[index];
        T[] newElements = elements;
        fastRemove(newElements, index);
        return oldValue;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if ((t == null && elements[i] == null)
                    || (t != null && t.equals(elements[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Item not found");
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
        int oldCapacity = elements.length;
        if (oldCapacity > 0 || elements != EMPTY_DATA) {
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            T[] newElements = (T[]) new Object[newCapacity];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Your index is more than size "
                    + "or index is less than 0");
        }
    }

    private void rangeCheckForGet(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Your index is more than size "
                    + "or index is less than 0");
        }
    }

    private void fastRemove(T[] array, int index) {
        if (--size > index) {
            System.arraycopy(array, index + 1, array, index, size - index);
        }
        rangeCheckForAdd(size);
        array[size] = null;
    }
}
