package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int NEW_ARRAY_SIZE = 10;
    private static final int NEW_ARRAY_LIST_SIZE = 0;
    private Object[] array;
    private int size;

    public ArrayList() {
        array = new Object[NEW_ARRAY_SIZE];
        size = NEW_ARRAY_LIST_SIZE;
    }

    @Override
    public void add(T value) {
        if (size >= array.length) {
            array = grow();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (indexValidation(index)) {
            throw new ArrayListIndexOutOfBoundsException("ArrayList index out of bounds");
        }
        if (size == array.length) {
            array = grow();
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
        if (indexValidation(index)) {
            throw new ArrayListIndexOutOfBoundsException("ArrayList index out of bounds");
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (indexValidation(index)) {
            throw new ArrayListIndexOutOfBoundsException("ArrayList index out of bounds");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (indexValidation(index)) {
            throw new ArrayListIndexOutOfBoundsException("ArrayList index out of bounds");
        }
        T removed = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return removed;

    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i] == element || (array[i] != null && array[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] grow() {
        Object[] growedArray = new Object[array.length + (array.length >> 1)];
        System.arraycopy(array, 0, growedArray, 0, array.length);
        return growedArray;
    }

    private boolean indexValidation(int index) {
        return index < 0 || index >= size;
    }
}
