package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_SIZE = 10;
    private static final int DEFAULT_SIZE = 0;
    private static final int EXTRA_NUMBER = 1;
    private static final double ENLARGE_INDEX = 1.5;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_ARRAY_SIZE];
        size = 0;
    }

    @Override
    public void add(T value) {
        enlarge();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        enlarge();
        System.arraycopy(elementData, index, elementData, index + EXTRA_NUMBER,
                elementData.length - index - EXTRA_NUMBER);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        enlarge();
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index, size);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, size);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, size);
        T removed = (T) elementData[index];
        System.arraycopy(elementData, index + EXTRA_NUMBER, elementData, index,
                elementData.length - index - EXTRA_NUMBER);
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element || element != null && element.equals(elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no that element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == DEFAULT_SIZE;
    }

    private int checkIndex(int index, int size) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("That index does not exist");
        }
        return index;
    }

    private void enlarge() {
        if (size == elementData.length) {
            int newLength = (int) (elementData.length * ENLARGE_INDEX);
            Object[] newArray = new Object[newLength];
            System.arraycopy(elementData, 0, newArray, 0, elementData.length);
            elementData = newArray;
        }
    }
}
