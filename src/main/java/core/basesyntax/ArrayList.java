package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int DEFAULT_SIZE = 0;
    private static final String EXCEPTION_MESSAGE = "index passed to the method is invalid";
    private static final String NO_SUCH_ELEMENT_MESSAGE = "there is no such element";
    private int size;
    private T[] content;

    public ArrayList() {
        content = (T[]) new Object[DEFAULT_CAPACITY];
        size = DEFAULT_SIZE;
    }

    @Override
    public void add(T value) {
        if (size == content.length) {
            content = resizeArray();
        }
        content[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == content.length) {
            content = resizeArray();
        }
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(EXCEPTION_MESSAGE);
        }
        System.arraycopy(content, index, content, index + 1, content.length - index - 1);
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
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(EXCEPTION_MESSAGE);
        }
        return content[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(EXCEPTION_MESSAGE);
        }
        content[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(EXCEPTION_MESSAGE);
        }
        T removingItem = content[index];
        System.arraycopy(content, index + 1, content, index, size - index);
        size--;
        return removingItem;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((content[i] == null && element == null) || ((content[i] != null && element != null)
                    && content[i].equals(element))) {
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

    private T[] resizeArray() {
        T[] resizedArray = (T[]) new Object[size + (size / 2)];
        System.arraycopy(content, 0, resizedArray, 0, size);
        return resizedArray;
    }
}
