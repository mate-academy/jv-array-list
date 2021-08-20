package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            grow();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        if (index == size) {
            add(value);
            return;
        }
        add(elements[size - 1]);
        for (int i = size - 3; i >= index; i--) {
            set(elements[i], i + 1);
        }
        set(value, index);
    }

    @Override
    public void addAll(List<T> list) {
        ArrayList<T> arrayList = (ArrayList<T>) list;
        for (int i = 0; i < arrayList.size; i++) {
            add(arrayList.elements[i]);
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        return index < size ? elements[index] : null;
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        T element = elements[index];
        for (int i = index; i < size; i++) {
            set(i == size - 1 ? null : elements[i + 1], i);
        }
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        int index = 0;
        boolean isFound = false;
        for (int i = 0; i < size; i++) {
            if ((elements[i] == element) || (element != null && element.equals(elements[i]))) {
                index = i;
                isFound = true;
            }
        }
        if (!isFound) {
            throw new NoSuchElementException("No such value");
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

    private void grow() {
        elements = Arrays.copyOf(elements, size + (size / 2));
    }
}
