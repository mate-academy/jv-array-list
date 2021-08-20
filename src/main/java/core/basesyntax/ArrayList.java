package core.basesyntax;

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
            throw new ArrayListIndexOutOfBoundsException("Invalid index, must be 0 < i < size");
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
        if (checkIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index, must be 0 < i <= size");
        }
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (checkIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index, must be 0 < i <= size");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (checkIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index, must be 0 < i <= size");
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
        boolean isFound = false;
        for (int i = 0; i < size; i++) {
            if ((elements[i] == element) || (element != null && element.equals(elements[i]))) {
                remove(i);
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            throw new NoSuchElementException("No such value was found");
        }
        return element;
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
        T[] resizedArr = (T[]) new Object[size + (size / 2)];
        System.arraycopy(elements, 0, resizedArr, 0, elements.length);
        elements = resizedArr;
    }

    private boolean checkIndex(int index) {
        return (index >= size || index < 0);
    }
}
