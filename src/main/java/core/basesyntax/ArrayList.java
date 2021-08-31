package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final String OUT_OF_BOUNDS_EXCEPTION = "Index out of bounds!";
    private T[] innerArray;
    private int size;

    public ArrayList() {
        innerArray = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == innerArray.length) {
            grow();
        }
        innerArray[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        if (size == innerArray.length) {
            grow();
        }
        System.arraycopy(innerArray, index, innerArray, index + 1, size - index);
        innerArray[index] = value;
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
        return innerArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        innerArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T value = innerArray[index];
        System.arraycopy(innerArray, index + 1, innerArray, index, size - index - 1);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == innerArray[i] || element != null && element.equals(innerArray[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element exist in list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    private void grow() {
        int oldCapacity = this.innerArray.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        T[] newInnerArray = (T[]) new Object[newCapacity];
        System.arraycopy(innerArray, 0, newInnerArray, 0, size);
        innerArray = newInnerArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(OUT_OF_BOUNDS_EXCEPTION);
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(OUT_OF_BOUNDS_EXCEPTION);
        }
    }
}
