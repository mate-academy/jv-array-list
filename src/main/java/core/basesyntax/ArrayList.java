package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int GROW = 2;
    private T[] elements;
    private int maxSize = 10;
    private int size = 0;

    public ArrayList() {
        this.elements = (T[]) new Object[maxSize];
    }

    @Override
    public void add(T value) {
        grow();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        grow();
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
        indexExist(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        indexExist(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        indexExist(index);
        T oldValue = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], element)) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("List don`t have element with value: " + element);
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
        if (size == maxSize) {
            maxSize = maxSize + maxSize / GROW;
            elements = Arrays.copyOf(elements, maxSize);
        }
    }

    private void indexExist(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Element with index " + index + " don`t exist!");
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Can`t put element to index: " + index);
        }
    }
}
