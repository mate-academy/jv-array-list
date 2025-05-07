package core.basesyntax;

import static java.util.Arrays.copyOf;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private int size;
    private T[] array;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (size < array.length) {
            array[size] = value;
            size++;
        } else {
            grow();
            array[size] = value;
            size++;
        }
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
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
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
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            array[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
    }

    @Override
    public T remove(int index) {
        if (index < size && index >= 0) {
            T removed = array[index];
            System.arraycopy(array, index + 1, array, index, size - index - 1);
            size--;
            return removed;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == element || array[i] != null && array[i].equals(element)) {
                System.arraycopy(array, i + 1, array, i, size - i - 1);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("Wrong index");
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
        if (size == array.length) {
            array = copyOf(array, (int) (size * 1.5));
        }
    }
}
