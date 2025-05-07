package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int default_capacity = 10;
    private int size;
    private int capacity;
    private Object[] elements;

    public ArrayList() {
        elements = new Object[default_capacity];
        capacity = default_capacity;
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        isValidIndex(index, true);

        if (size == capacity) {
            grow();
        }

        if (size == index) {
            elements[size] = value;
            size += 1;
            return;
        }

        size += 1;
        Object indexElement = elements[index];
        elements[index] = value;

        for (int i = size - 1; i > index + 1; i--) {
            elements[i] = elements[i - 1];
        }

        elements[index + 1] = indexElement;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new NullPointerException("list is null");
        }

        while (list.size() + size > capacity) {
            grow();
        }

        int j = 0;

        for (int i = size; i < size + list.size(); i++) {
            elements[i] = list.get(j);
            j += 1;
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        isValidIndex(index, false);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        isValidIndex(index, false);

        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        isValidIndex(index, false);
        T removedElement = (T) elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size -= 1;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], element)) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("no such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void isValidIndex(int index, boolean add) {
        if (add) {
            if ((index < 0 || index > size)) {
                throw new ArrayListIndexOutOfBoundsException("invalid Index");
            }
        } else {
            if (index < 0 || index >= size) {
                throw new ArrayListIndexOutOfBoundsException("invalid Index");
            }
        }
    }

    public boolean equals(List<T> list) {
        if (this == list) {
            return true;
        }

        if (list == null) {
            return false;
        }

        if (size != list.size()) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!elements[i].equals(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    private void grow() {
        Object[] oldElements = elements;
        int oldCapacity = size;
        getNewCapacity();
        elements = new Object[capacity];
        for (int i = 0; i < oldCapacity; i++) {
            elements[i] = oldElements[i];
        }
    }

    private void getNewCapacity() {
        capacity = capacity + (capacity >> 1);
    }
}
