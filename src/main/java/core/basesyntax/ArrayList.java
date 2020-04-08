package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] values;
    private int size;

    public ArrayList() {
        size = 0;
        values = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int initialCapacity) {
        size = 0;
        values = new Object[initialCapacity];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        values[size++] = value;

    }

    @Override
    public void add(T value, int index) {
        ensureCapacity();
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
        if (size < (index + 1)) {
            throw new ArrayIndexOutOfBoundsException("Invalid index");
        }

        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {

        if (size < (index + 1)) {
            throw new ArrayIndexOutOfBoundsException("Invalid index");
        }
        values[index] = value;

    }

    @Override
    public T remove(int index) {
        T removed = get(index);
        if (index == 0) {
            Object[] newValues = new Object[values.length];
            System.arraycopy(values, 1, newValues, 0, values.length - 1);
            values = newValues;
            size--;
            return removed;
        }

        if (index + 1 == size) {
            size--;
            return removed;
        }

        Object[] newValues = new Object[values.length];
        System.arraycopy(values, 0, newValues, 0, index);
        System.arraycopy(values, index + 1, newValues, index, values.length - index - 1);
        values = newValues;
        size--;

        return removed;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(t, values[i])) {
                return remove(i);
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

    private void ensureCapacity() {
        if (size == values.length) {
            Object[] newValues = new Object[(values.length * 3) / 2 + 1];
            System.arraycopy(values, 0, newValues, 0, values.length);
            values = newValues;
        }
    }

    private void extendArray(int index) {
        if (values.length < index + 1) {
            Object[] newValues = new Object[index + 1];
            System.arraycopy(values, 0, newValues, 0, values.length);
            values = newValues;
        }
    }
}
