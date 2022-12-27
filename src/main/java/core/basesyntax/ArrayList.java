package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    private void grow(Object[] o) {
        elements = Arrays.copyOf(o, elements.length + elements.length / 2);
    }

    private void grow(Object[] o, int addSize) {
        elements = Arrays.copyOf(o, elements.length + addSize);
    }

    private void indexCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index Out Of Bounds Exception");
        }
    }

    @Override
    public void add(T value) {
        if (elements.length == size) {
            grow(elements);
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index Out Of Bounds Exception");
        }
        if (index == size || elements.length == size) {
            grow(elements);
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] a = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            a[i] = list.get(i);
        }
        int length = list.size();
        if (length > elements.length - size) {
            grow(elements, length);
        }
        System.arraycopy(a, 0, elements, size, length);
        size += length;
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        if (elements.length == size) {
            grow(elements);
        }
        T remove = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index);
        size--;
        return remove;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elements[i] != null && elements[i].equals(element))
                    || (element == null && elements[i] == null)) {
                System.arraycopy(elements, i + 1, elements, i, size - i);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("No Such Element");
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
