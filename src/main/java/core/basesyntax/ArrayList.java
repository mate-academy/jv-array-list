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
        if (size >= 0 && size < internalArray.length) {
            internalArray[size] = value;
            size++;
        } else if (size == internalArray.length) {
            grow();
            internalArray[size] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound array!");
        }
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        } else if (index >= 0 && index < size) {
            resizeIdNeeded();
            System.arraycopy(internalArray, index, internalArray, index + 1, size - index);
            internalArray[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound array!");
        }
    }

    @Override
    public void addAll(List<T> list) {
        int j = size;
        for (int i = 0; i < list.size(); i++, j++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (checkIndex(index)) {
            return internalArray[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound array!");
        }
    }

    @Override
    public void set(T value, int index) {
        if (checkIndex(index)) {
            internalArray[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound array!");
        }
    }

    @Override
    public T remove(int index) {
        T temporaryElement;
        if (checkIndex(index)) {
            temporaryElement = internalArray[index];
            System.arraycopy(internalArray, index + 1, internalArray, index, size - index - 1);
            size--;
            return temporaryElement;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bound array!");
        }
    }

    @Override
    public T remove(T element) {
        int index = searchIndex(element);
        if (index >= 0 && index < size) {
            return remove(index);
        } else {
            throw new NoSuchElementException("Index is out of bound array!");
        }
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

    private void grow() {
        int newLength = (int) (internalArray.length + DEFAULT_MULTIPLIER);
        T[] newArray = (T[]) new Object[newLength];
        System.arraycopy(internalArray, 0, newArray, 0, size);
        internalArray = newArray;
    }

    private int searchIndex(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null && internalArray[i] == null
                    || element != null && element.equals(internalArray[i])) {
                return i;
            }
        }
        throw new NoSuchElementException("Value not found!");
    }

    private void resizeIdNeeded() {
        if (internalArray.length == size) {
            T[] newArray = (T[]) new Object[internalArray.length + 1];
            System.arraycopy(internalArray, 0, newArray, 0, size);
            internalArray = newArray;
        }
    }

    private boolean checkIndex(int index) {
        if (index >= 0 && index < size) {
            return true;
        }
        return false;
    }
}


