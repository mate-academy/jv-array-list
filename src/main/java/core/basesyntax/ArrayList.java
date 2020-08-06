package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;
    private int capacity;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
    }

    public ArrayList(int capacity) {
        this.capacity = capacity;
        elementData = (T[]) new Object[capacity];
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > capacity) {
            capacity = (minCapacity * 3) / 2 + 1;
            Object[] oldData = elementData;
            elementData = (T[]) new Object[capacity];
            System.arraycopy(oldData, 0, elementData, 0, size - 1);
        }
    }

    private boolean checkIndex(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException("Wrong index of array");
        }
        return true;
    }

    @Override
    public void add(T value) {
        size++;
        ensureCapacity(size);
        elementData[size - 1] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        size++;
        ensureCapacity(size);
        System.arraycopy(elementData, index, elementData, index + 1, size - index - 1);
        elementData[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        size += list.size();
        ensureCapacity(size);
        T[] array = (T[]) new Object[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        System.arraycopy(array, 0, elementData, size - array.length, array.length);
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[size-- - 1] = null;
        return removedElement;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elementData[i], t)) {
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
}
