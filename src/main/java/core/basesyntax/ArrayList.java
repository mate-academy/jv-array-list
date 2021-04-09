package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int setInitialSize) {
        elementData = new Object[setInitialSize];
    }

    @Override
    public void add(T value) {
        if (!ensureCapacity()) {
            elementData = expand();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        indexCheck(index);
        if (ensureCapacity()) {
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
            size++;
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
        indexCheck(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T elementRemoved = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[--size] = null;
        return elementRemoved;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementData[i]
                    || element != null && element.equals(elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element: " + element + " was not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean ensureCapacity() {
        return this.size < elementData.length;
    }

    private <T> Object[] expand() {
        Object[] newArray = (T[]) new Object[elementData.length + (elementData.length >> 1)];
        System.arraycopy(elementData, 0, newArray, 0, size);
        return newArray;
    }

    private void indexCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("For Index: "
                    + index + ", And size: " + size);
        }
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (elementData == null ? 0 : elementData.hashCode());
        result = 31 * result + size;
        return result;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        ArrayList<T> currentObject = (ArrayList<T>) object;
        if (object.getClass().equals(ArrayList.class)) {
            return Arrays.equals(this.elementData, currentObject.elementData)
                    && this.size == currentObject.size;
        }
        return false;
    }
}
