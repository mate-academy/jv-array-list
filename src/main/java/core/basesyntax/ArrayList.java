package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIALIZE_ELEMENT_DATA_NUMBER = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[INITIALIZE_ELEMENT_DATA_NUMBER];
        size = 0;
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        validateIndexToAdd(index);
        growIfArrayFull();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
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
        validateIndexToRemove(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndexToRemove(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndexToRemove(index);
        T elementToRemove = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return elementToRemove;
    }

    @Override
    public T remove(T element) {
        int index = findIndexByValue(element);
        validateIndexToRemove(index);
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
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

    private int findIndexByValue(T value) {
        int i;
        for (i = 0; i < size; i++) {
            if ((value == null && elementData[i] == null)
                    || (value != null && value.equals(elementData[i]))) {
                return i;
            }
        }
        throw new NoSuchElementException("There is no element:"
                + value.toString() + " in this list");
    }

    private void validateIndexToAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("index: "
                    + index + " is out of bound: " + size);
        }
    }

    private void validateIndexToRemove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index: "
                    + index + " is out of bound: " + size);
        }
    }

    private void growIfArrayFull() {
        if (size == elementData.length) {
            T[] newElementData = (T[]) new Object[Double.valueOf(size * 1.5).intValue()];
            System.arraycopy(elementData, 0, newElementData, 0, size);
            elementData = (T[]) newElementData;
        }
    }
}
