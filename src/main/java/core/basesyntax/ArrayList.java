package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private Object [] data = {};

    public ArrayList() {
        data = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == data.length) {
            ensureCapacity();
        }
        data[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size "
                    + index);
        }
        if (index >= DEFAULT_CAPACITY) {
            ensureCapacity();
        }
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
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
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size "
                    + index);
        }
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size()) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
        Object removedElement = data[index];
        System.arraycopy(data, index + 1, data, index, size - index);
        size--;
        return (T) removedElement;
    }

    @Override
    public T remove(T t) {
        if (exist(t) == false) {
            throw new NoSuchElementException();
        }
        Object removedElement = null;
        for (int i = 0; i < size(); i++) {
            if (t == data[i] || (t != null && t.equals(data[i]))) {
                removedElement = (T) data[i];
                remove(i);
            }
        }
        return (T) removedElement;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        int newEnsureCapacity = data.length * 3 / 2 + 1;
        data = Arrays.copyOf(data, newEnsureCapacity);
    }

    private boolean exist(T element) {
        int count = 0;
        int exist = 0;
        for (int i = 0; i < data.length; i++) {
            if ((element == null || element == (T) data[i]) || element.equals((T) data[i])) {
                count++;
            }
        }
        return count != exist;
    }

}
