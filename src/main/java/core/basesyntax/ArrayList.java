package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double GROWTH_MULTIPLIER = 1.5;
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size = 0;

    public ArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void removeElementAtIndex(Object[] elements, int index) {
        if ((size - 1) >= index) {
            System.arraycopy(elements, index + 1, elements, index, (size - 1) - index);
        }
        elements[size -= 1] = null;
    }

    private void validIndexIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    private void growIfArrayFull(int minCapacity) {
        if (minCapacity >= elementData.length) {
            int newArraySize = (int) Math.round(elementData.length * GROWTH_MULTIPLIER);
            T[] newArray = (T[]) new Object[Math.max(newArraySize,minCapacity)];
            System.arraycopy(elementData, 0, newArray, 0, elementData.length);
            elementData = newArray;
        }
    }

    private void sizeCheck() {
        if (size == elementData.length) {
            growIfArrayFull(size);
        }
    }

    @Override
    public void add(T value) {
        sizeCheck();
        elementData[size] = value;
        this.size = size + 1;
    }

    @Override
    public void add(T value, int index) {
        sizeCheck();
        if (index == size) {
            add(value);
        } else {
            validIndexIndex(index);
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
            this.size = size + 1;
        }
    }

    @Override
    public void addAll(List<T> list) {
        T[] inputListToArray = (T[]) new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            inputListToArray[i] = list.get(i);
        }
        if (inputListToArray.length > elementData.length - size) {
            growIfArrayFull(inputListToArray.length + size);
        }
        System.arraycopy(inputListToArray, 0, elementData, size, inputListToArray.length);
        size = size + inputListToArray.length;
    }

    @Override
    public T get(int index) {
        validIndexIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        validIndexIndex(index);
        elementData[index] = value;

    }

    @Override
    public T remove(int index) {
        validIndexIndex(index);
        T oldValue = elementData[index];
        removeElementAtIndex(elementData, index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int index = 0;
        found: {
            if (element == null) {
                for (; index < size; index++) {
                    if (elementData[index] == null) {
                        break found;
                    }
                }
            } else {
                for (; index < size; index++) {
                    if (element.equals(elementData[index])) {
                        break found;
                    }
                }
            }
            throw new NoSuchElementException(element + " not found");
        }
        removeElementAtIndex(elementData, index);
        return element;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
}
