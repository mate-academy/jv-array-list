package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_SIZE = 10;
    private static final String OUT_OF_BOUNDS_MESSAGE = "Index out of bounds";
    private static final String NO_SUCH_ELEMENT_MESSAGE = "No such element";
    private int size;

    private T[] elementData;

    public ArrayList() {
        this.elementData = (T[]) new Object[DEFAULT_ARRAY_SIZE];
    }

    @Override
    public void add(T value) {
        elementDataGrow(size);
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(OUT_OF_BOUNDS_MESSAGE);
        } else {
            System.arraycopy(elementData, index,
                    elementData, index + 1,
                    size - index);
            elementData[index] = value;
            size++;
            elementDataGrow(size);
        }
    }

    @Override
    public void addAll(List<T> list) {
        Object[] array = new Object[list.size()];
        for (int i = 0; i <= list.size(); i++) {
            array[i] = list.remove(0);
        }
        if (list.size() + size >= elementData.length) {
            elementData = grow();
        }
        System.arraycopy(array, 0, elementData, size, array.length);
        size += array.length;
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
        T removedElement;
        if ((size == elementData.length) && index + 1 == size) {
            size--;
            removedElement = elementData[index];
            return removedElement;
        }
        removedElement = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == null && element == null) {
                size--;
                return null;
            }
            if (elementData[i] != null && elementData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(NO_SUCH_ELEMENT_MESSAGE);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] grow() {
        Object[] newDataArray = new Object[size + size / 2];
        System.arraycopy(elementData, 0, newDataArray, 0, elementData.length);
        return (T[]) newDataArray;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(OUT_OF_BOUNDS_MESSAGE);
        }
    }

    private void elementDataGrow(int size) {
        if (size == elementData.length) {
            elementData = grow();
        }
    }
}

