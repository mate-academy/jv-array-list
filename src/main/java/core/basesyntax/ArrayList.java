package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] innerArray = (T[]) new Object[DEFAULT_CAPACITY];
    private int size = 0;

    @Override
    public void add(T value) {
        if (size == innerArray.length) {
            grow();
        }
        innerArray[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0 && index <= size) {
            if (size + 1 > innerArray.length) {
                grow();
            }
            T[] arr = (T[]) new Object[size - index];
            if (size > index) {
                System.arraycopy(innerArray, index, arr, 0, size - index);
            }
            innerArray[index] = value;
            System.arraycopy(arr, 0, innerArray, index + 1, size - index);
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }

    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            return innerArray[index];
        }
        throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            innerArray[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public T remove(int index) {
        if (index >= 0 && index < size) {
            T item = innerArray[index];
            System.arraycopy(innerArray, index + 1, innerArray, index, size - index - 1);
            size--;
            return item;
        }
        throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    @Override
    public T remove(T element) {
        int index = findIndex(element);
        if (index >= 0) {
            return remove(index);
        } else {
            throw new NoSuchElementException("Element: " + element + " not found");
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

    private void grow() {
        T[] arr = (T[]) new Object[innerArray.length * 2];
        System.arraycopy(innerArray, 0, arr, 0, innerArray.length);
        innerArray = arr;
    }

    private int findIndex(T item) {
        if (item == null) {
            for (int i = 0; i < size; i++) {
                if (innerArray[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (item.equals(innerArray[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
}
