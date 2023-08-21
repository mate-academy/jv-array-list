package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
    private final int indexMethod = stackTraceElements.length - 2;
    private StackTraceElement element = stackTraceElements[indexMethod];
    private Object[] elementData;
    private int size;

    ArrayList() {
        size = 0;
        elementData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size >= elementData.length) {
            grow();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (size < index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Can't add element by this index, method: " + element);
        }
        if (++size >= elementData.length) {
            grow();
        }
        if (index == 0) {
            System.arraycopy(elementData, 0, elementData, 1, size);
        } else {
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
        }
        elementData[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        int currentCapacity = elementData.length;
        while (currentCapacity + list.size() > elementData.length) {
            grow();
        }
        for (int j = 0; j < list.size(); j++) {
            add(list.get(j));
        }
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T removedElement = (T)elementData[index];
        if (index == 0) {
            System.arraycopy(elementData, 1, elementData, 0, --size);
        } else {
            System.arraycopy(elementData, index + 1, elementData, index, --size - index);
        }
        elementData[size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = getIndexOfElement(element);
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

    private void grow() {
        int newCapacity = (int)(elementData.length * 1.5);
        Object[] array = new Object[newCapacity];
        System.arraycopy(elementData, 0, array, 0, size);
        elementData = array;
    }

    private int getIndexOfElement(T element) {
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

    private void indexCheck(int index) {
        if (size <= index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Incorrect index in the method: " + element);
        }
    }
}
