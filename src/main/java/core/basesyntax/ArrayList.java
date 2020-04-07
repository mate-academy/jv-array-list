package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] values;
    private int size;

    public ArrayList() {
        this.values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.values = (T[]) new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.values = (T[]) new Object[0];
        } else {
            throw new IllegalArgumentException("Wrong argument : " + initialCapacity);
        }
    }

    @Override
    public void add(T value) {
        grow();
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIfIndexIsNotOutOfBound(index);
        grow();
        System.arraycopy(values, index, values, index + 1,
                size - index);
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
        checkIfIndexIsNotOutOfBound(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIfIndexIsNotOutOfBound(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIfIndexIsNotOutOfBound(index);
        T oldValue = get(index);
        decrease(index);
        return oldValue;
    }

    @Override
    public T remove(T t) {
        if (t == null) {
            for (int i = 0; i < size; i++) {
                if (values[i] == null) {
                    return remove(i);
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (t.equals(values[i])) {
                    return remove(i);
                }
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

    private void checkIfIndexIsNotOutOfBound(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index : " + index + ", size : " + size);
        }
    }

    private void grow() {
        if (size == values.length) {
            T[] temp = values;
            values = (T[]) new Object[(size * 3) / 2];
            System.arraycopy(temp, 0, values, 0, temp.length);
        }
    }

    private void decrease(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(values, index + 1, values, index,
                    numMoved);
        }
        values[--size] = null;
    }
}
