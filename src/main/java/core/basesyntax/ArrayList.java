package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private int size;
    private T[] values;

    public ArrayList() {
        values = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        increaseSize();
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndexPosition(index);
        increaseSize();
        System.arraycopy(values, index, values,
                index + 1, size - index);
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
        checkIndexPosition(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexPosition(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexPosition(index);
        T removedElement = values[index];
        deleteElementInArray(index);
        return removedElement;
    }

    @Override
    public T remove(T element) {
        T incomeElement;
        for (int i = 0; i < values.length; i++) {
            T currentElement = values[i];
            if (currentElement == element
                    || currentElement != null
                    && currentElement.equals(element)) {
                incomeElement = element;
                deleteElementInArray(i);
                return incomeElement;
            }
        }
        throw new NoSuchElementException("");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    private void checkIndexPosition(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("");
        }
    }

    private void growSize() {
        int currentLength = values.length;
        int newLength = currentLength + (currentLength >> 1);
        T[] newArray = (T[]) new Object[newLength];
        System.arraycopy(values, 0, newArray, 0, values.length);
        values = newArray;
    }

    private void deleteElementInArray(int index) {
        System.arraycopy(values, index + 1, values, index,
                values.length - index - 1);
        size--;
    }

    private void increaseSize() {
        if (size == values.length) {
            growSize();
        }
    }

}
