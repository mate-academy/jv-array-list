package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static int DEFAULT_CAPACITY = 10;
    private Object[] values;
    private int size;

    public ArrayList() {
        values = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == values.length) {
            resize();
        }
        values[size] = value;
        size++;

    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        if (size == values.length) {
            resize();
        }
        System.arraycopy(values, index, values, index + 1,size - index);
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
        checkIndex(index + 1);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index + 1);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index + 1);
        T elementToRemove = (T) values[index];
        size--;
        System.arraycopy(values,index + 1, values, index,size - index);
        return elementToRemove;
    }

    @Override
    public T remove(T element) {
        T elementToRemove;
        for (int i = 0; i < size; i++) {
            if (values[i] == element || element != null && element.equals(values[i])) {
                elementToRemove = (T) values[i];
                remove(i);
                return elementToRemove;
            }
        }
        throw new NoSuchElementException("No such element in this list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(values[i]).append(" ");
        }
        return stringBuilder.toString().trim();
    }

    private void resize() {
        Object[] oldValues = values;
        values = new Object[size + (size >> 1)];
        System.arraycopy(oldValues, 0, values, 0, size);
    }

    private void throwException() {
        throw new ArrayListIndexOutOfBoundsException("Index out of bounds for length: " + size);
    }

    private void checkIndex(int index) {
        if (index > size || index < 0) {
            throwException();
        }
    }
}
