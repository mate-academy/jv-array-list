package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INDEX_NOT_FOUND = -1;
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    private void grow() {
        elementData = Arrays.copyOf(elementData, elementData.length + (elementData.length >> 1));
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Array index %d out of bounds", index)
            );
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Index %d not suitable for add", index)
            );
        }
    }

    private int indexOf(Object o) {
        int index = 0;
        boolean found = false;
        if (o == null) {
            for (; index < size; index++) {
                if (elementData[index] == null) {
                    found = true;
                    break;
                }
            }
        } else {
            for (; index < size; index++) {
                if (o.equals(elementData[index])) {
                    found = true;
                    break;
                }
            }
        }
        return found ? index : INDEX_NOT_FOUND;
    }

    @Override
    public void add(T value) {
        if (size + 1 < elementData.length) {
            elementData[size++] = value;
            return;
        }
        grow();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        if (index == size) {
            add(value);
            return;
        }
        final int newSize = size + 1;
        final Object[] newArray = new Object[newSize];
        System.arraycopy(elementData, index, newArray, index + 1, size - index);
        System.arraycopy(elementData, 0, newArray, 0, index);
        elementData = Arrays.copyOf(newArray, size = newSize);
        elementData[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedValue = (T) elementData[index];
        final int newSize = size - 1;
        if (newSize > index) {
            System.arraycopy(elementData, index + 1, elementData, index, newSize - index);
        }
        elementData = Arrays.copyOf(elementData, size = newSize);
        return removedValue;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index > INDEX_NOT_FOUND) {
            return remove(index);
        }
        throw new NoSuchElementException();
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
