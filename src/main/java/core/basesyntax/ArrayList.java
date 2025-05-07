package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    static final int DEFAULT_ARRAY_SIZE = 10;
    private int size;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_ARRAY_SIZE];
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
        if (size == array.length) {
            grow();
        }
        if (index >= 0 && index <= size) {
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Out of bounds");
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
        if (index >= 0 && index < size) {
            return array[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Out of bounds");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || size <= index) {
            throw new ArrayListIndexOutOfBoundsException("Out of bounds");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= 0 && index < size) {
            T valueFromIndex = array[index];
            System.arraycopy(array, index + 1, array, index, size - index - 1);
            size--;
            return valueFromIndex;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Out of bounds");
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], element)) {
                return remove(i);
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

    public void grow() {
        if (size == array.length) {
            int newCapacity = (int)(size * 1.5);
            array = Arrays.copyOf(array, newCapacity);
        }
    }
}
