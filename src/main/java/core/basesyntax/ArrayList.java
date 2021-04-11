package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] elements = (T[]) new Object[DEFAULT_SIZE];
    private int size;

    @Override
    public void add(T value) {
        if (size == elements.length) {
            elements = grow();
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, size);
        if (size == elements.length) {
            elements = grow();
        }
        System.arraycopy(elements, index, elements,
                index + 1, size - index);
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
        checkIndex(index, size - 1);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, size - 1);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, size);
        T removedValue = elements[index];
        System.arraycopy(elements, index + 1,
                elements, index, size - index);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elements[i]
                    || element != null && element.equals(elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Such element does not exist!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index, int size) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds : " + index);
        }
    }

    private T[] grow() {
        T[] grewArray = (T[]) new Object[newSize()];
        System.arraycopy(elements, 0, grewArray,0, size);
        return grewArray;
    }

    private int newSize() {
        return (int) (size * 1.5);
    }
}
