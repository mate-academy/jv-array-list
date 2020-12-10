package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int LENGTH_ARRAY = 10;
    private static final double LENGTH_GROW = 1.5;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[LENGTH_ARRAY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size() >= array.length) {
            grow();
        }
        array[size()] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size || isIndexOfBounds(index)) {
            if (size() == array.length) {
                grow();
            }
            System.arraycopy(array, index, array, index + 1, size() - index);
            array[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int index = 0; index < list.size(); index++) {
            add(list.get(index));
        }
    }

    @Override
    public T get(int index) {
        if (isIndexOfBounds(index)) {
            return array[index];
        }
        return null;
    }

    @Override
    public void set(T value, int index) {
        if (isIndexOfBounds(index)) {
            array[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        T value = null;
        if (isIndexOfBounds(index)) {
            value = array[index];
            System.arraycopy(array, index + 1, array, index, size() - index - 1);
            size--;
        }
        return value;
    }

    @Override
    public T remove(T t) {
        for (int index = 0; index < size; index++) {
            if (array[index] == t || (array[index] != null && array[index].equals(t))) {
                System.arraycopy(array, index + 1, array, index, size() - index - 1);
                size--;
                return t;
            }
        }
        throw new NoSuchElementException("ArrayList doesn't have this element " + t);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void grow() {
        array = Arrays.copyOf(array, (int) (size() * LENGTH_GROW));
    }

    private boolean isIndexOfBounds(int index) {
        if (index >= 0 && index < size) {
            return true;
        }
        throw new ArrayIndexOutOfBoundsException("ArrayList doesn't have this index " + index);
    }
}
