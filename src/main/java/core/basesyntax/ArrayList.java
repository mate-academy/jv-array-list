package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final Object[] EMPTY_ELEMENT_DATA = {};
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size = 0;

    public ArrayList() {
        this.elementData = EMPTY_ELEMENT_DATA;
    }

    private void removeElementAtIndex(Object[] elements, int index) {
        if ((size - 1) >= index) {
            System.arraycopy(elements, index + 1, elements, index, (size - 1) - index);
        }
        elements[size -= 1] = null;
    }

    private void checkIndex(int index) {
        if ((index > size - 1 || index < 0) || index == size) {
            throw new ArrayListIndexOutOfBoundsException("index is out of bound of array");
        }
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0) {
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            Object[] copyOfElementData = elementData;
            elementData = new Object[Math.max(newCapacity,minCapacity)];
            System.arraycopy(copyOfElementData,0, elementData, 0, oldCapacity);
            return elementData;
        }
        return elementData = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
    }

    private void sizeCheck() {
        if (size == elementData.length) {
            elementData = grow(size + 1);
        }
    }

    @Override
    public void add(T value) {
        sizeCheck();
        elementData[size] = value;
        this.size = size + 1;
    }

    @Override
    public void add(T value, int index) {
        sizeCheck();
        if (index == size) {
            add(value);
        } else {
            checkIndex(index);
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
            this.size = size + 1;
        }
    }

    @Override
    public void addAll(List<T> list) {
        Object[] inputListToArray = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            inputListToArray[i] = list.get(i);
        }
        if (inputListToArray.length > elementData.length - size) {
            elementData = grow(size + inputListToArray.length);
        }
        System.arraycopy(inputListToArray, 0, elementData, size, inputListToArray.length);
        size = size + inputListToArray.length;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;

    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldValue = (T) elementData[index];
        removeElementAtIndex(elementData, index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int index = 0;
        found: {
            if (element == null) {
                for (; index < size; index++) {
                    if (elementData[index] == null) {
                        break found;
                    }
                }
            } else {
                for (; index < size; index++) {
                    if (element.equals(elementData[index])) {
                        break found;
                    }
                }
            }
            throw new NoSuchElementException(element + " not found");
        }
        removeElementAtIndex(elementData, index);
        return element;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
}
