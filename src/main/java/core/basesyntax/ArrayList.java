package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INIT_SIZE = 10;
    
    private Object[] elements = new Object[INIT_SIZE];
    private int filledElements = 0;

    @Override
    public void add(T value) {
        add(value, filledElements);
    }

    @Override
    public void add(T value, int index) {
        if (!isValidAddIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("index cant be lower than 0 "
                    + "or value cant be added to the non-initialized part of array!");
        }

        if (filledElements == elements.length) {
            expandArray();
        }

        System.arraycopy(elements, index, elements, index + 1, filledElements - index);
        elements[index] = value;
        filledElements++;
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
        if (!isInArrayBounds(index)) {
            throw new ArrayListIndexOutOfBoundsException("the value in index you have entered "
                    + "is not reachable!");
        }
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (!isInArrayBounds(index)) {
            throw new ArrayListIndexOutOfBoundsException("the value in index you have entered "
                    + "is not reachable!");
        }

        elements[index] = value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        if (!isInArrayBounds(index)) {
            throw new ArrayListIndexOutOfBoundsException("the value in index you have entered "
                    + "is not reachable!");
        }

        final T removed = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, filledElements - index - 1);
        filledElements--;
        elements[filledElements] = null;
        return removed;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(T element) {
        for (int i = 0; i < filledElements; i++) {
            if (element == null ? elements[i] == null : element.equals(elements[i])) {
                final T removed = (T) elements[i];
                System.arraycopy(elements, i + 1, elements, i, filledElements - i - 1);
                filledElements--;
                elements[filledElements] = null;
                return removed;
            }
        }
        throw new NoSuchElementException("There is no such element!");
    }

    @Override
    public int size() {
        return filledElements;
    }

    @Override
    public boolean isEmpty() {
        return filledElements == 0;
    }

    private void expandArray() {
        int newCapacity = elements.length + (elements.length >> 1);
        Object[] newArray = new Object[newCapacity];

        System.arraycopy(elements, 0, newArray, 0, filledElements);

        elements = newArray;
    }

    private boolean isInArrayBounds(int index) {
        return index >= 0 && index < filledElements;
    }

    private boolean isValidAddIndex(int index) {
        return index >= 0 && index <= filledElements; // <= для конца массива
    }
}
