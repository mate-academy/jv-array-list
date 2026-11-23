package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements = new Object[DEFAULT_CAPACITY];
    private int size = 0;

    @Override
    public void add(T value) {
        if (size == elements.length) {
            increaseCapacity();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {

        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index");
        }

        if (size == elements.length) {
            increaseCapacity();
        }

        System.arraycopy(elements, index, elements, index + 1, size - index);

        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {

        int originalSize = list.size();

        for (int listIndex = 0; listIndex < originalSize; listIndex++) {
            add(list.get(listIndex));
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

        final T result = (T)elements[index];

        System.arraycopy(elements, index + 1, elements, index, size - index - 1);

        size--;
        elements[size] = null;

        return result;
    }

    @Override
    public T remove(T element) {

        for (int elementIndex = 0; elementIndex < size; elementIndex++) {
            if (element == null ? elements[elementIndex] == null
                    : element.equals(elements[elementIndex])) {
                return remove(elementIndex);
            }
        }

        throw new NoSuchElementException("This element: " + element + " is not on the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    private void increaseCapacity() {

        Object[] expandedElements = new Object[elements.length + (elements.length >> 1)];

        System.arraycopy(elements, 0, expandedElements, 0, elements.length);

        elements = expandedElements;
    }

    private void checkIndex(int index) {
        if ((index >= 0 && index <= size - 1)) {
            return;
        }

        throw new ArrayListIndexOutOfBoundsException("Invalid index");
    }
}
