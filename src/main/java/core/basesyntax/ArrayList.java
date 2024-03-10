package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            grow();
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "This index doesen't exist in arraylist: " + index);
        }
        if (size == elements.length) {
            grow();
        }
        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (size == elements.length) {
            grow();
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "This index doesen't exist in arraylist: " + index);
        }
        return elementData(index);
    }

    private T elementData(int index) {
        return (T)elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "This index doesen't exist in arraylist: " + index);
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "This index doesen't exist in arraylist: " + index);
        }
        T previosNumber = elementData(index);
        int numMove = size - index - 1;
        if (numMove > 0) {
            for (int i = index; i < size - 1; i++) {
                elements[i] = elements[i + 1];
            }
        }
        size--;
        return previosNumber;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i],element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element does not exist.");
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
        int newCapacity = elements.length + (elements.length >> 1);
        elements = Arrays.copyOf(elements, newCapacity);
    }
}
