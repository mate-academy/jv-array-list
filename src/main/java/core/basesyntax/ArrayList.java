package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] data;
    private int size;

    public ArrayList() {
        data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkGrow();
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        checkGrow();
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
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
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T result = data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        --size;
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(data[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can't find the element");
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
        int oldCapacity = data.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        T[] newData = (T[]) new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, oldCapacity);
        data = newData;
    }

    private void checkGrow() {
        if (size == data.length) {
            grow();
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't find element by index");
        }
    }

    private void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index Out Of Bounds");
        }
    }
}
