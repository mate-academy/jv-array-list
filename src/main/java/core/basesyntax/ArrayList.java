package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_ARRAY_SIZE = 10;
    private int size;
    private T[] listsOfElements;

    public ArrayList() {
        listsOfElements = (T[]) new Object[MAX_ARRAY_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == listsOfElements.length) {
            grow();
        }
        listsOfElements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of bound");
        }
        if (size == listsOfElements.length) {
            grow();
        }
        System.arraycopy(listsOfElements, index, listsOfElements,
                 index + 1, size - index);
        listsOfElements[index] = value;
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
        return listsOfElements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        listsOfElements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldValue = listsOfElements[index];
        System.arraycopy(listsOfElements, index + 1, listsOfElements, index,
                size - index - 1);
        listsOfElements[--size ] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (listsOfElements[i] == element || (element != null
                    && element.equals(listsOfElements[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element" + element);
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
        T[] newArray = (T[]) new Object[size + (size / 2)];
        System.arraycopy(listsOfElements, 0, newArray, 0, listsOfElements.length);
        listsOfElements = newArray;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of bound");
        }
    }

}
