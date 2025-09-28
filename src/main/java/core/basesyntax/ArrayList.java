package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_COEFICIENT = 1.5;

    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void shiftRight(int index) {
        for (int i = size - 1; i >= index; --i) {
            elementData[i + 1] = elementData[i];
        }
    }

    private void shiftLeft(int index) {
        for (int i = index; i < size - 1; ++i) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size - 1] = null;
    }

    private void growIfArrayFull(int requiredCapacity) {
        if (requiredCapacity > elementData.length) {
            int newCapacity = (int) (elementData.length * GROW_COEFICIENT);
            if (newCapacity < requiredCapacity) {
                newCapacity = requiredCapacity;
            }
            T[] newElementData = (T[]) new Object[newCapacity];
            for (int i = 0; i < size; ++i) {
                newElementData[i] = elementData[i];
            }
            elementData = newElementData;
        }
    }

    @Override
    public void add(T value) {
        growIfArrayFull(size + 1);
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        growIfArrayFull(size + 1);
        shiftRight(index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        growIfArrayFull(size + list.size());
        for (int i = 0; i < list.size(); ++i) {
            elementData[size] = list.get(i);
            size++;
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
        T element = elementData[index];
        shiftLeft(index);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; ++i) {
            if ((elementData[i] == null && element == null)
                    || (elementData[i] != null && elementData[i].equals(element))) {
                T removedElement = elementData[i];
                shiftLeft(i);
                size--;
                return removedElement;
            }
        }
        throw new NoSuchElementException("Element: " + element + " not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
