package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        addMoreSize();
        this.elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        addMoreSize();
        errorOfIndexForAdd(index);

        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
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

        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        elements[--size] = null;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == null ? element == null : elements[i].equals(element)) {
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

    private void addMoreSize() {
        if (size == elements.length) {
            int newSize = elements.length + elements.length / 2;
            Object[] newArray = new Object[newSize];

            for (int i = 0; i < elements.length; i++) {
                newArray[i] = elements[i];
            }
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
