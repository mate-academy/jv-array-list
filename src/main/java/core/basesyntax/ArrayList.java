package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private int currentCapacity;
    private T[] values;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
        currentCapacity = DEFAULT_CAPACITY;
    }

    @Override
    public void add(T value) {
        checkCapacity();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("This index is out of bounds.");
        }
        checkCapacity();
        System.arraycopy(values, index, values, index + 1, size - index);
        size++;
        values[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedItem = values[index];
        System.arraycopy(values,index + 1, values, index, size - index);
        size--;
        return removedItem;
    }

    @Override
    public T remove(T t) {
        if (t == null) {
            size--;
            return null;
        }
        for (int i = 0; i < size; i++) {
            if (values[i] == t || t != null && values[i].equals(t)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element in this array.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkCapacity() {
        if (size >= currentCapacity) {
            currentCapacity = size * 3 / 2 + 1;
            Object[] newValues = new Object[currentCapacity];
            System.arraycopy(values, 0, newValues, 0, size);
            values = (T[]) newValues;
        }
    }

    private void checkIndex(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException("This index is out of bounds.");
        }
    }
}
