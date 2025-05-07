package core.basesyntax;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object [] DEFAULT_EMPTY_ELEMENTDATA = {};
    private int size;
    private Object[] elementData;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int initialSize) {
        if (initialSize > 0) {
            elementData = new Object[initialSize];
        } else if (initialSize == 0) {
            elementData = DEFAULT_EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal capacity");
        }
    }

    public ArrayList(Collection<? extends T> c) {
        Object [] listArray = c.toArray();
        size = listArray.length;
        if (size > 0) {
            elementData = Arrays.copyOf(listArray, size);
        } else {
            elementData = DEFAULT_EMPTY_ELEMENTDATA;
        }
    }

    @Override
    public void add(T value) {
        resizeArrayIfNeeded();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index != size) {
            checkIndex(index, size);
        }
        resizeArrayIfNeeded();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        while (list.size() > elementData.length) {
            int oldCapacity = elementData.length;
            int newCapacity = oldCapacity + oldCapacity / 2;
            elementData = new Object[newCapacity];
        }
        for (int i = 0; i < list.size(); i++) {
            elementData[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index, size);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, size);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, size);
        T deletedElement = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return deletedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == null) {
                if (element == null) {
                    System.arraycopy(elementData, i + 1, elementData, i, size - i - 1);
                    size--;
                    return null;
                }
                continue;
            }
            Object deletedFromArray = elementData[i];
            if (((T) deletedFromArray).equals(element)) {
                System.arraycopy(elementData, i + 1, elementData, i, size - i - 1);
                size--;
                return (T) deletedFromArray;
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

    private void resizeArrayIfNeeded() {
        if (elementData.length == size) {
            increaseCapacity();
        }
    }

    private void increaseCapacity() {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + oldCapacity / 2;
        Object [] newArray = Arrays.copyOf(elementData, newCapacity);
        elementData = newArray;
    }

    private void checkIndex(int index, int size) {
        if (index == 0) {
            return;
        }
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }
}
