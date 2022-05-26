package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final String MESSAGE_INDEX_OUT_OF_BOUNDS
            = "failed action to index invalid position";
    private static final String MESSAGE_NOT_SUCH_ELEMENT = "there is no such element present";
    private int capacity = 10;
    private int size = 0;
    private T[] values;

    public ArrayList() {
        values = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        growCapacityIfNeed(size);
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(MESSAGE_INDEX_OUT_OF_BOUNDS);
        }
        if (index == size) {
            add(value);
            return;
        }
        growCapacityIfNeed(size);
        T[] current = (T[]) new Object[size - index];
        System.arraycopy(values, index, current, 0, current.length);
        values[index] = value;
        System.arraycopy(current, 0, values, index + 1, current.length);
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int topBound = size + list.size();
        growCapacityIfNeed(topBound);
        for (int i = size, j = 0; i < topBound; i++, j++) {
            values[i] = list.get(j);
            size++;
        }
    }

    @Override
    public T get(int index) {
        checkIfIndexValidate(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIfIndexValidate(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIfIndexValidate(index);
        T current = values[index];
        if (index < (size - 1)) {
            System.arraycopy(values,index + 1, values, index, values.length - index - 1);
        }
        values[--size] = null;
        return current;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] != null ? values[i].equals(element) : element == null) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(MESSAGE_NOT_SUCH_ELEMENT);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growCapacityIfNeed(int calculateCapacity) {
        if (calculateCapacity >= capacity) {
            capacity = size + size / 2;
            T[] temporary = values;
            values = (T[]) new Object[capacity];
            System.arraycopy(temporary, 0, values, 0, temporary.length);
        }
    }

    private void checkIfIndexValidate(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(MESSAGE_INDEX_OUT_OF_BOUNDS);
        }
    }
}
