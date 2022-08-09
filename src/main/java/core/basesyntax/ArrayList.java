package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double MAGNIFICATION_FACTOR = 1.5;
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        extendArray();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(String
                    .format("Failed to get element for index %d", index));
        }
        extendArray();
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
        T result = elementData[index];
        if (result == null) {
            return null;
        }
        elementData[index] = null;
        if (elementData.length - index >= 0) {
            System.arraycopy(elementData, index + 1, elementData,
                    index, elementData.length - 1 - index);
        }
        size--;
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            T result = elementData[i];
            if (element == null) {
                if (elementData[i] == null) {
                    reindexArray(i + 1, i);
                    size--;
                    return result;
                }
            } else if (element.equals(elementData[i])) {
                elementData[i] = null;
                reindexArray(i + 1, i);
                size--;
                return result;
            }
        }
        throw new NoSuchElementException("Can't remove the element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void reindexArray(int srcPos, int destPos) {
        Object[] newElementData = new Object[(int) (elementData.length * MAGNIFICATION_FACTOR)];
        System.arraycopy(elementData,srcPos, elementData, destPos, size);
    }

    private void extendArray() {
        if (size == elementData.length) {
            Object[] newElementData = new Object[(int) (elementData.length * MAGNIFICATION_FACTOR)];
            System.arraycopy(elementData, 0, newElementData, 0, size);
            elementData = (T[]) newElementData;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(String
                    .format("Failed to get element for index %d", index));
        }
    }
}
