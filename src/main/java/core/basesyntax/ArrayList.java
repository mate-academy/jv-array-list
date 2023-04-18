package core.basesyntax;

import java.util.Arrays;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] elements;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new ArrayListIndexOutOfBoundsException("Illegal Capacity: " + initialCapacity);
        }
        this.elements = (T[]) new Object[initialCapacity];
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Illegal Index: " + index);
        }
        ensureCapacity(size + 1);
        System.arraycopy(this.elements, index, this.elements, index + 1, size - index);
        this.elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int numNew = list.size();
        T[] arrayList = (T[]) new Object[numNew];
        for (int i = 0; i < numNew; i++) {
            arrayList[i] = list.get(i);
        }
        System.arraycopy(arrayList, 0, elements, size, numNew);
        size += numNew;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Illegal Index: " + index);
        }
        return this.elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= this.size) {
            throw new ArrayListIndexOutOfBoundsException("Illegal Index:" + index);
        }
        this.elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Illegal Index: " + index);
        }
        T removedElement = this.elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(this.elements, index + 1, this.elements, index, numMoved);
        }
        this.elements[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index == -1) {
            throw new java.util.NoSuchElementException("Element not found in ArrayList");
        } else {
            remove(index);
        }
        return element;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity - this.elements.length > 0) {
            grow(minCapacity);
        }
    }

    private void grow(int minCapacity) {
        int oldCapacity = this.elements.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        if (newCapacity > Integer.MAX_VALUE - 8) {
            throw new OutOfMemoryError();
        }
        this.elements = Arrays.copyOf(this.elements, newCapacity);
    }

    private int indexOf(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (this.elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(this.elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
}
