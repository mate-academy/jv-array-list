package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int GROWTH_FACTOR = 1;
    private static final int DEFAULT_CAPACITY = 10;
    private int capacity = DEFAULT_CAPACITY;
    private T[] elements = (T[])new Object[capacity];
    private int size = 0;

    @Override
    public void add(T value) {
        this.ensureCapacity();

        this.elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > this.size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ",size: " + this.size);
        }

        this.ensureCapacity();

        System.arraycopy(this.elements, index, this.elements, index + 1, size - index);

        this.size++;
        this.elements[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        while (this.size + list.size() > capacity) {
            this.grow();
        }

        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        this.indexValidation(index);

        return this.elements[index];
    }

    @Override
    public void set(T value, int index) {
        this.indexValidation(index);

        this.elements[index] = value;
    }

    @Override
    public T remove(int index) {
        this.indexValidation(index);

        T elementToRemove = this.elements[index];

        System.arraycopy(this.elements, index + 1, this.elements, index, size - index - 1);

        this.elements[--size] = null;

        return elementToRemove;
    }

    @Override
    public T remove(T element) {
        int elementIndex = -1;

        for (int i = 0; i < this.size; i++) {
            if (this.elements[i] == null) {
                if (this.elements[i] == element) {
                    elementIndex = i;
                    break;
                }
            } else {
                if (this.elements[i].equals(element)) {
                    elementIndex = i;
                    break;
                }
            }
        }

        if (elementIndex == -1) {
            throw new NoSuchElementException("Element not found: " + element);
        }

        return this.remove(elementIndex);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.elements[i] = null;
        }

        this.size = 0;
    }

    private void grow() {
        this.capacity = this.capacity + (this.capacity >> GROWTH_FACTOR);
        T[] newArr = (T[])new Object[this.capacity];

        System.arraycopy(this.elements, 0, newArr, 0, this.size);

        this.elements = newArr;
    }

    private void indexValidation(int index) {
        if (index < 0 || index >= this.size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ",size: " + this.size);
        }
    }

    private void ensureCapacity() {
        if (this.size == capacity) {
            this.grow();
        }
    }
}
