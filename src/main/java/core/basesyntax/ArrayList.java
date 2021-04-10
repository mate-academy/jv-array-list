package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData = (T[]) new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            grow();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        try {
            if (size == elementData.length) {
                grow();
            }
            System.arraycopy(elementData, index,
                    elementData, index + 1, size - index);
            elementData[index] = value;
            size++;

        } catch (IndexOutOfBoundsException e) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds", e);
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: "
                    + index + ", Size " + size);
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        try {
            Objects.checkIndex(index, size);
            T oldValue = (T)elementData[index];
            elementData[index] = value;
        } catch (IndexOutOfBoundsException e) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + " Out of bound", e);
        }
    }

    @Override
    public T remove(int index) {
        try {
            Objects.checkIndex(index, size);
            final Object[] es = elementData;
            T oldValue = (T) es[index];
            fastRemove(es, index);
            return oldValue;
        } catch (IndexOutOfBoundsException e) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index
                    + "out of bound, Size:" + size, e);
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] != null && elementData[i].equals(element)
                    || elementData[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    private void grow() {
        if (size == elementData.length) {
            T[] newArray = (T[]) new Object[size + size / 2];
            System.arraycopy(elementData, 0, newArray, 0, size);
            elementData = newArray;
        }
    }

    public void fastRemove(Object[] es, int i) {
        final int newSize;
        if ((newSize = size - 1) > i) {
            System.arraycopy(es, i + 1, es, i, newSize - i);
        }
        es[size = newSize] = null;
    }

}
