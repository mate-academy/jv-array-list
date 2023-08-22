package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size() == elementData.length) {
            elementData = grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        addIndexValidation(index);
        if (size == elementData.length) {
            elementData = grow();
        }
        System.arraycopy(elementData, index, elementData,
                  index + 1, size - index);
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
        indexValidation(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexValidation(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexValidation(index);
        T element = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData,
                index, size - index - 1);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element || elementData[i] != null
                    && elementData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There aren't your element at list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] grow() {
        Object[] newArray = new Object[(int) (elementData.length * 1.5)];
        System.arraycopy(elementData, 0, newArray, 0, size);
        return newArray;
    }

    private void indexValidation(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("There is no element at index " + index);
        }
    }

    private void addIndexValidation(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("There is no element at index " + index);
        }
    }
}
