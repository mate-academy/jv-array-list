package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private int size;
    private Object[] elementData;

    public ArrayList() {
        this.elementData = new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (size >= elementData.length) {
            elementData = grow(elementData.length);
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        if (size == elementData.length) {
            elementData = grow(elementData.length);
        }
        if (index == size) {
            elementData[size++] = value;
            return;
        }
        System.arraycopy(elementData, index, elementData,
                index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int minLength = Math.max(list.size() + size, DEFAULT_SIZE);
        if (minLength > elementData.length) {
            grow(minLength);
        }
        for (int i = 0; i < list.size(); i++) {
            elementData[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("This index"
                    + " is out of bound for this list. Index : " + index);
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("This index"
                    + " is out of bound for this list. Index : " + index);
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("This index"
                    + " is out of bound for this list. Index : " + index);
        }
        final T removedElement = (T) elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || element != null && element.equals(elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element present " + element);
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
        if (index > size
                || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can`t operate on index: " + index);
        }
    }

    private Object[] grow(int minLength) {
        int newCapacity = minLength + (minLength >> 1);
        return elementData = Arrays.copyOf(elementData, newCapacity);
    }

}
