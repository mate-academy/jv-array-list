package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 10;
    private T[] list;
    private int size;

    ArrayList() {
        list = (T[]) new Object[CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        grow();
        if (index != size) {
            indexCheck(index);
            System.arraycopy(list, index, list, index + 1, size - index);
        }
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
        indexCheck(index);
        return list[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T element = list[index];
        size--;
        System.arraycopy(list, index + 1, list, index, size - index);
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, list[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can't find element " + element);
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
    public int indexOf(T element) {
        return indexOfRange(element, size);
    }

    private int indexOfRange(T element, int end) {
        if (element == null) {
            for (int i = 0; i < end; i++) {
                if (list[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < end; i++) {
                if (element.equals(list[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    private void indexCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index: " + index);
        }
    }

    private void grow() {
        if (size == list.length) {
            int newCapacity = list.length + (list.length >> 1);
            Object[] newList = new Object[newCapacity];
            System.arraycopy(list, 0, newList, 0, size);
            list = (T[]) newList;
        }
    }
}
