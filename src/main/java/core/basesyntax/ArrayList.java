package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] values;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        values = (T[]) new Object[DEFAULT_SIZE];
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newSize;
        if (size >= DEFAULT_SIZE) {
            newSize = size + (size / 2);
        } else {
            newSize = size + 1;
        }
        T[] newArray = (T[]) new Object[newSize];
        System.arraycopy(values, 0, newArray, 0, size);
        values = newArray;
    }

    private void checkIndex(int index) {
        if (size < index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    @Override
    public void add(T value) {
        resize();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        resize();
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (!list.isEmpty()) {
            if (size + list.size() >= DEFAULT_SIZE) {
                resize();
            }
            for (int i = 0; i < list.size(); i++) {
                add(list.get(i));
            }
        }
    }

    @Override
    public T get(int index) {
        try {
            checkIndex(index);
            return values[index];
        } catch (Exception e) {
            throw new ArrayListIndexOutOfBoundsException("Index is not correct: " + index);
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is not correct: " + index);
        }
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        try {
            final T removed = values[index];
            System.arraycopy(values, index + 1, values, index, size - index - 1);
            values[size - 1] = null;
            size--;
            return removed;
        } catch (Exception e) {
            throw new ArrayListIndexOutOfBoundsException("Index is not correct: " + index);
        }
    }

    @Override
     public T remove(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (values[i] == null) {
                    return remove(i);
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(values[i])) {
                    return remove(i);
                }
            }
        }
        throw new NoSuchElementException("No such element: " + element);
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
