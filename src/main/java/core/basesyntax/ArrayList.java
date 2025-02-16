package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int START_LENGTH_ARRAY = 10;
    private int capacity;
    private int size;
    private Element<T>[] array;

    public ArrayList() {
        capacity = START_LENGTH_ARRAY;
        array = new Element[capacity];
    }

    @Override
    public void add(T value) {
        if (size == capacity) {
            growCapacity();
        }
        array[size] = new Element<>(value);
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "The index entered exceeds the storage size");
        }
        if (size == capacity) {
            growCapacity();
        }
        if (index == size) {
            array[index] = new Element<>(value);
        }
        for (int i = size - 1; i >= index; i--) {
            if (i == size - 1) {
                array[i + 1] = new Element<>(array[i].value);
            }
            array[i + 1].value = array[i].value;
        }
        size++;
        array[index].value = value;
    }

    @Override
    public void addAll(List<T> list) {
        int totalSize = size + list.size();
        while (totalSize >= capacity) {
            growCapacity();
        }
        for (int i = 0; i < list.size(); i++) {
            array[size] = new Element<>(list.get(i));
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "The index entered exceeds the storage size");
        }
        return array[index].value;
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "The index entered exceeds the storage size");
        }
        array[index].value = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "The index entered exceeds the storage size");
        }
        T removedValue = array[index].value;
        for (int i = index; i < size; i++) {
            array[i].value = i == size - 1 ? null : array[i + 1].value;
        }
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        T removedElement = null;
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (equality(array[i].value, element)) {
                removedElement = array[i].value;
                index = i;
                break;
            }
        }
        if (index > -1) {
            for (int i = index; i < size; i++) {
                array[i].value = i == size - 1 ? null : array[i + 1].value;
            }
            size--;
            return removedElement;
        }
        throw new NoSuchElementException("The entered element is missing");
    }

    private boolean equality(T elementOne, T elementSecond) {
        return elementOne == elementSecond
                || elementOne != null && elementOne.equals(elementSecond);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growCapacity() {
        Element[] tempArray = array;
        capacity *= 1.5;
        array = new Element[capacity];
        for (int i = 0; i < tempArray.length; i++) {
            array[i] = tempArray[i];
        }
    }

    private class Element<T> {
        private T value;

        public Element(T value) {
            this.value = value;
        }
    }
}
