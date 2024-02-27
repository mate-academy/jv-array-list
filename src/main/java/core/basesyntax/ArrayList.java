package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int NOT_EXISTS = -1;
    private static final int UNIT = 1;
    private static final int DEFAULT_CAPACITY = 10;
    private static final double RESIZE_CONSTANT = 1.5;
    private T[] internalArray;
    private int size;

    public ArrayList() {
        internalArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size >= internalArray.length) {
            grow();
        }
        internalArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == index) {
            add(value);
            return;
        } else if (!isIndexAcceptable(index)) {
            throw new ArrayListIndexOutOfBoundsException("Unavailable index: " + index
                    + "for adding element!!!");
        }
        size++;
        if (size == internalArray.length) {
            grow();
        }
        System.arraycopy(internalArray, index, internalArray,
                index + UNIT, size - index - UNIT);
        internalArray[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (!isIndexAcceptable(index)) {
            throw new ArrayListIndexOutOfBoundsException("Can't get, out of "
                    + "bound index: " + index);
        }
        return internalArray[index];
    }

    @Override
    public void set(T value, int index) {
        if (!isIndexAcceptable(index)) {
            throw new ArrayListIndexOutOfBoundsException("Can't set, out of bound index: "
                    + index);
        }
        internalArray[index] = value;
    }

    @Override
    public T remove(int index) {
        if (isIndexAcceptable(index)) {
            T temporary = internalArray[index];

            System.arraycopy(internalArray, index + UNIT, internalArray,
                    index, size - index - UNIT);
            size--;
            return temporary;
        }
        throw new ArrayListIndexOutOfBoundsException("Can't remove non-existent element");
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (internalArray[i] == null ? element == null : internalArray[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element: " + element);
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
        T[] temporaryArray = internalArray;
        int newCapacity = (int) (internalArray.length * RESIZE_CONSTANT);
        internalArray = (T[]) new Object[newCapacity];

        System.arraycopy(temporaryArray, 0, internalArray,
                0, temporaryArray.length);
    }

    private boolean isIndexAcceptable(int index) {
        return index < size && index > NOT_EXISTS;
    }
}
