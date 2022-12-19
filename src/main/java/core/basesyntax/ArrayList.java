package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_LENGTH = 10;
    private static final double MULTIPLIER = 1.5;
    private int size = 0;
    private Object[] array;

    public ArrayList() {
        array = new Object[DEFAULT_LENGTH];
    }

    public ArrayList(int length) {
        array = new Object[length];
    }

    @Override
    public void add(T value) {
        fixSize();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        fixSize();
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("" + index);
        }
        size++;
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
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
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T value = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i] == null) {
                if (element == null) {
                    remove(i);
                    return null;
                }
                continue;
            }
            if (array[i].equals(element)) {
                remove(i);
                return element;
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

    private void fixSize() {
        if (array.length < size + 2) {
            array = Arrays.copyOf(array, (int) (array.length * MULTIPLIER));
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index not found " + index);
        }
    }
}
