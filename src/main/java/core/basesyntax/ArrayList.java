package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] values;
    private int size;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initCapacity) {
        if (initCapacity <= 0) {
            throw new IllegalArgumentException();
        }
        values = (T[]) new Object[initCapacity];
    }

    @Override
    public void add(T value) {
        resizeIfNeed();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        resizeIfNeed();
        System.arraycopy(values, index, values, index + 1, size - index);
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
        checkIndex(index);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        values[index] = value;

    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedValue = (T) values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(values[i], value)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Incorrect element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean checkIndex(int index) throws ArrayListIndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index");
        }
        return false;
    }

    private boolean checkIndexForAdd(int index) throws ArrayListIndexOutOfBoundsException {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index");
        }
        return false;
    }

    private void resizeIfNeed() {
        if (values.length == size) {
            T[] newArray = (T[]) new Object[(int) (values.length * 1.5)];
            System.arraycopy(values, 0, newArray, 0, size);
            values = newArray;
        }
    }
}
