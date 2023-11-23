package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T [] array = (T[]) new Object[DEFAULT_CAPACITY];

    public void resize() {
        if (array.length == size) {
            int newSize = (int)(array.length * 1.5);
            array = Arrays.copyOf(array, newSize);
        }
    }

    @Override
    public void add(T value) {
        resize();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is not valid. ");
        } else if (index + 1 < size) {
            resize();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            resize();
            array[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index is not valid. ");
        }
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index is not valid. ");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        T indexOld;
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index is not valid. ");
        }
        indexOld = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size - 1] = null;
        size--;
        return indexOld;
    }

    @Override
    public T remove(T element) {
        T indexOld;
        for (int i = 0; i < array.length; i++) {
            if ((array[i] == element) || (array[i] != null && array[i].equals(element))) {
                indexOld = array[i];
                System.arraycopy(array, i + 1, array, i, size - i - 1);
                array[size - 1] = null;
                size--;
                return indexOld;
            }
        }
        throw new NoSuchElementException("Value " + element + " not found!");
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
