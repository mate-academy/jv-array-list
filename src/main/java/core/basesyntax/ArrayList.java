package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList(int initCapacity) {
        if (initCapacity <= 0) {
            throw new IllegalArgumentException("IllegalArgument: " + initCapacity);
        }
        this.elements = new Object[initCapacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void add(T element) {
        resizeArray();
        elements[size] = element;
        size++;
    }

    @Override
    public void add(T element, int index) {
        if (index > elements.length || index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index more than array size");
        }
        resizeArray();
        System.arraycopy(elements, index, elements,index + 1,size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.size() + size <= elements.length) {
                add(list.get(i));
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndexOutOfBounds(index);
        return (T) elements[index];
    }

    @Override
    public void set(T element, int index) {
        checkIndexOutOfBounds(index);
        elements[index] = element;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndexOutOfBounds(index);
        T removedElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == null && element == null
                    || elements[i] != null && elements[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndexOutOfBounds(int index) {
        if (index > elements.length || index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Index more than array size");
        }
    }

    private void resizeArray() {
        if (elements.length == size) {
            Object[] newArray = new Object[(int)(size * 1.5)];
            System.arraycopy(elements, 0, newArray, 0, size);
            this.elements = newArray;
        }
    }
}
