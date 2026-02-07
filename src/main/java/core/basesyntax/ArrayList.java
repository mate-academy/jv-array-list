package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private T[] elementData;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void grow() {
        if (size == elementData.length) {
            int oldCapacity = elementData.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            if (newCapacity <= oldCapacity) {
                newCapacity = oldCapacity + 1;
            }

            @SuppressWarnings("unchecked")
            T[] newArray = (T[]) new Object[newCapacity];
            System.arraycopy(elementData, 0, newArray, 0, size);
            elementData = newArray;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + size
            );
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + size
            );
        }
    }

    @Override
    public void add(T value) {
        grow();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        grow();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null || list.isEmpty()) {
            return;
        }

        int additionalSize = list.size();
        while (size + additionalSize > elementData.length) {
            grow();
        }

        for (int i = 0; i < list.size(); i++) {
            elementData[size++] = list.get(i);
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
        T removed = elementData[index];

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }

        elementData[--size] = null;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null
                    ? elementData[i] == null
                    : element.equals(elementData[i])) {

                T removed = elementData[i];
                int numMoved = size - i - 1;

                if (numMoved > 0) {
                    System.arraycopy(elementData, i + 1, elementData, i, numMoved);
                }

                elementData[--size] = null;
                return removed;
            }
        }

        throw new NoSuchElementException("Element " + element + " not found");
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
