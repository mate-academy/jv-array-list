package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int DEFAULT_SIZE = 0;
    private T removedValue;
    private Object[] list;
    private int size;

    public ArrayList() {
        list = new Object[DEFAULT_CAPACITY];
        size = DEFAULT_SIZE;
    }

    public Object[] grow(int size) {
        return list = Arrays.copyOf(list, (size + size / 2));
    }

    @Override
    public void add(T value) {
        if (size >= list.length) {
            list = grow(size);
        }
        list[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("index is incorrect");
        }
        if (size >= list.length) {
            list = grow(size);
        }
        System.arraycopy(list, index, list, index + 1, size - index);
        list[index] = value;
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
        if (isIndexValid(index)) {
            return (T) list[index];
        }
        throw new ArrayListIndexOutOfBoundsException("Index is invalid");
    }

    @Override
    public void set(T value, int index) {
        if (isIndexValid(index)) {
            list[index] = value;
            return;
        }
        throw new ArrayListIndexOutOfBoundsException("Index is not valid");
    }

    @Override
    public T remove(int index) {
        if (isIndexValid(index)) {
            removedValue = (T) list[index];
            if (size == 1) {
                list[index] = null;
            }
            for (int i = index; i < size - 1; i++) {
                list[i] = list[i + 1];
            }
            size--;
            return removedValue;
        }
        throw new ArrayListIndexOutOfBoundsException("Index is incorrect");
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == list[i] || element != null && element.equals(list[i])) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No such element found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return !(size > 0);
    }

    private boolean isIndexValid(int index) {
        return index >= 0 && index < size;
    }
}
