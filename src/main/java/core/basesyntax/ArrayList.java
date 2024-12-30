package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_CAPACITY = 10;
    public static final double RESIZE_FACTOR = 1.5;

    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void ensureCapacity() {
        if (size == this.elements.length) {
            this.resize((int) (elements.length * RESIZE_FACTOR));
        }
    }

    private void resize(int newCapacity) {
        T[] elements = (T[]) new Object[newCapacity];
        System.arraycopy(this.elements, 0, elements, 0, size);
        this.elements = elements;
    }

    @Override
    public void add(T value) {
        this.ensureCapacity();
        this.elements[size] = value;
        this.size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > this.size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound.");
        }
        this.ensureCapacity();
        T[] swap = (T[]) new Object[size - index];
        System.arraycopy(this.elements, index, swap, 0, size - index);
        this.elements[index] = value;
        System.arraycopy(swap, 0, this.elements, index + 1, size - index);
        this.size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        T swap = elements[index];
        System.arraycopy(this.elements, index + 1, this.elements, index, size - index - 1);
        size--;
        return swap;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == null && element != null) {
                continue;
            }
            if (element == null && elements[i] == null) {
                this.remove(i);
                return null;
            }
            if (elements[i].equals(element)) {
                this.remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No such element for removing");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }
}
