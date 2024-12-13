package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_CAPACITY = 10;
    public static final double ARRAY_GROWTH_VALUE = 1.5;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayIsFull();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        growIfArrayIsFull();
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
        elementData[index] = value;
        size++;
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Can't add value to index " + index
                    + ". Index is invalid.");
        }
    }

    private void growIfArrayIsFull() {
        if (size == elementData.length) {
            Object[] grownElementData = new Object[(int) (elementData.length
                                + (elementData.length * 1.5))];
            System.arraycopy(elementData, 0, grownElementData, 0, size);
            elementData = grownElementData;
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
        checkIndexValidation(index);
        return (T) elementData[index];
    }

    private void checkIndexValidation(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid.");
        }
    }

    @Override
    public void set(T value, int index) {
        checkIndexValidation(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexValidation(index);
        T oldValue = (T) elementData[index];
        removeByIndex(elementData, index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return remove(i);
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elementData[i])) {
                    return remove(i);
                }
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    private void removeByIndex(Object[] elementData, int index) {
        if ((size - 1) > index) {
            System.arraycopy(elementData, index + 1, elementData, index,
                    size - index - 1);
        } else {
            elementData[index] = null;
        }
        size--;
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
