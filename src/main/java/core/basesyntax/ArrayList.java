package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double GROW_MULTIPLY = 1.5;
    private static final int DEFAULT_CAPACITY = 10;
    private static final String ERROR_ELEMENT_NOT_EXIST = "Element does not exist";
    private int size;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growCheck();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        growCheck();
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Element not found at " + index);
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
        checkedException(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkedException(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (isNotValidValue(index)) {
            throw new ArrayListIndexOutOfBoundsException("Element does not exist in position"
                    + index);
        }
        T tempVal = array[index];
        System.arraycopy(array, index + 1, array, index, --size - index);
        return tempVal;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i] == element
                    || array[i] != null && array[i].equals(element)) {
                return remove(i);
            }
        }

        throw new NoSuchElementException("Element does not exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growCheck() {
        if (size == array.length) {
            grow();
        }
    }

    private void grow() {
        int newCapacity = (int)(array.length * GROW_MULTIPLY);
        T[] arrayCopy = (T[]) new Object[newCapacity];
        System.arraycopy(this.array, 0, arrayCopy, 0, size);
        this.array = arrayCopy;
    }

    private boolean isNotValidValue(int index) {
        return index >= size || index < 0;
    }

    private void checkedException(int index) {
        if (isNotValidValue(index)) {
            throw new ArrayListIndexOutOfBoundsException(ERROR_ELEMENT_NOT_EXIST);
        }
    }
}
