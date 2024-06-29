package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double CAPACITY_INDEX = 1.5;
    private int size;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        resizeArray();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        resizeArray();
        System.arraycopy(elements, index, elements, index + 1, size++ - index);
        elements[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements,index, --size - index);
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || (elements[i] != null && elements[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " non-existent");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeArray() {
        int currentCapacity = elements.length;
        int newCapacity = (int) (currentCapacity * CAPACITY_INDEX);
        if (size == elements.length) {
            T[] newArray = (T[]) new Object[newCapacity];
            System.arraycopy(elements, 0, newArray,0, size);
            elements = newArray;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index " + index + " non-existent");
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("index " + index + " non-existent");
        }
    }
}
