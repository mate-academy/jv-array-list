package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double CAPACITY_INDEX = 1.5;
    private int size = 0;
    private T[] values;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resize();
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        resize();
        System.arraycopy(values,0, values, 1,size);
        values[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            resize();
            values[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T element;
        element = get(index);
        shiftToLeft(index);
        return (T) element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((values[i] == element) || (values[i] != null && values[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("the entered element is missing");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void resize() {
        if (values.length == size) {
            int newCapacity = (int) (values.length * CAPACITY_INDEX);
            T[] newArray = (T[]) new Object[newCapacity];
            System.arraycopy(values, 0, newArray, 0, size);
            values = newArray;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
    }

    private void shiftToLeft(int start) {
        size--;
        if (size < 0) {
            return;
        }
        if (size != start) {
            System.arraycopy(values, start + 1, values, start, size - start);
        }
        values[size] = null;
    }
}
