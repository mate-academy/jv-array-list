package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_SIZE = 10;
    private static final double ARRAY_ADD_SIZE = 1.5;
    private Object[] values;
    private int size;

    public ArrayList() {
        this.values = new Object[ARRAY_SIZE];
    }

    @Override
    public void add(T value) {
        checkingResize();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index != size) {
            checkException(index);
        }
        checkingResize();
        if (index <= size) {
            System.arraycopy(values, index, values, index + 1, size - index);
            values[index] = value;
            size++;
        } else {
            values[size] = value;
            size++;
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
        checkException(index);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        checkException(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkException(index);
        checkingResize();
        Object deleted = values[index];
        System.arraycopy(values, index + 1, values, index, size - index);
        size--;
        return (T) deleted;
    }

    @Override
    public T remove(T element) {
        Object deleted = null;
        boolean isElement = false;
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (values[i] == null && element == null
                    || values[i] != null && values[i].equals(element)) {
                isElement = true;
                index = i;
                deleted = values[i];
                break;
            }
        }
        if (isElement) {
            checkException(index);
            for (int i = index; i < size; i++) {
                if (index == size - 1) {
                    values[i] = null;
                } else {
                    values[i] = values[i + 1];
                }
            }
            size--;
        } else {
            throw new NoSuchElementException("No such element in Array");
        }

        return (T) deleted;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkException(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index of Array is out of bounds");
        }
    }

    private void checkingResize() {
        if (values.length == size) {
            values = Arrays.copyOf(values, (int) (size * ARRAY_ADD_SIZE));
        }
    }
}
