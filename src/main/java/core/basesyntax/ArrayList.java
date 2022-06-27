package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData = new Object[DEFAULT_CAPACITY];
    private int size;

    public ArrayList() {
        Object[] elementData = new Object[DEFAULT_CAPACITY];
    }

    private Object[] grow(int currentCapacity) {
        int newCapacity = currentCapacity + (currentCapacity >> 1);
        return Arrays.copyOf(elementData, newCapacity);
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = grow(size);
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("ArrayListIndexOutOfBoundsException");
        }
        if (size == elementData.length) {
            elementData = grow(size);
        }
        // shift elements to right for one position
        System.arraycopy(elementData, index,
                elementData, index + 1,
                size - index);
        elementData[index] = value;
        size = size + 1;

    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("ArrayListIndexOutOfBoundsException");
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("ArrayListIndexOutOfBoundsException");
        }
        elementData[index] = value;

    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("ArrayListIndexOutOfBoundsException");
        }
        T oldValue = (T) elementData[index];
        // shift element to left for one position with rewriting elementData[index]
        System.arraycopy(elementData, index + 1, elementData, index, size - 1 - index);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int index = 0;
        boolean isFound = false;
        while (index < size) {
            if (element == elementData[index]
                    || element != null && element.equals((T) elementData[index])) {
                isFound = true;
                break;
            }
            index++;
        }
        if (!isFound) {
            throw new NoSuchElementException();
        }
        return remove(index);
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
