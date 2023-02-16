package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elements;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            grow();
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        if (size == elements.length) {
            grow();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            T element = list.get(i);
            add(element);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        elements[index] = value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        final Object[] es = elements;
        T oldValue = (T) es[index];
        final int newSize;
        if ((newSize = size - 1) > index) {
            System.arraycopy(es, index + 1, es, index, newSize - index);
        }
        es[size = newSize] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || elements[i] != null && elements[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element in the list:" + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object[] grow() {
        int newCapacity = elements.length + (elements.length >> 1);
        return elements = Arrays.copyOf(elements, newCapacity);
    }

    @Override
    public Object[] grow(int minCapacity) {
        int newCapacity = minCapacity;
        return elements = Arrays.copyOf(elements, newCapacity);
    }

    @Override
    public void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index:" + index);
        }
    }
}
