package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

@SuppressWarnings("unchecked")

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] data;
    private int size;

    public ArrayList() {
        data = new Object[DEFAULT_CAPACITY];
    }

    private Object[] grow() {
        int newCapacity = (int) (data.length * 1.5);
        Object[] newData = new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, data.length);
        return newData;
    }

    private void validate(int index, int lowerBound, int upperBound)
            throws ArrayListIndexOutOfBoundsException {
        if (index < lowerBound || index > upperBound) {
            throw new ArrayListIndexOutOfBoundsException("Something went wrong...");
        }
    }

    @Override
    public void add(T value) {
        if (size == data.length) {
            data = grow();
        }
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) throws ArrayListIndexOutOfBoundsException {
        validate(index, 0, size + 1);
        if (size + 1 > data.length) {
            data = grow();
        }
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        validate(index, 0, size - 1);
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) throws ArrayListIndexOutOfBoundsException {
        validate(index, 0, size - 1);
        data[index] = value;
    }

    @Override
    public T remove(int index) throws ArrayListIndexOutOfBoundsException {
        validate(index, 0, size - 1);
        T removedElement = (T) data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) throws NoSuchElementException {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(data[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element");
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