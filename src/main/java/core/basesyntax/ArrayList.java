package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 10;
    private static final double SIZE_OF_INCREASE = 1.5;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[CAPACITY];
    }

    private void ensureCapacity() {
        if (values.length == size) {
            int newCapacity = (int)(values.length * SIZE_OF_INCREASE);
            values = Arrays.copyOf(values, newCapacity);
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        ensureCapacity();
        for (int i = size; i > index; i--) {
            values[i] = values[i - 1];
        }
        values[index] = value;
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
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        ensureCapacity();
        validateIndex(index);
        T value = values[index];
        if (index < size - 1) {
            System.arraycopy(values, index + 1, values, index, size - index - 1);
        }
        values[--size] = null;
        return value;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index == -1) {
            throw new NoSuchElementException("Element not found");
        }
        return remove(index);
    }

    private int indexOf(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (values[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(values[i])) {
                    return i;
                }
            }
        }
        return -1;
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
