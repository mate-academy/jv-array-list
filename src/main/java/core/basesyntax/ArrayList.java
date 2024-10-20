package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MIN_ARRAY_SIZE = 10;
    private Object[] arr;
    private int size;

    public ArrayList() {
        this.arr = new Object[MIN_ARRAY_SIZE];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        arr[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0 && index <= size) {
            ensureCapacity();
            System.arraycopy(arr, index, arr, index + 1, size - index);
            arr[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
    }

    @Override
    public void addAll(List<T> list) {
        ensureCapacity(list.size());
        for (int i = 0; i < list.size(); i++) {
            arr[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            return (T) arr[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            arr[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
    }

    @Override
    public T remove(int index) {
        if (index >= 0 && index < size) {
            T removedElement = (T) arr[index];
            System.arraycopy(arr, index + 1, arr, index, size - index - 1);
            arr[--size] = null;
            return removedElement;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == null && element == null) {
                return remove(i);
            }
            if (arr[i] != null && arr[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (size == arr.length) {
            arr = Arrays.copyOf(arr, (int) (arr.length * 1.5));
        }
    }

    private void ensureCapacity(int additionalSize) {
        if (size + additionalSize > arr.length) {
            arr = Arrays.copyOf(arr, (int) ((size + additionalSize) * 1.5));
        }
    }
}
