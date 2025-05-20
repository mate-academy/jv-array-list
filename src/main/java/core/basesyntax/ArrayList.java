package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkInsertIndex(index);
        ensureCapacity(size + 1);

        if (index < size) {
            System.arraycopy(elements, index, elements, index + 1, size - index);
        }

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
        checkAccessIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkAccessIndex(index);
        elements[index] = value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        checkAccessIndex(index);

        T removeElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[size - 1] = null;
        size--;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        int index = getElementIndex(element);

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

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = elements.length;
            while (newCapacity < minCapacity) {
                newCapacity += (newCapacity >> 1);
            }

            Object[] temp = new Object[newCapacity];
            System.arraycopy(elements, 0, temp, 0, size);
            elements = temp;
        }
    }

    private void checkAccessIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: "
                    + index + ". Allowed range: 0 to " + (size - 1)
            );
        }
    }

    private void checkInsertIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: "
                    + index + ". Allowed range for add: 0 to " + size
            );
        }
    }

    @SuppressWarnings("unchecked")
    private int getElementIndex(T element) {
        for (int i = 0; i < size; i++) {
            T currentElement = (T) elements[i];
            if (element == null ? currentElement == null : element.equals(currentElement)) {
                return i;
            }
        }
        throw new NoSuchElementException("Element not found");
    }
}
