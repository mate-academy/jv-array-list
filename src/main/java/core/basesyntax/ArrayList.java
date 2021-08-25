package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_SIZE = 10;
    private static final String MESSAGE = "Error! Index must be greater then 0 and less then ";
    private T[] array;
    private int size;

    public ArrayList() {
        array = (T[]) new Object[ARRAY_SIZE];
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(MESSAGE + size);
        }
    }

    private void checkSize() {
        if (size == array.length) {
            resizeArray();
        }
    }

    private void resizeArray() {
        T[] newArray = (T[]) new Object[size + (size / 2)];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    @Override
    public void add(T object) {
        checkSize();
        array[size] = object;
        size++;
    }

    @Override
    public void add(T object, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(MESSAGE + size);
        }
        checkSize();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = object;
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
        return array[index];
    }

    @Override
    public void set(T object, int index) {
        checkIndex(index);
        array[index] = object;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T object = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return object;
    }

    @Override
    public T remove(T object) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(object, array[i])) {
                remove(i);
                return object;
            }
        }
        throw new NoSuchElementException("Error! Input object wasn't found!");
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
