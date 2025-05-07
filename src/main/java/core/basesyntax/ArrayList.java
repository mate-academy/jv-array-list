package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int CAPACITY_COEFFICIENT = 2;
    private int size;
    private T[] defaultArray;

    public ArrayList() {
        defaultArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == defaultArray.length) {
            extendCapacity();
        }
        defaultArray[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (size == index) {
            add(value);
            return;
        }
        checkIndex(index);
        if (size + 1 == defaultArray.length) {
            extendCapacity();
        }
        System.arraycopy(defaultArray, index, defaultArray, index + 1, size - index);
        defaultArray[index] = value;
        size += 1;
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
        return (T) defaultArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        defaultArray[index] = value;
    }

    @Override
    public T remove(int index) {
        T removedValue = get(index);
        System.arraycopy(defaultArray, index + 1, defaultArray, index, size - index - 1);
        size -= 1;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (defaultArray[i] == element
                    || defaultArray[i] != null && defaultArray[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no element such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void extendCapacity() {
        int newCapacity = defaultArray.length * CAPACITY_COEFFICIENT;
        T [] newElementData = (T[]) new Object[newCapacity];
        System.arraycopy(defaultArray, 0, newElementData, 0, defaultArray.length);
        defaultArray = newElementData;
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index " + index + "! "
                    + "Index must not be the negative digit");
        }
        if (index == size || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index" + index + "! "
                    + "Index value must be less than size");
        }
    }
}
