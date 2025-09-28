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

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void shiftRight(int index) {
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
    }

    private void shiftLeft(int index) {
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[size - 1] = null;
    }

    private void growIfArrayFull(int requiredCapacity) {
        if (requiredCapacity > elementData.length) {
            int newCapacity = (int) (elementData.length * GROW_COEFICIENT);
            if (newCapacity < requiredCapacity) {
                newCapacity = requiredCapacity;
            }
            resizeCopyArray(newCapacity);
        }
    }

    private void resizeCopyArray(int newCapacity) {
        T[] newElementData = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, newElementData, 0, size);
        elementData = newElementData;
    }

    @Override
    public void add(T value) {
        growIfArrayFull(size + 1);
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
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
    public void clear() {
        for (int i = 0; i < size; ++i) {
            elementData[i] = null;
        }
        size = 0;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    @Override
    public T set(T value, int index) {
        checkIndex(index);
        T oldValue = elementData[index];
        elementData[index] = value;
        return oldValue;
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
    public boolean contains(T element) {
        for (int i = 0; i < size; ++i) {
            if ((elementData[i] == null && element == null)
                    || (elementData[i] != null && elementData[i].equals(element))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
