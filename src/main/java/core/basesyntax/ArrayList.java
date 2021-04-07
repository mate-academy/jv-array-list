package core.basesyntax;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private int size;
    private T[] container;

    public ArrayList() {
        container = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            grow();
        }
        container[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, size, "Trying to add at non existing index");
        if (size == container.length) {
            grow();
        }
        System.arraycopy(container, index, container, index + 1, size - index);
        container[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (T element : list) {
            add(element);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index, size - 1, "Trying to get value at non existing or negative index");
        return container[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, size - 1, "Trying to change value at non existing or negative index");
        container[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, size - 1, "Trying to remove at nonexistent or minus index");
        T element = container[index];
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        return remove(findIndex(element));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private int findIndex(T element) {
        for (int i = 0; i < size; i++) {
            if (container[i] == element || container[i] != null && container[i].equals(element)) {
                return i;
            }
        }
        throw new NoSuchElementException("The element wasn't found in container");
    }

    private void grow() {
        container = Arrays.copyOf(container, container.length + (container.length >> 1));
    }

    private void checkIndex(int index, int size, String message) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(message);
        }
    }

    private class ArrayListIterator implements Iterator<T> {
        private int index;

        public ArrayListIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return container[index++];
        }
    }
}
