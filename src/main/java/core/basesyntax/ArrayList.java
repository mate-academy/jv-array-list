package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_LIST_SIZE = 10;
    private static final int DEFAULT_MULTIPLIER = 1;
    private int size;
    private T[] internalArray;

    public ArrayList() {
        internalArray = (T[]) new Object[DEFAULT_LIST_SIZE];
    }

    @Override
    public void add(T value) {
        resizeIfNeeded();
        internalArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        resizeIfNeeded();
        System.arraycopy(internalArray, index, internalArray, index + 1, size - index);
        internalArray[index] = value;
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
        return internalArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        internalArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T temporaryElement = internalArray[index];
        System.arraycopy(internalArray, index + 1, internalArray, index, size - index - 1);
        size--;
        return temporaryElement;
    }

    @Override
    public T remove(T element) {
        int index = searchIndex(element);
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

    private int searchIndex(T element) {
        for (int i = 0; i < size; i++) {
            if (element == internalArray[i]
                    || element != null && element.equals(internalArray[i])) {
                return i;
            }
        }
        throw new NoSuchElementException("Value not found!");
    }

    private void resizeIfNeeded() {
        if (internalArray.length == size) {
            T[] newArray = (T[]) new Object[internalArray.length + DEFAULT_MULTIPLIER];
            System.arraycopy(internalArray, 0, newArray, 0, size);
            internalArray = newArray;
        }
    }

    private boolean checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound array!");
        }
        return true;
    }
}


