package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;

    private int size;
    private Object[] elements;

    public ArrayList() {
        elements = new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            grow();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        final int newIndex = index + 1;
        final int copiedLength = size - index;

        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
        if (size == elements.length) {
            grow();
        }
        System.arraycopy(elements, index, elements, newIndex, copiedLength);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        checkIndexValidity(index);
        return (T)elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexValidity(index);
        elements[index] = value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        final int srcIndex = index + 1;
        final int copiedLength = size - index;

        checkIndexValidity(index);
        Object removedElement = elements[index];
        if (srcIndex == size) {
            elements[index] = null;
        } else {
            System.arraycopy(elements, srcIndex, elements, index, copiedLength);
        }
        size--;
        return (T)removedElement;
    }

    @Override
    public T remove(T element) {
        final int index = getElementIndex(element);
        
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

    private void grow() {
        final int newSize = elements.length + elements.length / 2;
        final int currentSize = elements.length;

        Object[] newArray = new Object[newSize];
        System.arraycopy(elements, 0, newArray, 0, currentSize);
        elements = newArray;
    }

    private void checkIndexValidity(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
    }

    private int getElementIndex(Object element) {
        for (int i = 0; i < size; i++) {
            if ((elements[i] == element)
                    || (elements[i] != null && elements[i].equals(element))) {
                return i;
            }
        }
        throw new NoSuchElementException("No such element found");
    }
}
