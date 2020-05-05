package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final double GROWTH_RATE = 1.5;
    private int size;
    private Object[] array;

    public ArrayList() {
        array = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size < array.length) {
            array[size] = value;
            size++;
            return;
        }
        newCapacity();
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if ((size + 1) > array.length) {
            newCapacity();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
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
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < size) {
            array[index] = value;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public T remove(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return removeShift(index);
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (compare(t, (T) array[i])) {
                return removeShift(i);
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

    private T removeShift(int index) {
        T removed = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - 1 - index);
        size--;
        return removed;
    }

    private void newCapacity() {
        Object[] newArray = new Object[(int) (array.length * GROWTH_RATE)];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    private boolean compare(T key, T arrKey) {
        return key == arrKey || (key != null && key.equals(arrKey));
    }
}
