package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double LIST_ENLARGER = 1.5;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ARRAY_BEGINNING = 0;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        correctSize();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexAdding(index);
        correctSize();
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

    @SuppressWarnings("unchecked")
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

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedElement;
    }

    @SuppressWarnings("unckecked")
    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || element != null && element.equals(elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("This element doesn`t exist in in the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndexAdding(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("You can`t work with index " + index);
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("You can`t work with index " + index);
        }
    }

    private void correctSize() {
        if (elements.length == size) {
            Object[] internalArray = new Object[(int) (size * LIST_ENLARGER)];
            System.arraycopy(elements, ARRAY_BEGINNING, internalArray, ARRAY_BEGINNING, size);
            elements = internalArray;
        }
    }
}
