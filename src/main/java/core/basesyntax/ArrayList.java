package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DAFAULT_CAPACITY = 10;
    private static final double GROW_FACTORY = 1.5;
    private int size;
    private T[] internalArray;

    public ArrayList() {
        internalArray = (T[]) new Object[DAFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growArrayIfFull();
        internalArray[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is wrong: " + index);
        }
        growArrayIfFull();
        System.arraycopy(internalArray, index, internalArray,
                index + 1, size - index);
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
        T toRemoveElement = internalArray[index];
        size--;
        System.arraycopy(internalArray, index + 1, internalArray, index, size - index);
        return toRemoveElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == internalArray[i] || internalArray[i] != null
                    && internalArray[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element" + element + " is absent in the list.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growArrayIfFull() {
        if (internalArray.length == size) {
            T[] newArray = (T[]) new Object[(int) (internalArray.length * GROW_FACTORY)];
            System.arraycopy(internalArray, 0, newArray, 0, internalArray.length);
            internalArray = newArray;
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is wrong: " + index);
        }
    }
}
