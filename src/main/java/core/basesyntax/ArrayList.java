package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            elements = (T[]) grow();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        if (size == elements.length) {
            elements = (T[]) grow();
        }
        System.arraycopy(elements, index,
                elements, index + 1,
                size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        rangeCheckForGet(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheckForGet(index);
        T oldValue = (T) elements[index];
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheckForGet(index);
        T removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index,size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        final int size = this.size;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("element: '" + element + "' does not exist in the list");
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
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        return elements = Arrays.copyOf(elements,
                newCapacity);
    }

    private void fastRemove(Object[] es, int i) {
        final int newSize;
        if ((newSize = size - 1) > i) {
            System.arraycopy(es, i + 1, es, i, newSize - i);
        }
        es[size = newSize] = null;
    }

    private void rangeCheckForGet(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " doesn't exist in ArrayList. Please enter value from 0 to " + size);
        }
    }

    public void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " of bounds the ArrayList. Please enter value from 0 to " + size);
        }
    }
}
