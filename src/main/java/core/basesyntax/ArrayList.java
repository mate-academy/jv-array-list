package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int GROW_STEP = 1;
    private int size;
    private Object[] array;

    public ArrayList() {
        array = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            grow();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index + " is not valid for size " + size + ".");
        }
        if (size == array.length) {
            grow();
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
        validateIndex(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        Object removed = array[index];
        size--;
        System.arraycopy(array, index + 1, array, index, size - index);
        return (T)removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, array[i])) {
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

    private void grow() {
        Object[] temporary = array;
        array = new Object[size + (size >> GROW_STEP)];
        System.arraycopy(temporary, 0, array, 0, size);
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index + " is not valid for size " + size + ".");
        }
    }
}
