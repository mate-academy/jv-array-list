package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elementData = (T[]) new Object[DEFAULT_CAPACITY];
    private int size = 0;

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        try {
            if (size == elementData.length) {
                elementData = grow();
            }
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
            size++;
        } catch (IndexOutOfBoundsException e) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds[" + index + "]", e);
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index out of bounds [" + index + "]");
        }
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index out of bounds [" + index + "]");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index out of bounds [" + index + "]");
        }
        T removedValue = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("the element doesn't exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] grow() {
        T[] grewArray = (T[]) new Object[newCapacity()];
        System.arraycopy(elementData, 0, grewArray, 0, size);
        return grewArray;
    }

    private int newCapacity() {
        return size + (size >> 1);
    }

}
