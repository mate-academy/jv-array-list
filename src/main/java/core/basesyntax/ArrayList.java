package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_LENGTH = 10;

    private Object[] elements = new Object[DEFAULT_LENGTH];
    private int size = 0;

    @Override
    public void add(T value) {
        if (size == elements.length) {
            increseMaxSize();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {

        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index");
        }

        if (size == elements.length) {
            increseMaxSize();
        }

        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }

        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {

        int originalSize = list.size();

        for (int i = 0; i < originalSize; i++) {
            add(list.get(i));
        }

    }

    @Override
    public T get(int index) {

        if (index >= 0 && index <= size - 1) {
            return (T) elements[index];
        }
        throw new ArrayListIndexOutOfBoundsException("Invalid index");
    }

    @Override
    public void set(T value, int index) {

        if (!(index >= 0 && index <= size - 1)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }

        elements[index] = value;
    }

    @Override
    public T remove(int index) {

        if (!(index >= 0 && index <= size - 1)) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }

        final T result = (T)elements[index];

        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        size--;
        elements[size] = null;

        return result;
    }

    @Override
    public T remove(T element) {

        for (int i = 0; i < size; i++) {
            if (element == null ? elements[i] == null : element.equals(elements[i])) {
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
        if (size == 0) {
            return true;
        }
        return false;
    }

    private void increseMaxSize() {

        Object[] newArray = new Object[elements.length + (elements.length >> 1)];

        for (int i = 0; i < elements.length; i++) {
            newArray[i] = elements[i];
        }

        elements = newArray;
    }
}
