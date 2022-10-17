package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int NEGATIVE_INDEX = -1;
    private static final int DEFAULT_SIZE = 10;
    private int size;
    private Object[] values;

    public ArrayList() {
        size = 0;
        values = new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        grow();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("No element with index: " + index);
        }
        grow();
        System.arraycopy(values, index, values,index + 1, size - index);
        values[index] = value;
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
        exceptionCheck(index);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        exceptionCheck(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        exceptionCheck(index);
        final T removingValue = (T) values[index];
        size--;
        System.arraycopy(values, index + 1, values, index, size - index);
        return removingValue;
    }

    @Override
    public T remove(T element) {
        int index = NEGATIVE_INDEX;
        for (int i = 0; i < size; i++) {
            if (element == values[i] || element != null && element.equals(values[i])) {
                index = i;
            }
        }
        if (index != NEGATIVE_INDEX) {
            return remove(index);
        }
        throw new NoSuchElementException("No element: " + element + " in this list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        if (size == values.length) {
            Object[] draft = values;
            values = new Object[(int) (size * 1.5)];
            System.arraycopy(draft, 0, values, 0, draft.length);
        }
    }

    private void exceptionCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("No element with index: " + index);
        }
    }
}
