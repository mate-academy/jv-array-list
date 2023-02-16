package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private Object[] values;
    private int size;

    ArrayList() {
        values = new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        values[size] = value;
        increaseSize();
    }

    @Override
    public void add(T value, int index) {
        checkSizeForAdd(index);
        Object[] temp = new Object[values.length];
        System.arraycopy(values, 0, temp, 0, values.length);
        System.arraycopy(temp, 0, values, 0, index);
        values[index] = value;
        int amountElementSecondPartOfArray = size - index;
        System.arraycopy(temp, index, values, index + 1, amountElementSecondPartOfArray);
        increaseSize();
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() > values.length - size) {
            resize((int) Math.ceil((values.length + list.size()) * 1.5));
        }
        for (int i = 0; i < list.size(); i++) {
            values[size + i] = list.get(i);
        }
        size += list.size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        checkSize(index);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        checkSize(index);
        values[index] = value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        checkSize(index);
        Object[] temp = new Object[values.length];
        final T oldValue = (T) values[index];
        System.arraycopy(values, 0, temp, 0, values.length);
        values = new Object[values.length - 1];
        System.arraycopy(temp, 0, values, 0, index);
        int amountElementAfterIndex = size - index;
        System.arraycopy(temp, index + 1, values, index, amountElementAfterIndex);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (values[i] != null && values[i].equals(element) || values[i] == element) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < size; i++) {
            string.append(values[i]);
            string.append(", ");
        }
        return string.toString();
    }

    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        Object[] temp = new Object[values.length];
        System.arraycopy(values, 0, temp, 0, values.length);
        values = (T[]) new Object[(int) (newSize)];
        System.arraycopy(temp, 0, values, 0, temp.length);
    }

    private void increaseSize() {
        if (size == values.length - 1) {
            resize((int) Math.ceil(values.length * 1.5));
        }
        size++;
    }

    private void checkSizeForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("No such element exist");
        }
    }

    private void checkSize(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("No such element exist");
        }
    }
}
