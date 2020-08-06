package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[])new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == values.length) {
            extend();
        }
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index >= values.length) {
            throw new java.lang.ArrayIndexOutOfBoundsException();
        }
        if (size == values.length) {
            extend();
        }
        T[] arr = (T[])new Object[values.length];
        System.arraycopy(values, 0, arr, 0, index);
        arr[index] = value;
        System.arraycopy(values, index, arr, index + 1, size - index);
        values = arr;
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
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T[] arr = (T[])new Object[values.length];
        System.arraycopy(values, 0, arr, 0, index);
        System.arraycopy(values, index + 1, arr, index, size - 1 - index);
        T element = values[index];
        values = arr;
        size--;
        return element;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(t, values[i])) {
                T[] arr = (T[])new Object[values.length];
                System.arraycopy(values, 0, arr, 0, i);
                System.arraycopy(values, i + 1, arr, i, size - i - 1);
                values = arr;
                size--;
                return t;
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

    private int firstEmptyCell() {
        for (int i = 0; i < values.length; i++) {
            if (values[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private void extend() {
        T[] extendedValues = (T[])new Object[(int)Math.ceil(values.length * 2)];
        System.arraycopy(values, 0, extendedValues, 0, size);
        values = extendedValues;
    }

}
