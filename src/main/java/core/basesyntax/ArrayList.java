package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_INDEX = 1.5;

    private T[] array = (T[]) new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        if (rangeCheckForAdd()) {
            grow();
        }

        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (indexCheckForAdd(index)) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + size);
        }

        if (rangeCheckForAdd()) {
            grow();
        }

        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }

        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new RuntimeException("list is null");
        }

        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);

        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);

        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T removeValue = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
        return removeValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? array[i] == null : element.equals(array[i])) {
                return remove(i);
            }
        }

        throw new NoSuchElementException("Element not found: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + size);
        }
    }

    private boolean indexCheckForAdd(int index) {
        return index < 0 || index > size;
    }

    private boolean rangeCheckForAdd() {
        return size == array.length;
    }

    private void grow() {
        T[] newArray = (T[]) new Object[(int) (array.length * GROW_INDEX)];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }
}
