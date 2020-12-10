package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void grow() {
        elements = Arrays.copyOf(elements, elements.length + elements.length / 2);
    }

    private boolean isIndexValid(int index) {
        if (index >= 0 && index < size) {
            return true;
        }
        throw new ArrayIndexOutOfBoundsException("Wrong index. The index must be between"
                + " 0 and size: " + size);
    }

    @Override
    public void add(T value) {
        if (size == elements.length - 1) {
            grow();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 ^ index > size) {
            throw new ArrayIndexOutOfBoundsException("Wrong index. The index must be between"
                    + " 0 and size: " + size);
        }
        if (size == elements.length - 1) {
            grow();
        }
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
        isIndexValid(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexValid(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexValid(index);
        T removeValue = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removeValue;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if ((t == elements[i]) || (elements[i] != null && elements[i].equals(t))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("List doesn't have this value");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
