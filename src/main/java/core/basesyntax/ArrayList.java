package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_INDEX = 1.5;
    private Object[] objects;
    private int size;

    public ArrayList() {
        objects = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        expandArray();
        objects[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range");
        }
        if (index == size()) {
            add(value);
            return;
        }
        expandArray();
        Object[] arrExpanded = new Object[objects.length];
        System.arraycopy(objects, 0, arrExpanded, 0, index);
        arrExpanded[index] = value;
        System.arraycopy(objects, index, arrExpanded, index + 1, size() - index);
        objects = arrExpanded;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (objects == null) {
            throw new NullPointerException("No elements to add");
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) objects[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        objects[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T removed = (T) objects[index];
        System.arraycopy(objects, index + 1, objects, index, size() - 1 - index);
        size--;
        objects[size] = null;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i <= size; i++) {
            if (Objects.equals(element, objects[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found:" + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range");
        }
    }

    private void expandArray() {
        if (objects.length <= size) {
            int expanded = (int) (size * GROWTH_INDEX);
            Object[] temp = new Object[expanded];
            System.arraycopy(objects, 0, temp, 0, size);
            objects = temp;
        }
    }
}
