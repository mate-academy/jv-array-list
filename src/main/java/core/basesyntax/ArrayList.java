package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final String BOUNDS_EXCEPTION = "Out of bounds for index ";
    private static final String NO_SUCH_ELEMENT_EXCEPTION = "No such element ";
    private int size;
    private T[] values;

    @SuppressWarnings("unchecked")
    ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growCheck();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(BOUNDS_EXCEPTION + index);
        }
        growCheck();
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
        size++;
    }

    private void growCheck() {
        if (size == values.length) {
            grow();
        }
    }

    @SuppressWarnings("unchecked")
    private void grow() {
        int newCapacity = values.length + (values.length >> 1);
        T[] newValues = (T[]) new Object[newCapacity];
        System.arraycopy(values, 0, newValues, 0, values.length);
        values = newValues;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        outOfBoundsCheck(index);
        return values[index];
    }

    private void outOfBoundsCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(BOUNDS_EXCEPTION + index);
        }
    }

    @Override
    public void set(T value, int index) {
        outOfBoundsCheck(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        outOfBoundsCheck(index);
        T returnValue = values[index];
        System.arraycopy(values, index + 1, values, index, size - 1 - index);
        size--;
        return returnValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == null ? values[i] == element : values[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(NO_SUCH_ELEMENT_EXCEPTION + element);
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
