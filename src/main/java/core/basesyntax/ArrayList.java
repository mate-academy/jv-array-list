package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final String INDEX_EXCEPTION_MESSAGE = "index passed to the method is invalid";
    private static final String NO_SUCH_ELEMENT_MESSAGE = "there is no such element";
    private int size;
    private T[] content;

    public ArrayList() {
        content = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resizeArrayIfNeeded();
        content[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        resizeArrayIfNeeded();
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_EXCEPTION_MESSAGE);
        }
        System.arraycopy(content, index, content, index + 1, size - index);
        content[index] = value;
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
        indexCheck(index);
        return content[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        content[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T deleted = content[index];
        System.arraycopy(content, index + 1, content, index, size - index);
        size--;
        return deleted;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((content[i] == element) || (content[i] != null && content[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(NO_SUCH_ELEMENT_MESSAGE);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void indexCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_EXCEPTION_MESSAGE);
        }
    }

    private void resizeArrayIfNeeded() {
        if (size == content.length) {
            T[] resizedArray = (T[]) new Object[size + (size / 2)];
            System.arraycopy(content, 0, resizedArray, 0, size);
            content = resizedArray;
        }
    }
}
