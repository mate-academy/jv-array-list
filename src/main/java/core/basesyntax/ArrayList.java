package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elements;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void ensureCapacity() {
        int newSize = (int) (elements.length * 1.5);
        elements = Arrays.copyOf(elements, newSize);
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
    }

    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            ensureCapacity();
        }
        elements[size] = value;
        ++size;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        if (size == elements.length) {
            ensureCapacity();
        }
        System.arraycopy(elements, index,
                elements, index + 1,
                size - index);
        elements[index] = value;
        ++size;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() > elements.length - size) {
            ensureCapacity();
        }
        for (int index = size, listIndex = 0; index < (size + list.size()); ++index, ++listIndex) {
            elements[index] = list.get(listIndex);
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        rangeCheck(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheck(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        Object item = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return (T) item;
    }

    @Override
    public T remove(T element) {
        Object item = null;
        int index = 0;
        for (; index < size; ++index) {
            if (elements[index] != null && elements[index].equals(element)) {
                item = remove(index);
                break;
            } else if (elements[index] == element) {
                item = remove(index);
                break;
            }
        }
        if (index == size) {
            throw new NoSuchElementException("There is no element like this!!!");
        }
        return (T) item;
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
