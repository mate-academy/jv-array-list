package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private int currentSize;
    private int capacity;
    private T[] values;

    ArrayList() {
        capacity = DEFAULT_CAPACITY;
        values = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        growList();
        values[currentSize++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > currentSize || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        System.arraycopy(values, index, values, index + 1, capacity - index - 1);
        values[index] = value;
        currentSize++;
        growList();
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index > currentSize - 1 || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > currentSize - 1 || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > currentSize - 1 || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T valueToReturn = values[index];
        System.arraycopy(values, index + 1, values, index, currentSize--);
        return valueToReturn;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < currentSize; i++) {
            if (t == values[i] || (t != null && t.equals(values[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    private void growList() {
        if (currentSize >= capacity) {
            int oldCapacity = capacity;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            values = Arrays.copyOf(values, newCapacity);
            capacity = newCapacity;
        }
    }
}
