package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_LENGTH = 10;
    private static final double GROW_NUMBER = 1.5;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_ARRAY_LENGTH];;
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            resize();
        }
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        } else if (size == array.length) {
            resize();
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
        return fastRemove(index);
    }

    @Override
    public T remove(T element) {
        if (element == null) {
            size--;
            return null;
        }
        for (int i = 0; i <= size; i++) {
            if (element.equals(array[i])) {
                return fastRemove(i);
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
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private T fastRemove(int index) {
        T elementToRemove = array[index];
        int lengthLastPartArray = size - index;
        int correctLastElement = lengthLastPartArray == 1 ? 1 : 0;
        System.arraycopy(array, index + 1, array, index, lengthLastPartArray - correctLastElement);
        size--;
        return elementToRemove;
    }

    private T[] resize() {
        int currentSize = (int) (array.length * GROW_NUMBER);
        array = Arrays.copyOf(array, currentSize);
        return array;
    }
}
