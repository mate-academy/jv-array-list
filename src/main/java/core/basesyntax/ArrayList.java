package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_ARRAY_SIZE = 10;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE;
    private int size;
    private Object[] array;

    ArrayList() {
        array = new Object[INITIAL_ARRAY_SIZE];
    }

    private void grow(int value) {
        if (size + value > MAX_ARRAY_SIZE) {
            throw new OutOfMemoryError();
        }
        Object[] temp = new Object[size + value];
        System.arraycopy(array, 0, temp, 0, size);
        array = temp;
    }

    private void grow() {
        grow(INITIAL_ARRAY_SIZE / 2);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The array index is out of bounds");
        }
    }

    private void checkAddIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("The array index is out of bounds");
        }
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            grow();
        }
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkAddIndex(index);
        if (size == array.length) {
            grow();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int listSize = list.size();
        if (array.length - size < listSize) {
            grow(listSize);
        }
        for (int i = 0; i < listSize; i++) {
            array[size + i] = list.get(i);
        }
        size += listSize;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T)array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T item = (T)array[index];
        if (size != index + 1) {
            System.arraycopy(array, index + 1, array, index, size - index);
        }
        size--;
        return item;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? array[i] == null : element.equals(array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Such element does not exist in array");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
