package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int BASIC_CAPACITY = 10;
    private int size;

    private T[] elementData = (T[]) new Object[BASIC_CAPACITY];

    @Override
    public void add(T value) {
        if (size < elementData.length) {
            elementData[size] = value;
            size++;
        } else {
            elementData = Arrays.copyOf(elementData, newSize(elementData.length));
            elementData[size] = value;
            size++;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of ArrayList length");
        }

        if (size == elementData.length) {
            elementData = Arrays.copyOf(elementData, newSize(elementData.length));
        }

        if (index == size) {
            elementData[size] = value;
        } else {
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list != null && !list.isEmpty()) {

            if (elementData.length < size + list.size()) {
                elementData = Arrays.copyOf(elementData, size + list.size());
            }

            for (int i = 0; i < list.size(); i++) {
                T element = list.get(i);
                elementData[size++] = element;
            }
        }
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            return elementData[index];
        }
        throw new ArrayListIndexOutOfBoundsException("Index is out of ArrayList length");
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of ArrayList length");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of ArrayList length");
        }
        T removedElement = elementData[index];

        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);

        elementData[--size] = null;

        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && elementData[i] == null)
                    || (element != null && element.equals(elementData[i]))) {
                T removedElement = elementData[i];
                System.arraycopy(elementData, i + 1, elementData, i, size - i - 1);
                elementData[--size] = null;

                return removedElement;
            }
        }
        throw new NoSuchElementException("Can't find the element in ArrayList");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public int newSize(int size) {
        int newSize = (int) (size * 1.5);
        return newSize > size ? newSize : size + 1;
    }
}
