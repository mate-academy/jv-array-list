package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private Object[] array = new Object[DEFAULT_CAPACITY];
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

        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);

        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        T value = (T) array[index];

        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }

        size--;
        array[size] = null;

        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, array[i])) {
                return remove(i);
            }
        }

        throw new NoSuchElementException();
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
        Object[] newArray = new Object[(int) (array.length * 1.5)];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }
}
