package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int GROWTH_FACTOR = 2;
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private T[] elements = (T[]) new Object[DEFAULT_CAPACITY];

    @Override
    public void add(T value) {
        growArrayIfFull();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAddition(index);
        growArrayIfFull();
        System.arraycopy(elements, index, elements, index + 1, size - index);
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
        T temp = elements[index];

        if (index != size - 1) {
            System.arraycopy(elements, index + 1, elements, index, size - index);
        }
        size -= 1;
        return temp;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || elements[i] != null
                    && elements[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element present");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growArrayIfFull() {
        if (size == elements.length) {
            T[] temp = (T[]) new Object[elements.length + elements.length / GROWTH_FACTOR + 1];
            System.arraycopy(elements, 0, temp, 0, elements.length);
            elements = temp;
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: "
                    + index + ", for Size: " + size);
        }
    }

    private void checkIndexForAddition(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: "
                    + index + ", for Size: " + size + 1);
        }
    }
}
