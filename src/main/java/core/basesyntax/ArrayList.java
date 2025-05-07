package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;

    private Object[] internalStorage;
    private int size;

    public ArrayList() {
        this.internalStorage = new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        checkSizeAndGrow();
        internalStorage[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkAddingIndex(index);
        checkSizeAndGrow();
        System.arraycopy(internalStorage, index,
                internalStorage, index + 1,
                size - index);
        internalStorage[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) internalStorage[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        internalStorage[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final Object oldValue = internalStorage[index];
        if (index < size - 1) {
            System.arraycopy(internalStorage, index + 1, internalStorage, index, size - 1 - index);
        }
        internalStorage[size - 1] = null;
        size--;
        return (T) oldValue;
    }

    @Override
    public T remove(T element) {
        return remove(indexOf(element));
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkSizeAndGrow() {
        if (size == internalStorage.length) {
            grow();
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Index %d is out of array bounds.", index));
        }
    }

    private void checkAddingIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Index %d is out of array bounds.", index));
        }
    }

    private int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (internalStorage[i] == element
                    || internalStorage[i] != null && internalStorage[i].equals(element)) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    private void grow() {
        int newSize = internalStorage.length + (internalStorage.length >> 1);
        Object[] newStorage = new Object[newSize];
        System.arraycopy(internalStorage, 0, newStorage, 0, internalStorage.length);
        internalStorage = newStorage;
    }
}
