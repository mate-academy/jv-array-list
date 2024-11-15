package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double GROW_MULTIPLIER = 1.5;
    private int size;
    private Object[] elementData;

    public ArrayList() {
        elementData = new Object[DEFAULT_SIZE];

    }

    public void checkCapacity() {
        if (size == elementData.length) {
            int newSize = (int)(elementData.length * GROW_MULTIPLIER);
            Object[] newArray = new Object[newSize];
            System.arraycopy(elementData, 0, newArray, 0, size);
            elementData = newArray;
        }
    }

    @Override
    public void add(T value) {
        checkCapacity();
        elementData[size++] = value;

    }

    @Override
    public void add(T value, int index) {
        checkIndexForSet(index);
        checkCapacity();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;

    }

    public void checkIndexForSet(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
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
        checkIndex(index);
        return (T) elementData[index];

    }

    public void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;

    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedValue = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[--size] = null;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && elementData[i] == null)
                    || (element != null && element.equals(elementData[i]))) {
                return remove(i);
            }

        }
        throw new NoSuchElementException("This element doesn't exist in Array :" + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
