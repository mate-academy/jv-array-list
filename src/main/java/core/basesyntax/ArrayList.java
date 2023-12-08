package core.basesyntax;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        resizeIfNeeded();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexValidForAdd(index);
        resizeIfNeeded();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    private void checkIndexValidForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void checkIndexValidForGet(int index) {
        if (index == size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void resizeIfNeeded() {
        if (size == array.length) {
            resize();
        }
    }

    private void resize() {
        T[] newArray = (T[]) new Object[(int) (array.length * 1.5)];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.isEmpty()) {
            return;
        }
        ensureCapacity(list);
        for (T element : list) {
            add(element);
        }
    }

    private void ensureCapacity(List<T> list) {
        while (size + list.size() > array.length) {
            resize();
        }
    }

    @Override
    public T get(int index) {
        checkIndexValidForGet(index);
        return array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexValidForGet(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexValidForGet(index);
        final T removedElement = array[index];
        if (index < size - 1) {
            System.arraycopy(array, index + 1, array, index, size - 1 - index);
        }
        array[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null && array[i] == null || element != null && element
                    .equals(array[i])) {
                final T removedElement = array[i];
                if (i < size - 1) {
                    System.arraycopy(array, i + 1, array, i, size - i - 1);
                }
                array[size - 1] = null;
                size--;
                return removedElement;
            }
        }
        throw new NoSuchElementException("Element " + element + " not found in the list.");
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
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[index++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Remove not supported.");
            }
        };
    }
}
