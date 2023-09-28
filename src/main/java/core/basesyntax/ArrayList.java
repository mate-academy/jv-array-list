package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void resize() {
        int newCapacity = (int) (values.length * 1.5);
        T[] newArray = (T[]) new Object[newCapacity];
        System.arraycopy(values, 0, newArray, 0, size);
        values = newArray;
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
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Can't add value to index");
        }
        if (size == values.length) {
            resize();
        }
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            return;
        }
        if (size + list.size() > values.length) {
            int newCapacity = (int) (Math.max(size + list.size(), values.length * 1.5));
            T[] newArray = (T[]) new Object[newCapacity];
            System.arraycopy(values, 0, newArray, 0, size);
            values = newArray;
        }
        for (int i = 0; i < list.size(); i++) {
            T element = list.get(i);
            add(element);
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is wrong");
        }
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is wrong");
        }
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is wrong");
        }
        T removedValue = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, values[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is not the element");
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
