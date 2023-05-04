package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_ARRAY_LENGTH = 10;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[MAX_ARRAY_LENGTH];
    }

    @Override
    public void add(T value) {
        resizeArray();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        size++;
        checkIndex(index);
        resizeArray();
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        resizeArray();
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedValue = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (values[i] == element || values[i] != null && values[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " is not find");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    private void resizeArray() {
        if (size == values.length) {
            int sizeNewArray = (int) (values.length * 1.5);
            T[] newArray = (T[]) new Object[(int) (sizeNewArray)];
            System.arraycopy(values, 0, newArray, 0, size);
            values = newArray;
        }
    }

    private void checkIndex(int index) throws ArrayListIndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("This index is incorrect: " + index);
        }
    }
}
