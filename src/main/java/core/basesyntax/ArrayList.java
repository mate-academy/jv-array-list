package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_SIZE = 10;
    private int size;
    private Object [] elements;

    public ArrayList() {
        elements = new Object[DEFAULT_ARRAY_SIZE];
    }

    public void grow(int minCapacity) {
        int oldCapacity = elements.length;
        int newCapacity = Math.max(minCapacity - oldCapacity,
                oldCapacity >> 1) + oldCapacity;
        elements = Arrays.copyOf(elements, newCapacity);
    }

    public void existenceCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of the bounds");
        }
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            grow(size + 1);
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of the bounds");
        } else if (size == elements.length) {
            grow(size + 1);
        }
        System.arraycopy(elements,index,
                elements, index + 1,
                size - index);
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
        existenceCheck(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        existenceCheck(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        existenceCheck(index);
        T removedElement = (T) elements[index];
        for (int i = 0; i < 1; i++) {
            for (int j = index; j < size - 1; j++) {
                elements[j] = elements[j + 1];
            }
            size = size - 1;
            elements = Arrays.copyOf(elements,size);
        }
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int found = 0;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, elements[i])) {
                for (int j = i; j < size - 1; j++) {
                    elements[j] = elements[j + 1];
                }
                found++;
            }
        }
        if (found == 0) {
            throw new NoSuchElementException();
        }
        size = size - 1;
        elements = Arrays.copyOf(elements,size);
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
}
