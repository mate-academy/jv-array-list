package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_RATE = 1.5;
    private int size;
    private int newCapacity;
    private Object[] data;

    public ArrayList() {
        data = new Object[DEFAULT_CAPACITY];
        size = 0;
        newCapacity = DEFAULT_CAPACITY;
    }

    private void setData(T value, int index) {
        if (isNeedGrow()) {
            grow(this.data.length);
        }
        data[index] = value;
        size++;
    }

    private void checkIndex(int index) {
        if (index > size - 1 || index < 0) { //
            throw new ArrayListIndexOutOfBoundsException("Out of bounds exception");
        }
    }

    private boolean isNeedGrow() {
        return size == newCapacity;
    }

    private Object[] trimToSize(Object[] objects) {
        System.arraycopy(objects, 0, data, 0, size);
        return objects;
    }

    private void grow(int capacity) {
        newCapacity = (int) ((capacity) * GROWTH_RATE);
        Object[] data = new Object[newCapacity];
        System.arraycopy(this.data, 0, data, 0, this.data.length);
        this.data = data;
    }

    private void copyDataWithDecreaseLength(int index) {
        Object[] data = new Object[this.data.length];
        System.arraycopy(this.data, 0, data, 0, index);
        System.arraycopy(this.data, index + 1, data, index, size - 1 - index);
        this.data = trimToSize(data);
    }

    private void copyDataWithIncreaseLength(int index) {
        if (size + 1 == newCapacity) {
            grow(this.data.length);
        }
        Object[] data = new Object[this.data.length];
        System.arraycopy(this.data, 0, data, 0, index);
        System.arraycopy(this.data, index, data, index + 1, size + 1 - index); // ?
        this.data = data;
    }

    @Override
    public void add(T value) {
        if (size == 0) {
            setData(value, size);
            return;
        }
        setData(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (size == 0) {
            setData(value, size);
            return;
        }
        if (index == size) {
            checkIndex(index - 1);
        } else {
            checkIndex(index);
        }
        copyDataWithIncreaseLength(index);
        setData(value, index);
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() > list.size() + size - 1) {
            grow(list.size() + size - 1);
        }
        for (int i = 0; i < list.size(); i++) {
            setData(list.get(i), size);
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
        Object element = data[index];
        copyDataWithDecreaseLength(index);
        size--;
        return (T) element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(data[i], element)) {
                copyDataWithDecreaseLength(i);
                size--;
                return element;
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
}
