package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_CAPACITY = 10;
    public static final double DEFAULT_CAPACITY_K = 1.5;
    public static final Object[] EMPTY_INITIAL_ARRAY = {};
    private Object[] elementData;
    private int size;
    private int capacity;

    public ArrayList() {
        this.elementData = EMPTY_INITIAL_ARRAY;
    }

    public ArrayList(int capacity) {
        if (capacity > 0) {
            this.elementData = new Object[capacity];
        } else if (capacity == 0) {
            this.elementData = EMPTY_INITIAL_ARRAY;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
    }

    @Override
    public void add(T value) {
        checkCapacity(DEFAULT_CAPACITY_K);
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound");
        }
        checkCapacity(DEFAULT_CAPACITY_K);
        if (index == size) {
            elementData[index] = value;
        } else {
            Object[] arrayBuffer = new Object[capacity];
            if (index != 0) {
                System.arraycopy(elementData, 0, arrayBuffer, 0, index);
            }
            System.arraycopy(elementData, index, arrayBuffer, index + 1, size - index);
            arrayBuffer[index] = value;
            elementData = arrayBuffer;
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int listSize = list.size();
        size += listSize;
        double k = size / capacity;
        checkCapacity(k);
        for (int i = 0; i < listSize; i++) {
            elementData[size - listSize + i] = list.get(i);
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
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        size--;
        if (size == 0) {
            capacity = 0;
            elementData = EMPTY_INITIAL_ARRAY;
        } else if (capacity / size >= DEFAULT_CAPACITY_K) {
            elementData = resize();
        }
        return removed;
    }

    @Override
    public T remove(T element) {
        if (size == 0) {
            throw new NoSuchElementException("Array list is empty");
        }
        boolean elementExist = false;
        T oldElement = null;
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element,elementData[i])) {
                elementExist = true;
                oldElement = (T) elementData[i];
                index = i;
            }
        }
        if (elementExist) {
            for (int i = index; i < size - 1; i++) {
                elementData[i] = elementData[i + 1];
            }
            size--;
            if (size == 0) {
                capacity = 0;
                elementData = EMPTY_INITIAL_ARRAY;
            } else if (capacity / size >= DEFAULT_CAPACITY_K) {
                elementData = resize();
            }
            return oldElement;
        } else {
            throw new NoSuchElementException("Element not found in ArrayList");
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] grow(double k) {
        capacity = (int) (capacity * k);
        Object[] newCapacityArray = new Object[capacity];
        System.arraycopy(elementData,0, newCapacityArray,0, size);
        return newCapacityArray;
    }

    private void checkCapacity(double k) {
        if (capacity == 0) {
            elementData = new Object[DEFAULT_CAPACITY];
            capacity = DEFAULT_CAPACITY;
        }
        if (size >= capacity) {
            elementData = grow(k);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound");
        }
    }

    private Object[] resize() {
        capacity = (int) (capacity / DEFAULT_CAPACITY_K);
        Object[] newCapacityArray = new Object[capacity];
        System.arraycopy(elementData,0, newCapacityArray,0, size);
        return newCapacityArray;
    }
}
