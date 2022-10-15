package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int NEGATIVE_INDEX = -1;
    private static final int DEFAULT_SIZE = 10;
    private int size = 0;
    private Object[] values;

    public ArrayList() {
        values = new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        if (size == values.length) {
            grow();
        }
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("No element with index: " + index);
        }
        if (size == values.length) {
            grow();
        }
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
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("No element with index: " + index);
        }
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("No element with index: " + index);
        }
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("No element with index: " + index);
        }
        final T removingValue = (T) values[index];
        size--;
        System.arraycopy(values, index + 1, values, index, size - index);
        return removingValue;
    }

    @Override
    public T remove(T element) {
        int index = NEGATIVE_INDEX;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, values[i])) {
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
        Object[] draft = values;
        values = new Object[(int) (size * 1.5)];
        System.arraycopy(draft, 0, values, 0, draft.length);
    }
}
