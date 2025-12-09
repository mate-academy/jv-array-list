package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double TIMES_TO_GROW = 1.5;
    private static final int DEFAULT_CAPACITY = 10;
    private T[] arr;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        arr = (T[]) new Object[DEFAULT_CAPACITY];
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
        indexValidationForAdd(index);
        if (size() >= arr.length) {
            grow();
        }
        System.arraycopy(arr, index, arr, index + 1, size - index);
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
        indexValidationGeneral(index);
        return arr[index];
    }

    @Override
    public void set(T value, int index) {
        indexValidationGeneral(index);
        arr[index] = value;
    }

    @Override
    public T remove(int index) {
        indexValidationGeneral(index);
        final T value = arr[index];
        System.arraycopy(arr, index + 1, arr, index, size - index - 1);
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

    @SuppressWarnings("unchecked")
    public void grow() {
        T[] newArr = (T[]) new Object[(int) (arr.length * TIMES_TO_GROW)];
        if (size >= 0) {
            System.arraycopy(arr, 0, newArr, 0, size);
        }
        arr = newArr;
    }

    private void indexValidationGeneral(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "The index is invalid:" + index);
        }
    }

    private void indexValidationForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "The index is invalid:" + index);
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
