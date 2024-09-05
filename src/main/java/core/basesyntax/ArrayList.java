package core.basesyntax;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_LIST = {};

    private Object[] listElements;
    private int size = 0;

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.listElements = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.listElements = EMPTY_LIST;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(Collection<? extends T> collection) {
        this(collection.size());

        for (T collectionElement : collection) {
            add(collectionElement);
        }
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);

        this.listElements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        ensureCapacity(size + 1);

        size++;

        checkIndex(index);

        T[] afterPart = (T[]) Arrays.copyOfRange(listElements, index, size - 1);

        this.listElements[index] = value;

        for (int i = 0; i < afterPart.length; i++) {
            this.listElements[index + 1 + i] = afterPart[i];
        }
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

        return (T) listElements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);

        this.listElements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        T removedElement = (T) listElements[index];

        size--;

        for (int i = index; i < size; i++) {
            this.listElements[i] = this.listElements[i + 1];
        }

        return removedElement;
    }

    @Override
    public T remove(T element) {
        int findedIndex = -1;

        for (int i = 0; i < size; i++) {
            if (listElements[i] != null ? listElements[i].equals(element) : element == null) {
                findedIndex = i;
            }
        }

        if (findedIndex == -1) {
            throw new NoSuchElementException();
        }

        return remove(findedIndex);
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
        if (minCapacity > listElements.length) {
            this.listElements = grow();
        }
    }

    private T[] grow() {
        int oldCapacity = listElements.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);

        return (T[]) Arrays.copyOf(listElements, newCapacity);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
