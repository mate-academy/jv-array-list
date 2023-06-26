package core.basesyntax;

import exception.ArrayListIndexOutOfBoundsException;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size < elements.length) {
            elements[size] = value;
            size++;
            return;
        }
        size = getIndexFreeSell();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndexExist(index);
        if (size == elements.length) {
            growthElementsArray();
        }
        System.arraycopy(elements,index,elements,index + 1,
                elements.length - index - 1);
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
        checkIndexExist(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexExist(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexExist(index);
        final T value = elements[index];
        System.arraycopy(elements,index + 1,elements,index,elements.length - index - 1);
        size--;
        return value;
    }

    @Override
    public T remove(T element) throws NoSuchElementException {
        if (isEmpty()) {
            throw new ArrayListIndexOutOfBoundsException("Can't remove element from empty list");
        }
        int index;
        for (index = 0; index < elements.length; index++) {
            if (elements[index] == element
                    || elements[index] != null && elements[index].equals(element)) {
                return remove(index);
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

    private int getIndexFreeSell() {
        size = elements.length;
        growthElementsArray();
        return size;
    }

    private T [] growthElementsArray() {
        T[] buffer = (T[]) new Object[elements.length];
        System.arraycopy(elements,0,buffer,0,elements.length);
        elements = (T[]) new Object[elements.length + elements.length / 2];
        System.arraycopy(buffer,0,elements,0, buffer.length);
        return elements;
    }

    private void checkIndexExist(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index  "
                    + index + " is outside the list");
        }
    }
}

