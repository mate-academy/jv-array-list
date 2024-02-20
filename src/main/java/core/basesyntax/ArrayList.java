package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double COEFFICIENT_OF_EXPANSION = 1.5;
    private int size;
    T[] values;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_SIZE];
        this.size = 0;
    }

    public ArrayList(int size) {
        values = (T[]) new Object[size];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        if (this.size == values.length) {
            grow();
        }
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        indexValidation(index);
        if (this.size == values.length) {
            grow();
        }
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            return;
        }
        if ((values.length - size) < list.size()) {
            int temp = size;
            size = values.length;
            grow();
            size = temp;
        }
        for (int i = 0; i < list.size(); i++) {
            values[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        indexValidation(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        indexValidation(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        indexValidation(index);
        T temp = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        values[size - 1] = null;
        size--;
        return temp;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (values[i].equals(element)) {
                T temp = values[i];
                System.arraycopy(values, i + 1, values, i, size - i - 1);
                values[size - 1] = null;
                size--;
                return temp;
            }
        }
        throw new NoSuchElementException("No such element.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void indexValidation(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index.");
        }
    }

    private void grow() {
        int newCapacity = (int) (this.size * COEFFICIENT_OF_EXPANSION + 1);
        T[] temp = values;
        values = (T[]) new Object[newCapacity];
        System.arraycopy(temp, 0, values, 0, this.size);
    }
}
