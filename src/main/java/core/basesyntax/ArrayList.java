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
    private T[] values;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        values[currentSize++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAddAndRemove(index);
        ensureCapacity();
        System.arraycopy(values, index, values, index + 1, currentSize - index);
        values[index] = value;
        currentSize++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexForGetAndSet(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexForGetAndSet(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexForAddAndRemove(index);
        T valueToReturn = values[index];
        int toCopy = currentSize - index - 1;
        System.arraycopy(values, index + 1, values, index, toCopy);
        values[--currentSize] = null;
        return valueToReturn;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < currentSize; i++) {
            if (t == values[i] || (t != null && t.equals(values[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element in list");
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    private void checkIndexForAddAndRemove(int index) {
        if (index > currentSize || index < 0) {
            throw new ArrayIndexOutOfBoundsException("List has no such index");
        }
    }

    private void checkIndexForGetAndSet(int index) {
        if (index >= currentSize || index < 0) {
            throw new ArrayIndexOutOfBoundsException("List has no such index");
        }
    }

    private void ensureCapacity() {
        if (currentSize >= values.length) {
            int oldCapacity = values.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            values = Arrays.copyOf(values, newCapacity);
        }
    }
}
