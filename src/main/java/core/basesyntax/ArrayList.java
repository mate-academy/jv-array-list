package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_FACTOR = 1.5;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (elements.length == size) {
            grow();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index Out Of Bounds Exception " + index);
        }
        if (elements.length == size) {
            grow();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] addAll = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            addAll[i] = list.get(i);
        }
        int length = list.size();
        if (length > elements.length - size) {
            grow(length);
        }
        System.arraycopy(addAll, 0, elements, size, length);
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
        size--;
        T remove = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index);
        return remove;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elements[i]
                    || (elements[i] != null && elements[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No Such Element " + element);
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
        Object[] newArray = new Object[(int) (size * GROW_FACTOR)];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }

    private void grow(int addSize) {
        Object[] newArray = new Object[(int) (size + addSize)];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }

    private void indexCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index Out Of Bounds Exception " + index);
        }
    }
}
