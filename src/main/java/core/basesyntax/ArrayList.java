package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size;

    public ArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            Object[] newElementData = new Object[elementData.length + elementData.length / 2];
            System.arraycopy(elementData, 0, newElementData, 0, size);
            elementData = newElementData;
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        if (size == elementData.length) {
            Object[] newElementData = new Object[elementData.length + elementData.length / 2];
            System.arraycopy(elementData, 0, newElementData, 0, size);
            elementData = newElementData;
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() == 0) {
            return;
        }
        int newCapacity = Math.max(elementData.length + elementData.length / 2, size + list.size());
        Object[] array = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        if ((size + list.size()) > elementData.length) {
            Object[] newElementData = new Object[newCapacity];
            System.arraycopy(elementData, 0, newElementData, 0, size);
            elementData = newElementData;
        }
        System.arraycopy(array, 0, elementData, size, list.size());
        size = size + list.size();
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        T result;
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        result = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        elementData[size] = null;
        return result;
    }

    @Override
    public T remove(T element) {
        T result = null;
        boolean temp = true;
        for (int i = 0; i < size; i++) {
            if (element == null && null == elementData[i]
                    || element != null && element.equals(elementData[i])) {
                result = (T) elementData[i];
                System.arraycopy(elementData, i + 1, elementData, i, size - i - 1);
                size--;
                elementData[size] = null;
                temp = false;
                break;
            }
        }
        if (temp) {
            throw new NoSuchElementException("Invalid element: " + element);
        }
        return result;
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
