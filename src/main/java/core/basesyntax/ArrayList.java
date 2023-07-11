package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private transient Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int initCapacity) {
        if (initCapacity <= 0) {
            throw new IllegalArgumentException(
                    "Can't create array with negative length");
        }
        elements = new Object[initCapacity];
    }

    @Override
    public void add(T value) {
        growIfNeeded();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        addElementArrayListIndexOutOfBoundException(index);
        growIfNeeded();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        growIfNeeded(list);
        int listSize = list.size();
        for (int i = 0; i < listSize; i++) {
            elements[size + i] = list.get(i);
        }
        size += listSize;
    }

    @Override
    public T get(int index) {
        arrayListIndexOutOfBoundException(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        arrayListIndexOutOfBoundException(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        arrayListIndexOutOfBoundException(index);
        T removedElement = (T)elements[index];
        removeElement(index);
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int indexRemovedElement = -1;
        for (int i = 0; i < size; i++) {
            if (elements[i] != null
                    && elements[i].equals(element)
                    || elements[i] == element) {
                indexRemovedElement = i;
                break;
            }
        }
        if (indexRemovedElement == -1) {
            throw new NoSuchElementException("Can't find element: " + element);
        }
        T removedElement = (T)elements[indexRemovedElement];
        removeElement(indexRemovedElement);
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

    private void growIfNeeded() {
        if (elements.length == 1 && elements.length == size) {
            Object[] newArray = new Object[DEFAULT_CAPACITY];
            elements = newArray;
        }
        if (elements.length == size) {
            Object[] newArray = new Object[(int)((double)elements.length * GROWTH_FACTOR)];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    private void growIfNeeded(List<T> list) {
        if (list != null && elements.length < size + list.size()) {
            Object[] newArray = new Object[size + list.size()];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    private void addElementArrayListIndexOutOfBoundException(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index: " + index + " out of array size: " + size);
        }
    }

    private void arrayListIndexOutOfBoundException(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index: " + index + " out of array size: " + size);
        }
    }

    private void removeElement(int index) {
        if (index == elements.length - 1) {
            elements[size - 1] = null;
        }
        System.arraycopy(elements,index + 1, elements, index, size - index - 1);
        size--;
    }
}
