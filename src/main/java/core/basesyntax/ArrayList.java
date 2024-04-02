package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_DEFAULT = 10;
    private T[] elementData;
    private int arraySize;

    public ArrayList() {
        this.elementData = (T[]) new Object[ARRAY_DEFAULT];
    }

    @Override
    public void add(T value) {
        if (arraySize == elementData.length) {
            increaseElementData();
        }
        elementData[arraySize] = value;
        arraySize++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > arraySize) {
            throw new ArrayListIndexOutOfBoundsException("Not correct index: " + index);
        }
        if (arraySize == elementData.length) {
            increaseElementData();
        }

        System.arraycopy(elementData, index, elementData, index + 1, arraySize - index);
        elementData[index] = value;
        arraySize++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size();i++) {
            add(list.get(i));
        }

    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= arraySize) {
            throw new ArrayListIndexOutOfBoundsException("Not correct index: " + index);
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= arraySize) {
            throw new ArrayListIndexOutOfBoundsException("Not correct index: " + index);
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= arraySize) {
            throw new ArrayListIndexOutOfBoundsException("Not correct index: " + index);
        }
        T removedElement = get(index);
        System.arraycopy(elementData, index + 1, elementData, index, arraySize - index - 1);
        arraySize--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < arraySize; i++) {
            if (element == null ? elementData[i] == null : element.equals(elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found in the list");
    }

    @Override
    public int size() {
        return arraySize;
    }

    @Override
    public boolean isEmpty() {
        return arraySize == 0;
    }

    private void increaseElementData() {
        int newElementCapacity = elementData.length + (elementData.length >> 1);
        elementData = Arrays.copyOf(elementData,newElementCapacity);
    }
}
