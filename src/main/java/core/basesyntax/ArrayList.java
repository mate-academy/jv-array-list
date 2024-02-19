package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final float CAPACITY_INCREASE_FACTOR = 1.5f;
    private int capacity;
    private int size;
    private Object[] elementData;

    public ArrayList() {
        capacity = DEFAULT_CAPACITY;
        elementData = new Object[capacity];
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be less than 0");
        }
        this.capacity = capacity;
        elementData = new Object[capacity];
    }

    @Override
    public void add(T value) {
        growIfFull();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        growIfFull();
        if (index == size) {
            add(value);
        } else {
            insertAndShift(index, value);
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
        rangeCheck(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        return removeAndShift(index);
    }

    @Override
    public T remove(T element) {
        int index = findElementIndex(element);
        if (index == -1) {
            throw new NoSuchElementException("Element not found");
        }
        return remove(index);
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
            throw new ArrayListIndexOutOfBoundsException(index, size);
        }
    }

    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(index, size);
        }
    }

    private boolean isFull() {
        return capacity == size;
    }

    private void grow() {
        if (capacity > 0) {
            capacity = (int)(capacity * CAPACITY_INCREASE_FACTOR);
        } else {
            capacity = DEFAULT_CAPACITY;
        }
        Object[] newElementData = new Object[capacity];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        elementData = newElementData;
    }

    private void growIfFull() {
        if (isFull()) {
            grow();
        }
    }

    private void insertAndShift(int index, T value) {
        for (int i = size; i >= 0; i--) {
            if (i == index) {
                elementData[index] = value;
                size++;
                break;
            }
            elementData[i] = elementData[i - 1];
        }
    }

    private T removeAndShift(int index) {
        final Object removedElement = elementData[index];
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size - 1] = null;
        size--;
        return (T) removedElement;
    }

    private int findElementIndex(T element) {
        for (int i = 0; i < size; i++) {
            if (isEqual(elementData[i], element)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isEqual(Object firstElement, Object secondElement) {
        return (firstElement == secondElement
                || firstElement != null && firstElement.equals(secondElement));
    }
}
