package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;
    private int capacity;

    public ArrayList() {
        elementData = ((T[]) new Object[DEFAULT_CAPACITY]);
        capacity = DEFAULT_CAPACITY;
    }

    @Override
    public void add(T value) {
        checkCapacity();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        if (index == size) {
            add(value);
        } else {
            checkCapacity();
            addWithShift(value, index);
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
        T deletedElement = get(index);
        checkAndRemove(index);
        return deletedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (isEqual(elementData[i], element)) {
                T storedElement = elementData[i];
                checkAndRemove(i);
                return storedElement;
            }
        }
        throw new NoSuchElementException("Can't find element " + element + " in storage");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't reach index: " + index
                    + "out of capacity:" + size);
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't reach index: " + index
                    + "out of capacity:" + size);
        }
    }

    private void addWithShift(T value, int index) {
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    private void checkCapacity() {
        if (size == capacity) {
            expandCapacity();
        }
    }

    private void expandCapacity() {
        int getNewCapacity = newCapacity();
        T[] newElementData = ((T[]) new Object[getNewCapacity]);
        System.arraycopy(elementData, 0, newElementData, 0, size);
        elementData = newElementData;
        capacity = getNewCapacity;
    }

    private int newCapacity() {
        return capacity + (capacity >> 1);
    }

    private void checkAndRemove(int index) {
        if (index == (size - 1)) {
            removeLast();
        } else {
            removeAndShift(index);
        }
    }

    private void removeLast() {
        elementData[size - 1] = null;
        size--;
    }

    private void removeAndShift(int index) {
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        size--;
    }

    private boolean isEqual(T a, T b) {
        return (a == b) || (a != null && a.equals(b));
    }
}

