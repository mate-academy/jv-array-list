package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private int size;
    private T[] dataValue;

    public ArrayList() {
        dataValue = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (size < dataValue.length) {
            dataValue[size] = value;
            size++;
        } else {
            grow();
            dataValue[size] = value;
            size++;
        }
    }

    @Override
    public void add(T value, int index) {
        if (size == dataValue.length) {
            grow();
        }
        if (index >= 0 && index <= size) {
            System.arraycopy(dataValue, index, dataValue, index + 1, size - index);
            dataValue[index] = value;
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
            return dataValue[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Wrong Index");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            dataValue[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
    }

    @Override
    public T remove(int index) {
        T removed;
        if (index >= 0 && index < size) {
            removed = dataValue[index];
            System.arraycopy(dataValue, index + 1, dataValue, index, size - index - 1);
            size--;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Wrond index");
        }
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < dataValue.length; i++) {
            if (element == dataValue[i] || element != null && element.equals(dataValue[i])) {
                T removed = dataValue[i];
                System.arraycopy(dataValue, i + 1, dataValue, i, size - i - 1);
                size--;
                return removed;
            }
        }
        throw new NoSuchElementException("No such element in arraylist");
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
        dataValue = Arrays.copyOf(dataValue, dataValue.length + (dataValue.length >> 1));
    }
}
