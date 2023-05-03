package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        toResize();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        toResize();
        if (index != size) {
            if (index >= size || index < 0) {
                throw new ArrayListIndexOutOfBoundsException("Incorrect index: " + index);
            }
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
        }
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
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index: " + index);
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index: " + index);
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index: " + index);
        }
        final T removeElem = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index,
                elementData.length - index - 1);
        elementData[--size] = null;
        return removeElem;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (isEquals(element,elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found in ArrayList");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void toResize() {
        if (size == elementData.length || (size - 1) == elementData.length) {
            grow();
        }
    }

    private boolean isEquals(T elements, T elementsData) {
        return elements == null ? elements == elementsData : elements.equals(elementsData);
    }

    private void grow() {
        int newCapacity = (int) (elementData.length * 1.5);
        T[] newElementData = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        elementData = newElementData;
    }
}
