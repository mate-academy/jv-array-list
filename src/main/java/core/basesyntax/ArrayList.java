package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size = 0;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int userDefinedCapacity) {
        elements = (T[]) new Object[userDefinedCapacity];
    }

    @Override
    public void add(T value) {
        growArrayIfFull();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        ifIndexOutOfBounds(index, size);
        growArrayIfFull();
        if (index < size && size < elements.length) {
            System.arraycopy(elements, index, elements, index + 1, size);
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
        ifIndexOutOfBounds(index, size - 1);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        ifIndexOutOfBounds(index, size - 1);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        ifIndexOutOfBounds(index, size);
        T elementRemoved = get(index);
        if (index < size - 1) {
            System.arraycopy(elements, index + 1, elements, index, size - index);
        }
        size--;
        return elementRemoved;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || (elements[i] != null && elements[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void ifIndexOutOfBounds(int index, int upperBound) {
        if (index > upperBound || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    new IndexOutOfBoundsException().getMessage());
        }
    }

    private void growArrayIfFull() {
        if (elements.length == size) {
            T[] increasedElements = (T[]) new Object[elements.length + elements.length / 2];
            System.arraycopy(elements, 0, increasedElements, 0, size);
            elements = increasedElements;
        }
    }
}
