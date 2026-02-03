package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

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
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("ArrayList index out of bounds");
        }
        if (size == array.length - 1) {
            array = grow();
        }
        for (int i = size - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }
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
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("ArrayList index out of bounds");
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("ArrayList index out of bounds");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("ArrayList index out of bounds");
        }
        T removed = (T) array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return removed;

    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], element)) {
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
        Object[] growedArray = new Object[size + (size << 1)];
        for (int i = 0; i < this.array.length; i++) {
            growedArray[i] = this.array[i];
        }
        return growedArray;
    }
}
