package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;
    private int size = 0;

    private T[] data;

    {
        data = (T[]) new Object[INITIAL_SIZE];
    }

    private T[] grow() {
        double newLength = data.length * 1.5;
        T[] newArray = (T[]) new Object[(int) newLength];
        System.arraycopy(data, 0, newArray, 0, data.length);
        return newArray;
    }

    @Override
    public void add(T value) {
        if (isDataFull()) {
            data = grow();
        }
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (isDataFull()) {
            data = grow();
        }
        if (index > size || index >= data.length || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Given index is out of bounds of array");
        }
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
        size++;
        data[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int index = 0; index < list.size(); index++) {
            add(list.get(index));
        }
    }

    @Override
    public T get(int index) {
        if (!isIndexCorrect(index)) {
            throw new ArrayListIndexOutOfBoundsException("Given index is out of bounds of array");
        }
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        if (isIndexCorrect(index)) {
            data[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (isIndexCorrect(index)) {
            T elementToRemove = (T) data[index];
            System.arraycopy(data, index + 1, data, index, size - index - 1);
            size--;
            return elementToRemove;
        }
        throw new ArrayListIndexOutOfBoundsException("Given index is out of bounds of array");
    }

    @Override
    public T remove(T element) {

        for (int i = 0; i < data.length; i++) {
            if ((element == null && data[i] == element)
                    || (data[i] != null && data[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isIndexCorrect(int index) {
        if (index >= size || index > data.length || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Given index is out of bounds of array");
        }
        return true;
    }

    private boolean isDataFull() {
        if (size == data.length) {
            return true;
        }
        return false;
    }
}
