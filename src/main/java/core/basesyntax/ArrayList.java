package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 16;

    // elements.length / CAPACITY_FACTOR - part of capacity that can be added or removed
    private static final int CAPACITY_FACTOR = 2;
    private Object[] elements = new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        growIfNeed(1);
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        growIfNeed(1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        growIfNeed(list.size());
        for (int i = 0; i < list.size(); i++) {
            elements[size + i] = list.get(i);
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        rangeCheck(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheck(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        final T element = (T) elements[index];
        if (size == elements.length && index == size - 1) {
            elements[size - 1] = null;
        } else {
            System.arraycopy(elements, index + 1, elements, index, size - index);
        }
        size--;
        reduceCapacityIfNeed();
        return element;
    }

    @Override
    public T remove(T value) {
        int valueIndex = indexOf(value);
        if (valueIndex == -1) {
            throw new NoSuchElementException("Can't remove element");
        }

        return remove(valueIndex);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (value == null && elements[i] == null
                    || value != null && value.equals(elements[i])) {
                return i;
            }
        }

        return -1;
    }

    private void growIfNeed(int newElementsAmount) {
        if (elements.length - size - newElementsAmount < 0) {
            elements = Arrays.copyOf(elements, elements.length + elements.length / CAPACITY_FACTOR);
        }
    }

    private void reduceCapacityIfNeed() {
        int resultSize = elements.length - elements.length / CAPACITY_FACTOR;
        if (resultSize >= size && resultSize >= DEFAULT_CAPACITY) {
            elements = Arrays.copyOf(elements, resultSize);
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Can't add element at index " + index);
        }
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
    }
}
