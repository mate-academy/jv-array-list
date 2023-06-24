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
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(" The element can't be placed "
                    + "at the position " + index);
        }
        if (index == size) {
            add(value);
            return;
        }
        if (size == elements.length) {
            growthElementsArray();
            releaseCell(index);
            elements[index] = value;
            size++;
            return;
        }
        releaseCell(index);
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
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index  "
                    + index + "is outside the list");
        }
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index  "
                    + index + "is outside the list");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("index  " + index + "is outside the list");
        }
        final T value = elements[index];
        T[] buffer = (T[]) new Object[elements.length];
        rewriteArray(elements,buffer,index,0);
        rewriteArray(buffer,elements,1,index);
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
        int newLength = elements.length + elements.length / 2;
        T[] buffer = (T[]) new Object[elements.length];
        rewriteArray(elements, buffer, 0,0);
        elements = (T[]) new Object[newLength];
        rewriteArray(buffer, elements, 0,0);
        return elements;
    }

    private void rewriteArray(T[] in, T[] out, int startIn,int startOut) {
        int j = startOut;
        int i;
        for (i = startIn; i < in.length; i++) {
            if (j < out.length) {
                out[j] = in[i];
                j++;
            }
        }
    }

    private void releaseCell(int index) {
        T[] buffer = (T[]) new Object[elements.length - index + 1];
        rewriteArray(elements,buffer,index,0);
        rewriteArray(buffer,elements,0,index + 1);
    }
}

