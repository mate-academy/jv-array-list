package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;
    private Object[] array;
    private int size;

    public ArrayList() {
        array = new Object[INITIAL_SIZE];
        size = 0;
    }

    public ArrayList(int size) {
        if (size < 0) {
            throw new RuntimeException("Provide size less than 0");
        } else if (size == 0) {
            array = new Object[INITIAL_SIZE];
        } else {
            array = new Object[size];
        }
    }

    private void grow() {
        Object[] resizedArray = new Object[(array.length * 3) / 2];
        System.arraycopy(array, 0, resizedArray, 0, array.length);
        array = resizedArray;
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index <= size && index >= 0) {
            if (size == array.length) {
                grow();
            }
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
            size++;
        } else {
            throw new ArrayIndexOutOfBoundsException();
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
        if (!isIndexInBound(index)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (!isIndexInBound(index)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (!isIndexInBound(index)) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T deletedElement = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return deletedElement;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (array[i] == t || (array[i] != null && array[i].equals(t))) {
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

    private boolean isIndexInBound(int index) {
        return index < size && index >= 0;
    }
}
