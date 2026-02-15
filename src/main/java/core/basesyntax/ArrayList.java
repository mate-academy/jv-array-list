package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        if (elementData.length == size) {
            growIfFull();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException();
        }

        if (size == elementData.length) {
            growIfFull();
        }

        System.arraycopy(elementData, index, elementData, index + 1, size - index);

        elementData[index] = value;
        this.size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new NullPointerException("List must not be null.");
        }
        int n = list.size();
        if (n == 0) {
            return;
        }
        while (elementData.length < size + n) {
            growIfFull();
        }
        for (int i = 0; i < n; i++) {
            elementData[size + i] = list.get(i);
        }
        size += n;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) this.elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);

        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        final T removed = (T) elementData[index];

        if (index < size - 1) {
            System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        }
        size--;
        elementData[size] = null;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? elementData[i] == null : element.equals(elementData[i])) {
                if (i < size - 1) {
                    System.arraycopy(elementData, i + 1, elementData, i, size - i - 1);
                }
                size--;
                elementData[size] = null;
                return element;
            }
        }
        throw new NoSuchElementException("Element not found: " + element);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfFull() {
        int oldCapacity = this.elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);

        Object[] newArray = new Object[newCapacity];

        System.arraycopy(this.elementData, 0, newArray, 0, oldCapacity);

        this.elementData = newArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException();
        }
    }
}
