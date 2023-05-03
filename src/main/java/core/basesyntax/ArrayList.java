package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_LIST_LENGTH = 10;
    private int size;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_LIST_LENGTH];
    }

    @Override
    public void add(T value) {
        growIfNeeded(1);
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        growIfNeeded(1);
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
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
        T[] elementDataCopy = elementData;
        T deletedElement = elementData[index];
        System.arraycopy(elementData, index + 1, elementDataCopy, index,
                size - 1 - index);
        size--;
        return deletedElement;
    }

    @Override
    public T remove(T element) {
        return remove(getElementIndex(element));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfNeeded(int quantity) {
        if (size + quantity > elementData.length) {
            int newCapacity = (elementData.length + (elementData.length >> 1));
            T[] growedElementData = (T[]) new Object[newCapacity];
            System.arraycopy(elementData,0, growedElementData, 0, size);
            elementData = growedElementData;
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("invalid index: "
                    + index + " is not exist");
        }
    }

    private void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(" Index " + index + " out of bounds List");
        }
    }

    private int getElementIndex(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i] == element || (element != null)
                    && element.equals(elementData[i])) {
                return i;
            }
        }
        throw new NoSuchElementException("invalid element: " + element + " is not exist");
    }
}
