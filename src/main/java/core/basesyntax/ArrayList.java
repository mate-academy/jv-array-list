package core.basesyntax;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkCapacity();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexRangeInAdd(index);
        checkCapacity();
        checkElementByIndex(index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            checkCapacity();
            elements[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final Object removedElement = elements[index];
        Object[] copyElements = new Object[elements.length - 1];
        System.arraycopy(elements, 0, copyElements, 0, index);
        System.arraycopy(elements, index + 1, copyElements, index, elements.length - index - 1);
        elements = copyElements;
        size--;
        return (T) removedElement;
    }

    @Override
    public T remove(T element) {
        T removedElement = null;
        if (element == null) {
            for (int i = 0; i < this.size; i++) {
                if (this.elements[i] == null) {
                    removedElement = remove(i);
                    return removedElement;
                }
            }
        } else {
            for (int i = 0; i < this.size; i++) {
                if (element.equals(this.elements[i])) {
                    removedElement = remove(i);
                    return removedElement;
                }
            }
        }
        if (removedElement == null) {
            throw new NoSuchElementException("No such element in list");
        }
        return removedElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkCapacity() {
        if (size == elements.length) {
            ensureCapacity();
        }
    }

    private void ensureCapacity() {
        int newCapacity = elements.length * 2;
        elements = Arrays.copyOf(elements, newCapacity);
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound list");
        }
    }

    private void checkIndexRangeInAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound list");
        }
    }

    private void checkElementByIndex(int index) {
        if (elements[index] != null) {
            System.arraycopy(elements, index, elements, index + 1, size);
        }
    }

}
