package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        increaseCapacityIfArrayFull();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is not available: " + index);
        }
        increaseCapacityIfArrayFull();
        System.arraycopy(elementData, index, elementData, (index + 1), (size - index));
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
        T oldValue = elementData[index];
        deleteElementInArray(index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        T equalsElement;
        for (int i = 0; i < elementData.length; i++) {
            T currentElement = elementData[i];
            if (equalsElement(currentElement, element)) {
                equalsElement = element;
                deleteElementInArray(i);
                return equalsElement;
            }
        }
        throw new NoSuchElementException("Can't find element" + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void grow() {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, newArray, 0, elementData.length);
        elementData = newArray;
    }

    private void deleteElementInArray(int index) {
        int numMoved = size - index - 1;
        System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        size--;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Illegal index: " + index);
        }
    }

    private boolean equalsElement(T elementOne, T elementTwo) {
        if (elementOne == elementTwo) {
            return true;
        }
        if (elementOne == null || elementTwo == null) {
            return false;
        }
        return elementOne.equals(elementTwo);
    }

    private void increaseCapacityIfArrayFull() {
        if (size == elementData.length) {
            grow();
        }
    }
}
