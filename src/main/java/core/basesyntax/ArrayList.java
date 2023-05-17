package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elements;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkingListToAddition();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        checkingListToAddition();
        if (index != size) {
            System.arraycopy(elements, index, elements, index + 1, size - index);
        }
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
        if (isIndexInValidRange(index)) {
            throw new ArrayListIndexOutOfBoundsException("Can`t find element by index" + index);
        }
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (isIndexInValidRange(index)) {
            throw new ArrayListIndexOutOfBoundsException("Can`t find element by index" + index);
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (isIndexInValidRange(index)) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        Object element = elements[index];
        removeElement(index);
        return (T)element;
    }

    @Override
    public T remove(T element) {
        int index = findIndexByElement(element);
        if (index == -1) {
            throw new NoSuchElementException("Can`t find element int list");
        }
        removeElement(index);
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

    private void removeElement(int index) {
        elements[index] = null;
        if (index != size - 1) {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        }
        size--;
    }

    private int findIndexByElement(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], element)) {
                return i;
            }
        }
        return -1;
    }

    private void checkingListToAddition() {
        if (size >= elements.length) {
            elements = Arrays.copyOf(elements, size + size / 2);
        }
    }

    private boolean isIndexInValidRange(int index) {
        return index < 0 || index >= size;
    }
}
