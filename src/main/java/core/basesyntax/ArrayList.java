package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {

        if (size == values.length) {
            T[] valuesExtended = values;
            values = (T[]) new Object[(int) (values.length * 1.5)];
            System.arraycopy(valuesExtended, 0, values, 0, size);
        }
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {

        size++;
        if (size == values.length) {
            T[] valuesExtended = values;
            values = (T[]) new Object[(int) (values.length * 1.5)];
            System.arraycopy(valuesExtended, 0, values, 0, size);
        }
        if (index < size && index >= 0) {
            System.arraycopy(values, index,
                    values, index + 1, size - index);
            values[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Can not add element. Index is out of bounds");
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
            return values[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Can not get element. Index is out of bounds");
        }
    }

    @Override
    public void set(T value, int index) {

        if (index < size && index >= 0) {
            values[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Can not set element. Index is out of bounds");
        }
    }

    @Override
    public T remove(int index) {
        if (index < size && index >= 0) {
            T objectRemoved = values[index];
            System.arraycopy(values, index + 1, values, index, size - index - 1);
            values[size - 1] = null;
            size--;;
            return objectRemoved;
        } else {
            throw new ArrayListIndexOutOfBoundsException("There is no such element present");
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (values[i] == element || values[i] != null
                    && values[i].equals(element)) {
                System.arraycopy(values, i + 1, values, i, size - i - 1);
                values[size - 1] = null;
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("This element doesn't exist");
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
