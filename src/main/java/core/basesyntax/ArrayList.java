package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elementData;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        addElement(value);
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
        } else {
            checkIndex(index);
            if (size == elementData.length) {
                elementData = grow();
            }
            System.arraycopy(elementData, index, elementData, index + 1, size);
            elementData[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        while (list.size() + size > elementData.length) {
            elementData = grow();
        }
        for (int i = 0; i < list.size(); i++) {
            elementData[size++] = list.get(i);
        }
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
        if (index == size - 1) {
            elementData[--size] = null;
        } else {
            System.arraycopy(elementData, index + 1, elementData, index, size - index);
            elementData[--size] = null;
        }
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element
                    || (elementData[i] != null && elementData[i].equals(element))) {
                remove(i);
                break;
            } else if (i == size - 1) {
                throw new NoSuchElementException("Element " + element + " wasn't found");
            }
        }
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for length " + size);
        }
    }

    private int findIndex(Object[] tempArr, int index) {
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return i;
            }
        }
        return -1;
    }

    private Object[] copyBackToArray(int index, Object[] tempArray, int size) {
        for (int j = 0, k = index; j < size; j++, k++) {
            elementData[k] = tempArray[j];
        }
        return elementData;
    }

    private Object[] copyToTempArray(int index, Object[] tempArray, int startIndex, int size) {
        for (int j = startIndex, k = index; j < size; j++, k++) {
            tempArray[j] = elementData[k];
        }
        return tempArray;
    }

    private void addElement(T value) {
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[size] = value;
        size++;
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    private Object[] grow(int minCapacity) {
        return elementData = Arrays.copyOf(elementData, newCapacity(minCapacity));
    }

    private int newCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        return oldCapacity + (oldCapacity / 2);
    }
}
