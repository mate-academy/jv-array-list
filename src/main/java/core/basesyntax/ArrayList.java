package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INDEX_OF_METHOD = 2;
    private StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
    private StackTraceElement element
            = stackTraceElements[stackTraceElements.length - INDEX_OF_METHOD];
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
        if (size >= capacity) {
            elementData = grow();
        }
        for (int i = size; i != index; i--) {
            swap(i - 1, i);
        }
        elementData[index] = value;
        size++;
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
        for (int i = index; i < size - 1; i++) {
            swap(i + 1, i);
        }
        elementData[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = findIndex(element);
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
        Object[] newArray = Arrays.copyOf(elementData, capacity);
        return newArray;
    }

    private void swap(int firstIndex, int secondIndex) {
        Object temp = elementData[firstIndex];
        elementData[firstIndex] = elementData[secondIndex];
        elementData[secondIndex] = temp;
    }

    private int findIndex(T element) {
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
