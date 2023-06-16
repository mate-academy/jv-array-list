package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        elementData = (size == elementData.length) ? grow() : elementData;
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is invalid");
        }
        elementData = (size == elementData.length) ? grow() : elementData;
        if (index <= size) {
            System.arraycopy(elementData, index, elementData, index + 1, size++ - index);
            elementData[index] = value;
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new ArrayListIndexOutOfBoundsException("Can't add list");
        }
        while (elementData.length < size + list.size()) {
            elementData = grow();
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T deletedObject = elementData[index];
        if (index + 1 == elementData.length) {
            size--;
            return deletedObject;
        }
        System.arraycopy(elementData, index + 1, elementData, index, size-- - index);
        return deletedObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elementData[i] == element)
                    || elementData[i] != null && elementData[i].equals(element)) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("Element " + element + " does not exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] grow() {
        T[] tempElementData = (T[]) new Object[elementData.length + (elementData.length >> 1)];
        System.arraycopy(elementData, 0, tempElementData, 0, size);
        elementData = tempElementData;
        return elementData;
    }

    private void validateIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is invalid");
        }
    }
}
