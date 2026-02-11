package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int GROWTH_DIVISOR = 2;
    private Object[] elements;
    private int size;

    public ArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        this.elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        errorOfIndexForAdd(index);
        ensureCapacity();

        System.arraycopy(elements, index,
                elements, index + 1,
                size - index);

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
        errorOfIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        errorOfIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        errorOfIndex(index);
        T removed = (T) elements[index];

        System.arraycopy(elements, index + 1,
                elements, index,
                size - index - 1);

        elements[--size] = null;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == null
                    ? element == null
                    : elements[i].equals(element)) {
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

    private void ensureCapacity() {
        if (size == elements.length) {
            int newSize = elements.length + elements.length / GROWTH_DIVISOR;
            Object[] newArray = new Object[newSize];

            System.arraycopy(elements, 0,
                    newArray, 0,
                    elements.length);

            this.elements = newArray;
        }
    }

    private void errorOfIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + size
            );
        }
    }

    private void errorOfIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + size
            );
        }
    }
}
