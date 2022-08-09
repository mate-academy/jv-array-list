package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 10;
    private Object[] row;
    private int size;

    public ArrayList() {
        row = new Object[CAPACITY];
    }

    private void checkSize() {
        if (size == row.length) {
            Object[] newRow = new Object[row.length + row.length / 2];
            System.arraycopy(row, 0,newRow, 0, size);
            row = newRow;
        }
    }

    private void indexException(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect input index: " + index);
        }
    }

    @Override
    public void add(T value) {
        checkSize();
        row[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect input index: " + index);
        }
        checkSize();
        System.arraycopy(row, index, row,index + 1, size - index);
        row[index] = value;
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
        indexException(index);
        return (T) row[index];
    }

    @Override
    public void set(T value, int index) {
        indexException(index);
        row[index] = value;
    }

    @Override
    public T remove(int index) {
        indexException(index);
        T value = (T) row[index];
        System.arraycopy(row, index + 1, row, index, size - index - 1);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        indexOf(element);
        T value = (T) row[indexOf(element)];
        remove(indexOf(element));
        return value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int indexOf(T element) {
        int i = 0;
        for ( ;i < size; i++) {
            if (row[i] == element || row[i] != null && row[i].equals(element)) {
                return i;
            }
        }
        throw new NoSuchElementException("Element " + element + " was not found!");
    }
}
