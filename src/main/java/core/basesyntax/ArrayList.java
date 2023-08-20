package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
    private final int indexMethod = stackTraceElements.length - 2;
    private StackTraceElement element = stackTraceElements[indexMethod];
    private int capacity = 10;
    private Object[] elementData = new Object[capacity];
    private int size = 0;

    @Override
    public void add(T value) {
        if (size >= capacity) {
            elementData = grow();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (size < index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Can't add element by this index, method: " + element);
        }
        if (++size >= capacity) {
            elementData = grow();
        }
        if (index == 0) {
            System.arraycopy(elementData, 0, elementData, 1, size);
        } else {
            System.arraycopy(elementData, 0, elementData, 0, index);
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
        }
        elementData[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        int currentCapacity = elementData.length;
        while (currentCapacity + list.size() > capacity) {
            elementData = grow();
        }
        for (int i = size, j = 0; j < list.size(); i++, j++) {
            elementData[i] = list.get(j);
            this.size++;
        }
    }

    @Override
    public T get(int index) {
        returnException(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        returnException(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        returnException(index);
        T removedElement = (T)elementData[index];
        if (index == 0) {
            System.arraycopy(elementData, 1, elementData, 0, --size);
        } else {
            System.arraycopy(elementData, 0, elementData, 0, index - 1);
            System.arraycopy(elementData, index + 1, elementData, index, --size - index);
        }
        elementData[size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = indexCheck(element);
        if (index == -1) {
            throw new NoSuchElementException(
                    "There is no such element in ArrayList, method: " + element);
        }
        remove(index);
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

    private Object[] grow() {
        capacity *= 1.5;
        Object[] array = new Object[capacity];
        System.arraycopy(elementData, 0, array, 0, size);
        return array;
    }

    private int indexCheck(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (element == null ? element == elementData[i]
                    : element.equals(elementData[i])) {
                index = i;
                break;
            }
        }
        return index;
    }

    private void returnException(int index) {
        if (size <= index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Incorrect index in the method: " + element);
        }
    }
}
