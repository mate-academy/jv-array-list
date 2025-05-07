package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_CAPACITY = 10;
    private static final double INCREASE_INDEX = 1.5;
    private int size;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[ARRAY_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkSize();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index:" + index + "out of size" + size);
        }
        checkSize();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        checkSize();
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldValue = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - 1 - index);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elements[i] || element != null && element.equals(elements[i])) {
                T value = (T) elements[i];
                return remove(i);
            }
        }
        throw new NoSuchElementException("can't find element - " + element);
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
            throw new ArrayListIndexOutOfBoundsException("Index:" + index + "out of size" + size);
        }
    }

    private void checkSize() {
        if (size == elements.length) {
            grow();
        }
    }

    private T[] grow() {
        T[] newArr = (T[]) new Object[(int) (elements.length * INCREASE_INDEX)];
        newArr = Arrays.copyOf(elements, newArr.length);
        elements = newArr;
        return newArr;
    }
}
