package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int NATURAL_NUMBER_BORDERLINE = 0;
    private static final int DEFAULT_CAPACITY = 10;
    private static final double CAPACITY_MULTIPLY_BY = 1.5;
    private static final String NEGATIVE_INDEX_ERROR = "Index should be natural number";
    private static final String INDEX_OUT_OF_SIZE_ERROR = "Index out of list length";

    private T[] data;
    private int size;

    public ArrayList() {
        data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity > 0) {
            data = (T[]) new Object[capacity];
        } else if (capacity == 0) {
            data = (T[]) new Object[]{};
        } else {
            throw new ArrayListIndexOutOfBoundsException(NEGATIVE_INDEX_ERROR);
        }
    }

    @Override
    public void add(T value) {
        if (size < data.length) {
            data[size] = value;
            size++;
        } else {
            this.ensureCapacity();
            this.add(value);
        }
    }

    @Override
    public void add(T value, int index) {
        isIndexValid(index);
        if (size < data.length) {
            System.arraycopy(data, index, data, index + 1, size - index);
            data[index] = value;
            size++;
        } else {
            this.ensureCapacity();
            this.add(value, index);
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (size + list.size() > data.length) {
            this.ensureCapacity();
            this.addAll(list);
        }
        for (int i = size; i < (size + list.size()); i++) {
            data[i] = list.get(i - size);
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        isIndexValid(index + 1);
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexValid(index + 1);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexValid(index + 1);
        final T result = data[index];
        if (index == size - 1) {
            data[index] = null;
        } else {
            System.arraycopy(data, index + 1, data, index, size - index);
            data[size - 1] = null;
        }
        size--;
        return result;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if ((data[i] != null && data[i].equals(element))
                    || (data[i] == null && element == null)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException(INDEX_OUT_OF_SIZE_ERROR);
        }
        return this.remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    private void ensureCapacity() {
        T[] newData = (T[]) new Object[(int)(size * CAPACITY_MULTIPLY_BY)];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    private void isIndexValid(int index) {
        if (index < NATURAL_NUMBER_BORDERLINE) {
            throw new ArrayListIndexOutOfBoundsException(NEGATIVE_INDEX_ERROR);
        }
        if (index > size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_OUT_OF_SIZE_ERROR);
        }
    }

}
