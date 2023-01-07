package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    static final int ARRAY_MIN_SIZE = 10;

    private T[] array = (T[]) new Object[ARRAY_MIN_SIZE];
    private int size;
    private int newCapacity;
    private T[] arrayGrow;

    @Override
    public void add(T value) {
        if (size >= array.length) {
            grow(array);
            array = arrayGrow;
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0 && index <= size) {
            if (size >= array.length) {
                grow(array);
                array = arrayGrow;
            }
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        isIndexValid(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexValid(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (isIndexValid(index)) {
            T removeElement = array[index];
            System.arraycopy(array, index + 1, array, index, size - index - 1);
            size--;
            return removeElement;
        }
        throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == array[i] || array[i] != null && array[i].equals(element)) {
                T removeElement = array[i];
                remove(i);
                return removeElement;
            }
        }
        throw new NoSuchElementException("Element: " + element + " is missing");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow(T[] array) {
        newCapacity = array.length + (array.length >> 1);
        arrayGrow = (T[]) new Object[newCapacity];
        System.arraycopy(array, 0, arrayGrow, 0, size);
    }

    private boolean isIndexValid(int index) {
        if (index >= 0 && index < size) {
            return true;
        }
        throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
    }
}
