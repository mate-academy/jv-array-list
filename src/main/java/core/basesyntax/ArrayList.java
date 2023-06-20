package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_CAPACITY = 10;
    public static final double DEFAULT_CAPACITY_GROW_COEFFICIENT = 1.5;
    private Object[] elementData;
    private int size;
    private int capacity;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
        this.capacity = DEFAULT_CAPACITY;
    }

    public ArrayList(int capacity) {
        if (capacity >= 0) {
            this.elementData = new Object[capacity];
            this.capacity = capacity;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
    }

    @Override
    public void add(T value) {
        checkCapacity();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound");
        }
        checkCapacity();
        if (index != size) {
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
        }
        elementData[index] = value;
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
        return (T) elementData[index];
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
        size--;
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        elementData[size] = null;
        return removed;
    }

    @Override
    public T remove(T element) {
        if (size == 0) {
            throw new NoSuchElementException("Array list is empty");
        }
        int index = indexOf(element);
        if (index == -1) {
            throw new NoSuchElementException("Element not found in ArrayList");
        }
        return remove(index);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] grow() {
        capacity = (int) (capacity * DEFAULT_CAPACITY_GROW_COEFFICIENT);
        Object[] newCapacityArray = new Object[capacity];
        System.arraycopy(elementData,0, newCapacityArray,0, size);
        return newCapacityArray;
    }

    private void checkCapacity() {
        if (capacity == 0) {
            elementData = new Object[DEFAULT_CAPACITY];
            capacity = DEFAULT_CAPACITY;
        }
        if (size >= capacity) {
            elementData = grow();
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound");
        }
    }

    private int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element,elementData[i])) {
                return i;
            }
        }
        return -1;
    }
}
