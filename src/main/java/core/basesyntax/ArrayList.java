package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_ITEMS_NUMBER = 10;
    private static final double DEFAULT_SIZE_GROW = 1.5;

    private int size;
    private T[] values;

    public ArrayList() {
        values = (T[]) new Object[MAX_ITEMS_NUMBER];
    }

    private void checkSize() {
        if (size == values.length) {
            values = getGrownArray();
        }
    }

    private T[] getGrownArray() {
        int newCapacity = 0;
        int oldCapacity = values.length;
        newCapacity = (int) (oldCapacity * DEFAULT_SIZE_GROW);

        T[] newValues = (T[]) new Object[newCapacity];
        System.arraycopy(values, 0, newValues, 0, size);
        return newValues;
    }

    @Override
    public void add(T value) {
        checkSize();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
        checkSize();
        T[] resultCopy = (T[]) new Object[values.length];
        System.arraycopy(values, 0, resultCopy, 0, index + 1);
        resultCopy[index] = value;
        System.arraycopy(values, index, resultCopy, index + 1, size - index);
        values = resultCopy;
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
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        } else {
            return values[index];
        }

    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
        values[index] = value;

    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        } else {
            final T removeValue = (T) values[index];
            final T[] resultCopy = (T[]) new Object[values.length - 1];
            System.arraycopy(values, 0, resultCopy, 0, index);
            System.arraycopy(values, index + 1, resultCopy, index, size - (index + 1));
            values = resultCopy;
            size--;
            return removeValue;
        }
    }

    @Override
    public T remove(T element) {

        int indexElement = 0;
        for (int i = 0; i < size; i++) {
            if (values[i] == element || values[i] != null && values[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("informative message");
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
