package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData = new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[size] = value;
        size++;
    }

    private Object[] grow() {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        return elementData = Arrays.copyOf(elementData, newCapacity);
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size + 1 ) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }
        if (size == elementData.length || index > size) elementData = grow();
        Object[] elementsAfterIndex = new Object[this.size - index + 1];
        for (int i = 0; i < elementsAfterIndex.length; i++) {
            elementsAfterIndex[i] = elementData[index + i];
        }
        elementData[index] = value;
        for (int i = index +  1; i <= size; i++) {
            elementData[i] = elementsAfterIndex[i - index - 1];
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {
       for (int i = 0; i < list.size(); i++) {
           T elem = (T) list.get(i);
           add(elem);
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds");
        }
        T value = (T) elementData[index];
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
       for (int i = 0; i < elementData.length; i++) {
            if ((elementData[i] != null && element != null && elementData[i].equals(element)) || (elementData[i] == null && element == null)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element was not founded");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }
}
