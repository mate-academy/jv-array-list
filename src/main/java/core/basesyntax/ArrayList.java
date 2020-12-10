package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int LENGTH = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[LENGTH];
        size = 0;
    }

    private void indexCheck(int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index is out of bounds. Should be in "
                    + "between 0 and " + size + ", but was: " + index);
        }
    }

    private void indexEqualsToSizeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Can't access invalid index, list size is: "
                    + size + ", your index is: " + index);
        }
    }

    private void capacityCheck() {
        if (size == elementData.length) {
            grow();
        }
    }

    private void grow() {
        Object[] newElementData = (new Object[(int) (elementData.length
                + (elementData.length >> 1))]);
        System.arraycopy(elementData, 0, newElementData, 0, size);
        elementData = newElementData;
    }

    @Override
    public void add(T value) {
        capacityCheck();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        capacityCheck();
        indexCheck(index);
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
        indexEqualsToSizeCheck(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexEqualsToSizeCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexEqualsToSizeCheck(index);
        T removedValue = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (t == null && elementData[i] == null || t != null && t.equals(elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element can't be found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }
}
