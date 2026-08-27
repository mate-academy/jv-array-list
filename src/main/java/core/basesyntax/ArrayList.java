package core.basesyntax;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int GROWTH_NUMERATOR = 3;
    private static final int GROWTH_DENOMINATOR = 2;

    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        growIfArrayFull();

        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int index = 0; index < list.size(); index++) {
            add(list.get(index));
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

        final T removedElement = elementData[index];
        int elementsToMove = size - index - 1;

        if (elementsToMove > 0) {
            System.arraycopy(elementData, index + 1,
                    elementData, index, elementsToMove);
        }

        elementData[size - 1] = null;
        size--;

        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int index = 0; index < size; index++) {
            if (elementData[index] == null
                    ? element == null
                    : elementData[index].equals(element)) {
                return remove(index);
            }
        }

        throw new NoSuchElementException(
                "Element " + element + " is not present in the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + size);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + size);
        }
    }

    private void growIfArrayFull() {
        if (size == elementData.length) {
            int newCapacity = elementData.length
                    * GROWTH_NUMERATOR / GROWTH_DENOMINATOR;

            T[] newElementData = (T[]) new Object[newCapacity];
            System.arraycopy(elementData, 0, newElementData, 0, size);
            elementData = newElementData;
        }
    }
}
