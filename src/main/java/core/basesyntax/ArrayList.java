package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INIT_CAPACITY = 10;

    private Object[] elements = new Object[INIT_CAPACITY];
    private int size = 0;

    @Override
    public void add(T value) {
        if (size == elements.length) {
            increaseSize();
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexBounds(index);
        if (size == elements.length) {
            increaseSize();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
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
        checkIndexBoundsExcludingLastElement(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexBoundsExcludingLastElement(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexBoundsExcludingLastElement(index);
        T element = get(index);
        size--;
        System.arraycopy(elements, index + 1, elements, index, size - index);
        return element;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index == -1) {
            throw new NoSuchElementException("No such element");
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

    private void increaseSize() {
        Object[] newElements = new Object[size + (size >> 1)];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    private int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elements[i] || (element != null && element.equals(elements[i]))) {
                return i;
            }
        }
        return -1;
    }

    private void checkIndexBounds(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", but size: " + size);
        }
    }

    private void checkIndexBoundsExcludingLastElement(int index) {
        if (index == size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", but size: " + size);
        }
        checkIndexBounds(index);
    }
}
