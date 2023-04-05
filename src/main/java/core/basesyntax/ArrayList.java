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
        growIfArrayIsFull();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        growIfArrayIsFull();
        checkIndexCorrectness(index);
        System.arraycopy(elementData, index,
                elementData, index + 1,
                elementData.length - index - 1);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            growIfArrayIsFull();
            elementData[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        checkIndexCorrectnessWithoutLastEmptyElement(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        growIfArrayIsFull();
        checkIndexCorrectnessWithoutLastEmptyElement(index);
        elementData[index] = value;
        if (index == size) {
            size++;
        }
    }

    @Override
    public T remove(int index) {
        checkIndexCorrectnessWithoutLastEmptyElement(index);
        T removingElementData = (T) elementData[index];
        moveArray(index);
        size--;
        return removingElementData;
    }

    @Override
    public T remove(T element) {
        int index = 0;
        for (int i = 0; i < size; i++) {
            if ((elementData[i] == element)
                    || (elementData[i] != null && elementData[i].equals(element))) {
                index = i;
                break;
            }
            if ((elementData[i] != null
                    && !(elementData[i].equals(element)) && i == size - 1)) {
                throw new NoSuchElementException(
                        "Specified element: "
                                + element + ", was not found. Please enter correct element");
            }
        }
        T removingElementData = (T) elementData[index];
        moveArray(index);
        size--;
        return removingElementData;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfArrayIsFull() {
        if (size == elementData.length) {
            Object[] resizedArray = new Object[elementData.length + (elementData.length >> 1)];
            System.arraycopy(elementData, 0, resizedArray, 0, elementData.length);
            elementData = resizedArray;
        }
    }

    private void checkIndexCorrectness(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index: " + index + " is out of bounds. Please enter correct index");
        }
    }

    private void checkIndexCorrectnessWithoutLastEmptyElement(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index: " + index + " is out of bounds. Please enter correct index");
        }
    }

    private void moveArray(int index) {
        System.arraycopy(elementData, index + 1,
                elementData, index,
                elementData.length - index - 1);
    }
}
