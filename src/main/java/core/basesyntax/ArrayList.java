package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    static final int DEFAULT_CAPACITY = 10;
    static final float INCREASE_CAPACITY_COEFFICIENT = 1.5f;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        indexCheck(index);
        checkCapacity(1);
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
        indexCheck(index);
        indexOutofUpperBoundCheck(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        indexOutofUpperBoundCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        indexOutofUpperBoundCheck(index);
        T element = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        int index = getIndexByValue(element);
        return remove(index);
    }

    private int getIndexByValue(T value) {
        for (int i = 0; i < size; i++) {
            if (value == elementData[i]
                    || (value != null && value.equals(elementData[i]))) {
                return i;
            }
        }
        throw new NoSuchElementException("No such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void indexCheck(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
    }

    private void indexOutofUpperBoundCheck(int index) {
        if (index == size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private void checkCapacity(int numbOfNewElements) {
        if (size + numbOfNewElements > elementData.length) {
            growCapacity();
        }
    }

    private void growCapacity() {
        int newCapacity = (int) (elementData.length * INCREASE_CAPACITY_COEFFICIENT);
        Object[] newElementData = new Object[newCapacity];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        elementData = newElementData;
    }
}
