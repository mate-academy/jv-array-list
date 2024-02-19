package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final float CAPACITY_INCREASE_FACTOR = 1.5f;
    private static final int NO_INDEX = -1;
    private int capacity;
    private int size;
    private Object[] elementData;

    public ArrayList() {
        capacity = DEFAULT_CAPACITY;
        elementData = new Object[capacity];
    }

    public ArrayList(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Capacity cannot be less than 1");
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
        checkIndexForAdd(index);
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
        return removeAndShift(index);
    }

    @Override
    public T remove(T element) {
        int index = findElementIndex(element);
        if (index == NO_INDEX) {
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

    private void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + " Size: " + size);
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + " Size: " + size);
        }
    }

    private void grow() {
        capacity = calculateNewCapacity();
        Object[] newElementData = new Object[capacity];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        elementData = newElementData;
    }

    private int calculateNewCapacity() {
        //this is an important method for the case of initial capacity = 1,
        //rounding is similar to Math.ceil()
        double newCapacity = capacity * CAPACITY_INCREASE_FACTOR;
        return newCapacity % 1 == 0 ? (int)newCapacity : (int)newCapacity + 1;
    }

    private void growIfFull() {
        if (capacity == size) {
            grow();
        }
    }

    private void insertAndShift(int index, T value) {
        for (int i = size; i >= 0; i--) {
            int sourceIndex = i - 1;
            int destinationIndex = i;
            if (destinationIndex == index) {
                elementData[index] = value;
                size++;
                break;
            }
            elementData[destinationIndex] = elementData[sourceIndex];
        }
    }

    private T removeAndShift(int index) {
        final Object removedElement = elementData[index];
        for (int i = index; i < size - 1; i++) {
            int sourceIndex = i + 1;
            int destinationIndex = i;
            elementData[destinationIndex] = elementData[sourceIndex];
        }
        removeLastElement();
        return (T) removedElement;
    }

    private void removeLastElement() {
        int lastElementIndex = size - 1;
        elementData[lastElementIndex] = null;
        size--;
    }

    private int findElementIndex(T element) {
        for (int i = 0; i < size; i++) {
            if (isEqual(elementData[i], element)) {
                return i;
            }
        }
        return NO_INDEX;
    }

    private boolean isEqual(Object firstElement, Object secondElement) {
        return (firstElement == secondElement
                || firstElement != null && firstElement.equals(secondElement));
    }
}
