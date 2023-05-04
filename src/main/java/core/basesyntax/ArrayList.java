package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_INDEX = 1.5;
    private int maxSize = DEFAULT_CAPACITY;
    private int size;
    private T[] values;

    public ArrayList() {
        values = (T[]) new Object[maxSize];
    }

    @Override
    public void add(T value) {
        checkSizeOfArray();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("No such index exception: "
                    + index + " size " + size);
        }
        checkSizeOfArray();
        if (index < size) {
            System.arraycopy(values, index, values, index + 1, size - index);
            values[index] = value;
            size++;
            return;
        }
        values[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            T value = list.get(i);
            add(value);
        }
    }

    @Override
    public T get(int index) {
        checkIndexExeption(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexExeption(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexExeption(index);
        T result = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element != null && element.equals(values[i]))
                    || element == values[i]) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No such index exception: " + element + " is absent");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkSizeOfArray() {
        if (size == maxSize) {
            grow();
        }
    }

    private void grow() {
        maxSize = (int) (maxSize * GROW_INDEX);
        T[] arrayTemporary = (T[]) new Object[maxSize];
        System.arraycopy(values, 0, arrayTemporary, 0, size);
        values = arrayTemporary;
    }

    private void checkIndexExeption(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("No such index exception: "
                    + index + " for size" + size);
        }
    }
}
