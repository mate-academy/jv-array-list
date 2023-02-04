package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ARRAYCOPY_POSITION = 0;
    private static final int ARRAYCOPY_MOVE_POSITION = 1;
    private static final double GROW_SIZE = 1.5;
    private static final String INDEX_EXCEPTION_MESSAGE = "ArrayList index out of bounds";
    private static final String NO_SUCH_ELEMENT_EXCEPTION_MESSAGE = "No such element";
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (isFull()) {
            grow();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (isFull()) {
            grow();
        }
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_EXCEPTION_MESSAGE);
        }
        Object indexPositionElement = elements[index];
        elements[index] = value;
        size++;
        Object tempElement;
        for (int i = index + 1; i < size; i++) {
            tempElement = elements[i];
            elements[i] = indexPositionElement;
            indexPositionElement = tempElement;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_EXCEPTION_MESSAGE);
        }
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_EXCEPTION_MESSAGE);
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_EXCEPTION_MESSAGE);
        }
        Object removedElement = elements[index];
        System.arraycopy(elements,index + ARRAYCOPY_MOVE_POSITION,
                elements,index,elements.length - index - ARRAYCOPY_MOVE_POSITION);
        size--;
        return (T)removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elements[i] || elements[i] != null && elements[i].equals(element)) {
                System.arraycopy(elements,i + 1, elements, i,elements.length - i - 1);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException(NO_SUCH_ELEMENT_EXCEPTION_MESSAGE);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == elements.length;
    }

    private void grow() {
        size *= GROW_SIZE;
        Object[] tempElements = new Object[size];
        System.arraycopy(elements, ARRAYCOPY_POSITION, tempElements,
                ARRAYCOPY_POSITION, elements.length);
        for (int i = 0; i < tempElements.length; i++) {
            if (tempElements[i] == null) {
                size--;
            }
        }
        elements = tempElements;
    }
}
