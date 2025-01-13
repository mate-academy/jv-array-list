package core.basesyntax;

import java.util.Arrays;

public final class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MAGIC_NUMBER = 3;
    private int size;
    private Object[] elements;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void ensureCapacity(int size) {
        if (size == elements.length) {
            grow();
        }
    }

    private void indexCheck(final int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of bounds for add operation");
        }
    }

    private void grow() {
        int newCapacity = elements.length * MAGIC_NUMBER / 2 + 1;
        elements = Arrays.copyOf(elements, newCapacity);
    }

    @Override
    public void add(final T element) {
        ensureCapacity(size);
        elements[size++] = element;
    }

    @Override
    public void add(final T value, final int index) {
        indexCheck(index);
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(final int index) {
        indexCheck(index);
        return (T) elements[index];
    }

    @Override
    public void set(final T value, final int index) {

        for (int i = 0; i < elements.length; i++) {
            if (i == index) {
                elements[i] = value;
            }
        }
    }

    @Override
    public T remove(final int index) {
        indexCheck(index);
        final T removedElement = (T) elements[index];
        System.arraycopy(elements, index + 1,
                elements, index, size - index - 1);
        elements[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(final T element) {
        ensureCapacity(size);
        for (int i = 0; i < size; i++) {
            if ((element == null && elements[i] == null)
                    || (element != null && element.equals(elements[i]))) {
                return remove(i);
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
