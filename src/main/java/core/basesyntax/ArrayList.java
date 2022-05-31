package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;
    private Object[] data;
    private int size;

    public ArrayList() {
        this.data = new Object[INITIAL_SIZE];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        increase();
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexInBounds(index, 0, size);
        increase();
        final Object[] half1 = new Object[index];
        final Object[] half2 = new Object[size - index];
        System.arraycopy(data, 0, half1, 0, index);
        System.arraycopy(data, index, half2, 0, size - index);
        data = (T[]) new Object[data.length];
        for (int i = 0; i < half1.length; i++) {
            data[i] = half1[i];
        }
        data[index] = value;
        for (int i = 0; i < half2.length; i++) {
            data[i + index + 1] = half2[i];
        }
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
        checkIndexInBounds(index, 0, size - 1);
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexInBounds(index, 0, size - 1);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexInBounds(index, 0, size - 1);
        final Object[] half1 = new Object[index];
        final Object[] half2 = new Object[data.length - index - 1];
        final T oldValue = (T) data[index];
        System.arraycopy(data, 0, half1, 0, index);
        System.arraycopy(data, index + 1, half2, 0, data.length - index - 1);
        data = new Object[data.length - 1];
        if (half1.length == 0) {
            System.arraycopy(half2, 0, data, 0, half2.length);
            size--;
            return oldValue;
        }
        if (half2.length == 0) {
            System.arraycopy(half1, 0, data, 0, half1.length);
            size--;
            return oldValue;
        }
        for (int i = 0; i < half1.length; i++) {
            data[i] = half1[i];
        }
        for (int i = 0; i < half2.length; i++) {
            data[i + index] = half2[i];
        }
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        if (element == null) { // here is how i played the nullpointerexception safe)
            for (int i = 0; i < data.length; i++) {
                if (data[i] == null) {
                    remove(i);
                    return element;
                }
            }
        }
        for (int i = 0; i < data.length; i++) {
            if (element.equals(data[i])) {
                remove(i);
                return element;
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

    private void increase() {
        if (size == data.length) {
            final Object[] buffer = new Object[data.length];
            System.arraycopy(data, 0, buffer, 0, data.length);
            data = new Object[data.length * 3 / 2];
            for (int j = 0; j < buffer.length; j++) {
                data[j] = buffer[j];
            }
        }
    }

    private void checkIndexInBounds(int index, int lowerBound, int upperBound) {
        if (index < lowerBound || index > upperBound) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }
}
