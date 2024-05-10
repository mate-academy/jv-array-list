package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double GROW_MULTIPLYER = 1.5;
    private static final int DEFAUL_SIZE = 10;
    private int size;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[DEFAUL_SIZE];
    }

    @Override
    public void add(T value) {
        resize();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of array boundaries size = "
                    + size + " " + index);
        }
        resize();
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
        final T res = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[size - 1] = null;
        size--;
        return res;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index == -1) {
            throw new NoSuchElementException("There is no such element in this list " + element);
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

    private void resize() {
        if (elements.length == size) {
            int newCapacity = (int) (elements.length * GROW_MULTIPLYER);
            T[] newArray = (T[]) new Object[newCapacity];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    private int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? elements[i] == null : element.equals(elements[i])) {
                return i;
            }
        }
        return -1;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of array boundaries size ="
                    + size + " " + index);
        }
    }
}
