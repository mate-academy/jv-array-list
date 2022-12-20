package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    public static final double GROW_MULTIPLIER = 1.5;
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size + 1 > elements.length) {
            elements = grow();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
        if (size >= elements.length) {
            elements = grow();
        }
        if (size == index) {
            elements[index] = value;
            size++;
            return;
        }
        Object[] temp = new Object[elements.length];
        for (int i = 0; i < index; i++) {
            temp[i] = elements[i];
        }
        temp[index] = value;
        for (int i = index + 1; i < size + 1; i++) {
            temp[i] = elements[i - 1];
        }
        elements = temp;
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
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
        T removedElement = (T) elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size - 1; i++) {
            if (element == null && elements[i] == null
                    || element != null && element.equals(elements[i])) {
                return remove(i);
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

    private Object[] grow() {
        return Arrays.copyOf(elements, (int) Math.round(elements.length * GROW_MULTIPLIER));
    }
}
