package core.basesyntax;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        isArrayListFull();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        isArrayListFull();
        System.arraycopy(values, index, values, index + 1, size - index);
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
        isIndexOutOfBounds(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexOutOfBounds(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexOutOfBounds(index);
        final T removedElement = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        values[size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (values[i] == element || values[i] != null && values[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " not found.");
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
        int newCapacity = values.length * 15 / 10;
        T[] newValues = (T[]) new Object[newCapacity];
        System.arraycopy(values, 0, newValues, 0, values.length);
        values = newValues;
    }

    private void isIndexOutOfBounds(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
    }

    private void isArrayListFull() {
        if (size == values.length) {
            grow();
        }
    }
}
