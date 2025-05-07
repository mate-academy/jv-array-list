package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private int size;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index != size) {
            checkIndexValidity(index);
        }
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        T[] arrayForList = (T[]) new Object[size + list.size()];
        System.arraycopy(elements, 0, arrayForList, 0, size);
        for (int i = 0; i < list.size(); i++) {
            arrayForList[size + i] = list.get(i);
        }
        elements = arrayForList;
        size += list.size();
    }

    @Override
    public T get(int index) {
        checkIndexValidity(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexValidity(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexValidity(index);
        T temp = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return temp;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((elements[i] == null && element == null)
                    || (elements[i] != null && elements[i].equals(element))) {
                T temp = elements[i];
                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                size--;
                return temp;
            }
        }
        throw new NoSuchElementException("Element not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = (int) (elements.length * 1.5);
            T[] newArray = (T[]) new Object[newCapacity];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    private void checkIndexValidity(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }
}
