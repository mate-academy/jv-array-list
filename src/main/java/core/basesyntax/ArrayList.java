package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double DEFAULT_GROW_ARRAY = 1.5;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        grow();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        grow();
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Such an index " + index + " is invalid for size " + size);
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
        T element = get(index);
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        int index = findItIndex(element);
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void grow() {
        if (size == elements.length) {
            Object[] newArray = new Object[(int) (size * DEFAULT_GROW_ARRAY)];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    private int findItIndex(T element) {
        for (int i = 0; i < size; i++) {
            if (element == get(i) || (get(i) != null && get(i).equals(element))) {
                return i;
            }
        }
        throw new NoSuchElementException("not found element in array");
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Such an index " + index + " is invalid for size " + size);
        }
    }
}
