package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] data;

    public ArrayList() {
        data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        checkIndexFoAdd(index);
        if (size == data.length) {
            grow();
        }
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
        size++;
    }

    public void checkIndexFoAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index of the bounds "
                    + size + " Check your input index!"
                    + index);
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
        checkInvalidIndex(index);
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        checkInvalidIndex(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        checkInvalidIndex(index);
        T item = data[index];
        System.arraycopy(data, index + 1, data, index, size - 1 - index);
        size--;
        return item;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, data[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element");
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
        T[] newItems = (T[]) new Object[(int) (data.length * 1.5)];
        System.arraycopy(data, 0, newItems, 0, size);
        data = newItems;
    }

    private void checkInvalidIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index of the bounds "
                    + size + " Check your input index!"
                    + index);
        }
    }
}
