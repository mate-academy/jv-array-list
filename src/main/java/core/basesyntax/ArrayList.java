package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_MULTIPLIER = 1.5;
    private int size = 0;
    private Object[] elements;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            expandCapacity();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == elements.length) {
            expandCapacity();
        }
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid." + index);
        }
        System.arraycopy(elements,index,elements,index + 1,size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            return;
        }
        int delta;
        while ((delta = elements.length - size - list.size()) < 0) {
            expandCapacity();
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T deleted = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return deleted;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element in list: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void expandCapacity() {
        int newSize = (int) (GROW_MULTIPLIER * elements.length);
        Object[] elementsNew = new Object[newSize];
        System.arraycopy(elements, 0, elementsNew, 0, elements.length);
        elements = elementsNew;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid." + index);
        }
    }
}
