package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public void add(T value) {
        if (size == elements.length) {
            resize();
        }
        elements[size++] = value;
    }

    private void resize() {
        int newCapacity = (int) (elements.length * 1.5);
        T[] newArray = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = elements[i];
        }
        elements = newArray;
    }

    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index: "
                    + index + ", Size: " + size);
        }
        if (size == elements.length) {
            resize();
        }
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = value;
        size++;
    }

    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    public T get(int index) {
        checkIndex(index);
        return elements[index];
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of bounds for length " + size);
        }
    }

    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    public T remove(int index) {
        checkIndex(index);
        T oldValue = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null;
        return oldValue;
    }

    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && elements[i] == null)
                    || (element != null && element.equals(elements[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element: " + element + " not found");
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
