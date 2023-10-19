package core.basesyntax;

import static java.lang.System.arraycopy;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH = 1.5;
    private int size;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
        extendArrayIfFull();
        if (index != size) {
            arraycopy(elements, index, elements, index + 1, size - index);
        }
        elements[index] = value;
        resizeArray(1);
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexIsInBounds(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexIsInBounds(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexIsInBounds(index);
        T element = elements[index];
        arraycopy(elements, index + 1, elements, index, size - index - 1);
        resizeArray(-1);
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || elements[i] != null && elements[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can't find element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeArray(int delta) {
        size += delta;
    }

    private void checkIndexIsInBounds(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
    }

    private void extendArrayIfFull() {
        if (elements.length == size) {
            T[] newTemp = (T[]) new Object[(int) (elements.length * GROWTH)];
            arraycopy(elements, 0, newTemp, 0, elements.length);
            elements = newTemp;
        }
    }
}
