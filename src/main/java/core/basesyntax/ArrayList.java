package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_DATA_LENGTH = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private Object[] data;
    private int size;

    public ArrayList() {
        data = new Object[DEFAULT_DATA_LENGTH];
    }

    @Override
    public void add(T value) {
        ifGrow();
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        ifGrow();
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
    public T get(int index) {
        checkIndexExclusive(index);
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexExclusive(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexExclusive(index);
        T oldValue = (T) data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == data[i] || data[i] != null && data[i].equals(element)) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No such element exception");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] grow(Object[] data) {
        int newCapacity = (int) (data.length * GROWTH_FACTOR);
        return Arrays.copyOf(data, newCapacity);
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        }
    }

    private void checkIndexExclusive(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound");
        }
    }

    private void ifGrow() {
        if (size == data.length) {
            data = grow(data);
        }
    }

}
