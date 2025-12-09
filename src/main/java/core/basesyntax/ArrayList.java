package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double TIMES_TO_GROW = 1.5;
    private int capacity = 10;
    private T[] arr;
    private int size = 0;

    public ArrayList() {
        arr = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size() >= arr.length) {
            grow();
        }
        arr[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "The index is invalid:" + index);
        }
        if (size() >= arr.length) {
            grow();
        }
        for (int i = size; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index] = value;
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
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "The index is invalid:" + index);
        }
        return arr[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "The index is invalid:" + index);
        }
        arr[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "The index is invalid:" + index);
        }
        final T value = arr[index];
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        size--;
        arr[size] = null;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (arr[i] == null) {
                    return remove(i);
                }
            } else {
                if (element.equals(arr[i])) {
                    return remove(i);
                }
            }
        }
        throw new NoSuchElementException("No such element"
                + "present in the list: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void grow() {
        capacity = (int) (capacity * TIMES_TO_GROW);
        arr = Arrays.copyOf(arr, capacity);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
