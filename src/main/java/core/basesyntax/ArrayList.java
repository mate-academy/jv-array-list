package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resize();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0 && index <= size) {
            resize();
            System.arraycopy(values, index, values, index + 1, size - index);
            values[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Give another index, please");
        }
    }

    @Override
    public void addAll(List<T> list) {
        resize();
        for (int i = 0; i < list.size(); i++) {
            values[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Give another index, please");
        } else {
            return values[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Give another index, please");
        } else {
            values[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        T removeElement;
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Give another index, please");
        } else {
            removeElement = values[index];
            System.arraycopy(values, index + 1, values, index, size - index - 1);
            size--;
        }
        return removeElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < values.length; i++) {
            if (element == values[i] || element != null && element.equals(values[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Such element doesn't exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        if (size == values.length) {
            values = Arrays.copyOf(values,values.length + (values.length >> 1));
        }
    }
}
