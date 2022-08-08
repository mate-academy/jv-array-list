package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int MAX_LENGHT = 10;
    private Object[] array;
    private int size;

    public ArrayList() {
        array = new Object[MAX_LENGHT];
    }

    @Override
    public void add(T value) {
        grow();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        validateIndex(index, size + 1);
        grow();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
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
        validateIndex(index, size);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index, size);
        array[index] = value;

    }

    @Override
    public T remove(int index) {
        validateIndex(index, size);
        T removed = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        T removed;
        int index = getIndex(element);
        if (index < 0) {
            throw new NoSuchElementException("Element is not found in the array");
        }
        removed = (T) array[index];
        remove(index);
        return removed;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void validateIndex(int index, int size) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Wrong index out of bounds, please check index!");
        }
    }

    private void grow() {
        if (size == array.length) {
            Object[] newArray = new Object[array.length + (array.length / 2)];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    private int getIndex(T value) {
        for (int i = 0; i < size; i++) {
            if (value != null && value.equals(array[i]) || value == array[i]) {
                return i;

            }
        }
        return -1;
    }
}
