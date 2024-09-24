package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double DEFAULT_MULTIPLICATION_NUMBER = 1.5;
    private static final int START_ARRAY_SIZE = 0;
    private T[] elementsData;
    private int capacity;
    private int size;

    public ArrayList() {
        this.elementsData = (T[]) new Object[DEFAULT_CAPACITY];
        this.capacity = DEFAULT_CAPACITY;
        this.size = START_ARRAY_SIZE;
    }

    @Override
    public void add(T value) {
        if (size() == capacity) {
            resizeArray();
        }
        elementsData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size() == capacity) {
            resizeArray();
        }
        if (index >= 0 && index <= size) {
            T[] newElementsData = (T[]) new Object[capacity];
            for (int i = 0; i < index; i++) {
                newElementsData[i] = elementsData[i];
            }
            newElementsData[index] = value;
            for (int i = index; i < size(); i++) {
                newElementsData[i + 1] = elementsData[i];
            }
            size++;
            elementsData = newElementsData;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " doesn't found");
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
        if (index < size && index >= 0) {
            return elementsData[index];
        }
        throw new ArrayListIndexOutOfBoundsException("Index out of bounds for index: " + index);
    }

    @Override
    public void set(T value, int index) {
        if (index < size && index >= 0) {
            elementsData[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " doesn't found");
        }
    }

    @Override
    public T remove(int index) {
        T remove;
        if (index < size && index >= 0) {
            remove = elementsData[index];
            System.arraycopy(elementsData, index + 1, elementsData, index, size - index);
            size--;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " doesn't found");
        }
        return remove;
    }

    @Override
    public T remove(T element) {
        int ourIndex = getIndexOfElement(element);
        if (ourIndex != -1) {
            if (element == null && elementsData[ourIndex] == null
                    || element != null && elementsData[ourIndex].equals(element)) {
                return remove(ourIndex);
            }
        } else {
            throw new NoSuchElementException("No such element: " + element);
        }
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeArray() {
        double newCapacity = capacity * DEFAULT_MULTIPLICATION_NUMBER;
        this.capacity = (int) newCapacity;
        T[] newElementsData = (T[]) new Object[capacity];
        System.arraycopy(elementsData, 0, newElementsData, 0, size);
        elementsData = newElementsData;
    }

    private int getIndexOfElement(T element) {
        for (int i = 0; i < size(); i++) {
            if (elementsData[i] == element || elementsData[i] != null
                    && elementsData[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }
}
