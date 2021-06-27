package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;
    private Object[] values = new Object[INITIAL_SIZE];
    private int size = 0;

    private void resizingCheck() {
        int oldLength = values.length;
        if (size >= oldLength) {
            Object[] tempoBuffer = values;
            values = new Object[(int)(oldLength * 1.5)];
            System.arraycopy(tempoBuffer, 0, values, 0, oldLength);
        }
    }

    @Override
    public void add(T value) {
        resizingCheck();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Attempt adding element to index out "
                    + "of possible range");
        }
        resizingCheck();
        Object[] tempoBuffer = new Object[size - index];
        System.arraycopy(values, index, tempoBuffer, 0, size - index);
        values[index] = value;
        System.arraycopy(tempoBuffer, 0, values, index + 1, size - index);
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
        if (index >= 0 && index < size) {
            return (T) values[index];
        }
        throw new ArrayListIndexOutOfBoundsException("You tried to get index not within "
                + "existing range");
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            values[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("You tried to set value for index "
                    + "not within existing range");
        }
    }

    @Override
    public T remove(int index) {
        Object result;
        if (index >= 0 && index < size) {
            result = values[index];
            System.arraycopy(values, index + 1, values, index, size - index - 1);
            values[size - 1] = null;
        } else {
            throw new ArrayListIndexOutOfBoundsException("You tried to set value for index "
                    + "not within existing range");
        }
        size--;
        return (T) result;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if ((values[i] != null && values[i].equals(element))
                    || (values[i] == null && element == null)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("Required element not found");
        }
        remove(index);
        return element;
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
