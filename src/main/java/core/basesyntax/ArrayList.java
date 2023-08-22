package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double SIZE_MAGNIFICATION = 1.5;

    private Object[] elementData;
    private int size;

    ArrayList() {
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
                    "Can't add element by this index");
        }
        if (size == elementData.length) {
            grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
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
        T removedElement = (T)elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, --size - index);
        elementData[size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = getIndexOfElement(element);
        try {
            remove(index);
        } catch (RuntimeException e) {
            throw new NoSuchElementException(
                    "There is no such element in ArrayList, method: " + element);
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

    private void grow() {
        int newCapacity = (int)(elementData.length * SIZE_MAGNIFICATION);
        Object[] array = new Object[newCapacity];
        System.arraycopy(elementData, 0, array, 0, size);
        elementData = array;
    }

    private int getIndexOfElement(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if ((element == null && element == elementData[i])
                    || (element != null && element.equals(elementData[i]))) {
                index = i;
                break;
            }
        }
        return index;
    }

    private void checkIndex(int index) {
        if (size <= index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Incorrect index");
        }
    }
}
