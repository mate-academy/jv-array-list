package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] data;
    private int size;

    public ArrayList() {
        data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == data.length) {
            Object[] newArray = new Object[data.length + data.length / 2];
            System.arraycopy(data, 0, newArray, 0, size);
            data = (T[]) newArray;
        }
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index <= size && index >= 0) {
            if (size == data.length) {
                Object[] newArray = new Object[data.length + data.length / 2];
                System.arraycopy(data, 0, newArray, 0, size);
                data = (T[]) newArray;
            }
            System.arraycopy(data, index, data, index + 1, size - index);
            data[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Wrong input index during adding value");
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
        if (index < size && index >= 0) {
            return data[index];
        }
        throw new ArrayListIndexOutOfBoundsException("Wrong input index during getting value");
    }

    @Override
    public void set(T value, int index) {
        if (index < size && index >= 0) {
            data[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Wrong input index during setting value");
        }
    }

    @Override
    public T remove(int index) {
        if (index < size && index >= 0) {
            T removedValue = data[index];
            System.arraycopy(data, index + 1, data, index, size - index - 1);
            size--;
            return removedValue;
        }
        throw new ArrayListIndexOutOfBoundsException("Wrong input index during removing element");
    }

    @Override
    public T remove(T element) {
        T removedValue = (T) "No such element";
        for (int i = 0; i < size; i++) {
            if (data[i] == element || data[i] != null && data[i].equals(element)) {
                removedValue = data[i];
                System.arraycopy(data, i + 1, data, i, size - i - 1);
            }
        }
        if (removedValue == "No such element") {
            throw new NoSuchElementException("There's no input element in the list");
        }
        size--;
        return removedValue;
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
