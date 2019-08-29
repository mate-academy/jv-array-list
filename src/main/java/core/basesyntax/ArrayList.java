package core.basesyntax;

import java.util.Arrays;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int BASIC_CAPACITY = 10;
    private int capacity;
    private int size;
    private Object[] values;

    public ArrayList() {
        capacity = BASIC_CAPACITY;
        size = 0;
        values = new Object[capacity];
    }

    public  ArrayList(int capacity) {
        this.capacity = capacity;
        size = 0;
        values = new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == values.length) {
            grow();
        }
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        indexCheck(index);
        if (size - index >= 0) {
            System.arraycopy(values, index, values, index + 1, size - index);
        }
        values[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T result = (T) values[index];
        System.arraycopy(values, index + 1, values, index, this.size - 1 - index);
        this.size--;
        return result;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if ((T) values[i] == t) {
                return remove(i);
            }
        }
        return remove(-1);
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
        capacity = capacity + (capacity >> 1);
        values = Arrays.copyOf(values, capacity);
    }

    private void indexCheck(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
