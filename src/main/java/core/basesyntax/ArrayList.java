package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double RESIZE = 1.5;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resizeIfNeeded();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        resizeIfNeeded();
        if (index != size) {
            checkSizeWithIndex(index);
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
        checkSizeWithIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkSizeWithIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkSizeWithIndex(index);
        final T removeElem = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index,
                elementData.length - index - 1);
        elementData[--size] = null;
        return removeElem;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (isEquals(element, elementData[i])) {
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

    private void resizeIfNeeded() {
        if (size == elementData.length) {
            grow();
        }
    }

    private boolean isEquals(T firstElement, T secondElement) {
        return firstElement == null
                ? firstElement == secondElement : firstElement.equals(secondElement);
    }

    private void grow() {
        int newCapacity = (int) (elementData.length * RESIZE);
        T[] newElementData = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        elementData = newElementData;
    }

    private void checkSizeWithIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index: " + index);
        }
    }
}
