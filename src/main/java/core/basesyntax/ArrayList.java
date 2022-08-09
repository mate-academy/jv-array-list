package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double ARRAY_MAGNIFICATION = 1.5;
    private int size;
    private T[] defaultArray;

    public ArrayList() {
        this.defaultArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkArrayPlus();
        defaultArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        checkArrayPlus();
        System.arraycopy(defaultArray, index, defaultArray, index + 1, size - index);
        defaultArray[index] = value;
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
        checkIndexGetRemove(index);
        return defaultArray[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < size && index >= 0) {
            defaultArray[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("OutOfBoundsArrayList: " + index);
        }
    }

    @Override
    public T remove(int index) {
        checkIndexGetRemove(index);
        T returnFields = defaultArray[index];
        System.arraycopy(defaultArray, index + 1, defaultArray, index, size - index - 1);
        size--;
        return returnFields;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < defaultArray.length; i++) {
            if (defaultArray[i] != null ? defaultArray[i].equals(element)
                    : defaultArray[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element in ArrayList");
    }

    private void checkIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index not valid: " + index);
        }
    }

    private void checkIndexGetRemove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index not valid: " + index);
        }
    }

    private void checkArrayPlus() {
        if (defaultArray.length == size) {
            T[] newArray = (T[]) new Object[(int) (defaultArray.length * ARRAY_MAGNIFICATION)];
            System.arraycopy(defaultArray, 0, newArray, 0, size);
            defaultArray = newArray;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
