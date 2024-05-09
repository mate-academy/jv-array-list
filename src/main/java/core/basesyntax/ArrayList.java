package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final double GROW_MULTIPLYER = 1.5;
    private static final int DEFAUL_SIZE = 10;
    private int size = 0;
    private int currentMaxSize;
    private T[] elements;

    public ArrayList() {
        currentMaxSize = DEFAUL_SIZE;
        this.elements = (T[]) new Object[DEFAUL_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == currentMaxSize) {
            increaseArrCapacity();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of array boundaries");
        }
        if (size == currentMaxSize) {
            increaseArrCapacity();
        }
        if (index != size) {
            T previousElem = elements[index];
            T nextElem;
            elements[index] = value;
            for (int i = index + 1; i < size + 1; i++) {
                nextElem = elements[i];
                elements[i] = previousElem;
                previousElem = nextElem;
            }
        } else {
            elements[index] = value;
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        while (size + list.size() > currentMaxSize) {
            increaseArrCapacity();
        }
        for (int i = 0; i < list.size(); i++) {
            elements[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of array boundaries");
        }
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of array boundaries");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of array boundaries");
        }
        final T res = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
        size--;
        return res;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index == -1) {
            throw new NoSuchElementException("There is no such element in this list");
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void increaseArrCapacity() {
        currentMaxSize = (int) (currentMaxSize * GROW_MULTIPLYER);
        T[] copy = (T[]) new Object[currentMaxSize];
        for (int i = 0; i < size; i++) {
            copy[i] = elements[i];
        }
        elements = copy;
    }

    private int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, elements[i])) {
                return i;
            }
        }
        return -1;
    }
}
