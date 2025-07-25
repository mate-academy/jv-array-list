package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final float CAPACITY_INDEX = 1.5f;

    private Object[] data;
    private int size;

    public ArrayList() {
        this.data = new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        innerAdd(value, size);
    }

    @Override
    public void add(T value, int index) {
        checkIndexAdd(index);
        resize();
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            resize();
            data[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldVar;
        oldVar = (T) data[index];
        if (index < size) {
            System.arraycopy(data, index + 1, data, index, --size - index);
        }
        return oldVar;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? data[i] == null : element.equals(data[i])) {
                T elem = (T) data[i];
                remove(i);
                return elem;
            }
        }
        throw new NoSuchElementException("No such element in the List");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        if (data.length == size) {
            int newCapacity = (int) (data.length * CAPACITY_INDEX);
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(data, 0, newArray, 0, size);
            data = newArray;
        }
    }

    private boolean checkIndexAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index is out of bounds");
        }
        return true;
    }

    private boolean checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index is out of bounds");
        }
        return true;
    }

    private void innerAdd(T value, int idx) {
        if (size == data.length) {
            resize();
        }
        if (idx < size) {
            System.arraycopy(data, idx, data, idx + 1, size - idx);
        }
        try {
            data[idx] = value;
            size++;
        } catch (IndexOutOfBoundsException e) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Adding new element at index is failed: index out of bounds", e);
        }
    }

}
