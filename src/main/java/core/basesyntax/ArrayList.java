package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private Object[] values;
    private int size;

    public ArrayList() {
        values = new Object[DEFAULT_SIZE];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == values.length) {
            getNewSize();
        }
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
        if (size == values.length) {
            getNewSize();
        }
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            values[size] = list.get(i);
            size++;
            if (size == values.length) {
                getNewSize();
            }
        }

    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        values[index] = value;

    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        Object removedValue = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        return (T) removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, values[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Elemnt does not exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void getNewSize() {
        T[] newArray = (T[]) new Object[size + ((int) (size * 1.5))];
        System.arraycopy(values, 0, newArray, 0, size);
        values = newArray;
    }

    public void indexCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
    }
}
