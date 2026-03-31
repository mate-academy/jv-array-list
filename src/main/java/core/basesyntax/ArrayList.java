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

    public ArrayList(T[] elements) {
        if (elements == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }
        this.elements = new Object[DEFAULT_CAPACITY < elements.length
                ? elements.length : DEFAULT_CAPACITY];
        int i = 0;
        for (T elem : elements) {
            this.elements[i] = elem;
            i++;
        }
        this.size = elements.length;
    }

    private void grow() {
        int newCapacity = elements.length + elements.length / 2;
        Object[] newElements = new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    private void arrayListIndexOutOfBoundException(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index + " out of bounds for size " + size);
        }
    }

    @Override
    public void add(T value) {
        if (this.size >= this.elements.length) {
            grow();
        }
        elements[size] = value;
        this.size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + index + " out of bounds for size " + size);
        }
        if (this.size >= this.elements.length) {
            grow();
        }
        System.arraycopy(
                this.elements,
                index,
                this.elements,
                index + 1,
                size - index
        );
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new IllegalArgumentException("List cannot be null");
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        arrayListIndexOutOfBoundException(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        arrayListIndexOutOfBoundException(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        arrayListIndexOutOfBoundException(index);
        final T removed = (T) this.elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(
                    elements,
                    index + 1,
                    elements,
                    index,
                    numMoved
            );
        }
        elements[size - 1] = null;
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i <= size - 1; i++) {
            if (element == null) {
                if (this.elements[i] == null) {
                    index = i;
                    break;
                }
            } else {
                if (element.equals(this.elements[i])) {
                    index = i;
                    break;
                }
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("Element not found: " + element);
        }
        return remove(index);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
