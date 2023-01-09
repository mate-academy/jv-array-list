package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkCapacity(size + 1);
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
        } else {
            checkRange(index);
            checkCapacity(size + 1);
            T[] newElementData = generateNewArray(elementData.length);
            System.arraycopy(elementData, 0, newElementData, 0, (size - 1) - index);
            newElementData[index] = value;
            System.arraycopy(elementData, index, newElementData, index + 1, size - index);
            elementData = newElementData;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        checkCapacity(size + list.size());
        T[] arrayFromList = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arrayFromList[i] = list.get(i);
        }
        System.arraycopy(arrayFromList, 0, elementData, size, arrayFromList.length);
        size = size + list.size();
    }

    private void grow() {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        T[] newElementData = generateNewArray(newCapacity);
        System.arraycopy(elementData, 0, newElementData, 0, size);
        elementData = newElementData;
    }

    private T[] generateNewArray(int newCapacity) {
        return (T[]) new Object[newCapacity];
    }

    private void checkCapacity(int capacity) {
        if (capacity - elementData.length > 0) {
            grow();
        }
    }

    private void checkRange(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't add element to index " + index);
        }
    }

    @Override
    public T get(int index) {
        checkRange(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkRange(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkRange(index);
        T removeValue = elementData[index];
        T[] newElementData = generateNewArray(elementData.length);
        if (index == size - 1) {
            System.arraycopy(elementData, 0, newElementData, 0, size - 1);
        } else {
            copyArray(index, newElementData);
        }
        size--;
        return removeValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == get(i) || (element != null && element.equals(elementData[i]))) {
                T removeValue = elementData[i];
                T[] newElementData = generateNewArray(elementData.length);
                copyArray(i, newElementData);
                size--;
                return removeValue;
            }
        }
        throw new NoSuchElementException("No such element " + element);
    }

    private void copyArray(int index, T[] newElementData) {
        System.arraycopy(elementData, 0, newElementData, 0, (size - 1) - index);
        System.arraycopy(elementData, index + 1, newElementData, index, size - index);
        elementData = newElementData;
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
