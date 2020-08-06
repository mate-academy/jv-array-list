package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        elementData = (T[]) new Object[capacity];
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elementData.length) {
            int newCapacity = (minCapacity * 3) / 2 + 1;
            Object[] oldData = elementData;
            elementData = (T[]) new Object[newCapacity];
            System.arraycopy(oldData, 0, elementData, 0, size);
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
        ensureCapacity(size + 1);
        size++;
        elementData[size - 1] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }

        checkIndex(index);
        ensureCapacity(size);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        size++;
        elementData[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        ensureCapacity(size + list.size());
        size += list.size();
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
        throw new NoSuchElementException("There is no such element in the ArrayList");
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
