package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index should be in range from 0 to "
                    + size());
        }
        grow();
        System.arraycopy(array, index, array, index + 1,
                size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldValue = array[index];
        System.arraycopy(array, index + 1, array, index,
                array.length - (index + 1));
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == array[i] || (element != null
                    && element.equals(array[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found");
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
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    private void grow() {
        if (size == array.length) {
            int newCapacity = array.length + (array.length >> 1);
            T[] newElements = (T[]) new Object[newCapacity];
            System.arraycopy(array, 0, newElements,
                    0, array.length);
            array = newElements;
        }
    }
}
