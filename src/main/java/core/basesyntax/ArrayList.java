package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private int size;
    private T[] content;

    public ArrayList() {
        content = (T[]) new Object[DEFAULT_SIZE];
    }

    public void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("is not such element");
        }
    }

    @Override
    public void add(T value) {
        resizedArray();
        content[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        resizedArray();
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index are wrong");
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
        checkIndex(index);
        return content[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        content[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T remove = content[index];
        System.arraycopy(content, index + 1, content, index,size - index);
        size--;
        return remove;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((content[i] == element) || content[i] != null && content[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("is not such elements");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void resizedArray() {
        if (size == content.length) {
            T[] resizedArray = (T[]) new Object[size + (size / 2)];
            System.arraycopy(content, 0, resizedArray,0, size);
            content = resizedArray;
        }
    }
}
