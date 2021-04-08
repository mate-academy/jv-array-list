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
            Object[] newArray = expand();
            System.arraycopy(elementData, 0, newArray, 0, size);
            elementData = newArray;
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index != size) {
            indexCheck(index);
        }
        if (ensureCapacity() && index <= size) {
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
            size++;
            return;
        }
        elementData[size++] = value;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] copyOfList = new Object[list.size()];
        for (int i = 0; i < copyOfList.length; i++) {
            copyOfList[i] = list.get(i);
        }
        if (ensureCapacity()) {
            System.arraycopy(copyOfList, 0, elementData, size, copyOfList.length);
        }
        Object[] newArray = expand();
        System.arraycopy(elementData, 0, newArray, 0, size);
        System.arraycopy(copyOfList, 0, newArray, size, copyOfList.length);
        elementData = newArray;
        size += copyOfList.length;
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
        int moveNumberOfElements = size - index - 1;
        System.arraycopy(elementData, index + 1, elementData, index, moveNumberOfElements);
        elementData[--size] = null;
        return elementRemoved;
    }

    @Override
    public T remove(T element) {
        T elementRemoved = null;
        for (int i = 0; i < size; i++) {
            if (element == null && elementData[i] == null
                    || element != null && element.equals(elementData[i])) {
                elementRemoved = remove(i);
                if (elementRemoved == null) {
                    return null;
                }
            }
        }
        if (elementRemoved == null) {
            throw new NoSuchElementException("Element: " + element + " was not found");
        }
        return elementRemoved;
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
        return (T[]) new Object[elementData.length + (elementData.length >> 1)];
    }

    private void indexCheck(int index) {
        if (index >= size || index < 0) {
            throwException(index);
        }
    }

    private void throwException(int index) {
        throw new ArrayListIndexOutOfBoundsException("For Index: " + index + ", And size: " + size);
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
