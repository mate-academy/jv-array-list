package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resizeIfNeeded(size + 1);
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, false);
        resizeIfNeeded(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
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
        checkIndex(index, true);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index,true);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, false);
        T removed = get(index);
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
        return removed;
    }

    @Override
    public T remove(T element) {
        int index = getIndex(element);
        if (index != -1) {
            T removed = elements[index];
            remove(index);
            return removed;
        } else if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    T removed = elements[i];
                    remove(i);
                    return removed;
                }
            }
        }
        throw new NoSuchElementException("Element not found: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeIfNeeded(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = elements.length + elements.length / 2;
            T[] newArray = (T[]) new Object[newCapacity];
            for (int i = 0; i < elements.length; i++) {
                newArray[i] = elements[i];
            }
            elements = newArray;
        }
    }

    private void checkIndex(int index, boolean included) {
        if (index < 0 || (included ? index >= size : index > size)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private void checkIndex1(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private void checkIndex2(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private int getIndex(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == elements[i])
                    || (element != null && element.equals(elements[i]))) {
                return i;
            }
        }
        return -1;
    }
}
