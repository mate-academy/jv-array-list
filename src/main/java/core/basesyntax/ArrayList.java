package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private int size;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAddByIndex(index);
        growIfArrayFull();
        arrayCopyForAddByIndex(index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (elementData.length > list.size()) {
            for (int index = 0; index < list.size(); index++) {
                add(list.get(index));
            }
        } else {
            grow();
            addAll(list);
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
        T oldValue = elementData[index];
        removeByIndex(elementData, index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int index = 0; index < elementData.length; index++) {
            if (equalsElemets(elementData[index], element)) {
                removeByIndex(elementData, index);
                return element;
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
        return size == 0;
    }

    private void growIfArrayFull() {
        if (size >= DEFAULT_SIZE) {
            grow();
        }
    }

    private void grow() {
        int oldCapacity = elementData.length;
        T[] newElementData = (T[]) new Object[oldCapacity + (DEFAULT_SIZE >> 1) + 1];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        elementData = newElementData;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index >= size or index < 0");
        }
    }

    private void checkIndexForAddByIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index > size or index < 0");
        }
    }

    private void arrayCopyForAddByIndex(int index) {
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
    }

    private void removeByIndex(T[] elementData, int index) {
        System.arraycopy(elementData, index + 1, elementData, index, size - 1 - index);
        size--;
    }

    private boolean equalsElemets(T a, T b) {
        return a == b || a != null && a.equals(b);
    }
}
