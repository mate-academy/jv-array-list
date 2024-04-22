package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    public static final int DEFAULT_CAPACITY = 10;
    private T[] listContent = (T[]) new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        if (size + 1 > listContent.length) {
            extendArray(size + (size >> 1));
        }
        listContent[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("ArrayList index out of bounds");
        }

        if (size + 1 > listContent.length) {
            extendArray(size + (size >> 1));
        }

        System.arraycopy(listContent, index, listContent, index + 1, size - index);
        listContent[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() > listContent.length - size) {
            extendArray(size + list.size());
        }

        for (int i = 0; i < list.size(); i++) {
            listContent[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return listContent[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        listContent[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        T removed = listContent[index];

        if (index + 1 == size) {
            listContent[index] = null;
            size--;
            return removed;
        }

        for (int i = index; i < size; i++) {
            listContent[i] = listContent[i + 1];
        }
        size--;

        return removed;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index == -1) {
            throw new NoSuchElementException("No such element");
        }
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

    private int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (listContent[i] == element || listContent[i] != null
                    && listContent[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    private void extendArray(int newSize) {
        T[] arrayCopy = (T[]) new Object[newSize];
        System.arraycopy(listContent, 0, arrayCopy, 0, listContent.length);
        listContent = arrayCopy;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("ArrayList index out of bounds");
        }
    }
}
