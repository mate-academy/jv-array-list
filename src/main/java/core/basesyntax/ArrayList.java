package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] data;

    public ArrayList() {
        data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        grow();
        if (index >= 0 && index <= size) {
            System.arraycopy(data, index, data, index + 1, size - index);
            data[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("You enter wrong index " + index);
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
            return data[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("You enter wrong index " + index);
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            data[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("You enter wrong index " + index);
        }
    }

    @Override
    public T remove(int index) {
        if (index >= 0 && index < size) {
            T removedValue = data[index];
            System.arraycopy(data, index + 1, data, index, size - index - 1);
            size--;
            return removedValue;
        } else {
            throw new ArrayListIndexOutOfBoundsException("You enter wrong index " + index);
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == data[i] || element != null && element.equals(data[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("You enter wrong value");
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
        if (size == data.length) {
            int firstCapacity = data.length;
            int newCapacity = firstCapacity + (firstCapacity >> 1);
            data = Arrays.copyOf(data, newCapacity);
        }
    }
}
