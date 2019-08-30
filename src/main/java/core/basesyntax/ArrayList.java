package core.basesyntax;

import java.util.Arrays;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List.</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private Object[] data;

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            data = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Wrong initial capacity: " + initialCapacity);
        }
    }

    public ArrayList() {
        data = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size + 1 == data.length) {
            data = Arrays.copyOf(data, data.length * 3 / 2);
        }
        data[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (index == size - 1) {
            add(value);
        } else {
            if (size + 1 == data.length) {
                data = Arrays.copyOf(data, data.length * 3 / 2);
            }
            size++;
            System.arraycopy(data, index, data, index + 1, size - index);
            data[index] = value;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            if (size + 1 == data.length) {
                data = Arrays.copyOf(data, data.length * 3 / 2);
            }
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        final T removedElement = (T) data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        data[size] = null;
        return removedElement;
    }

    @Override
    public T remove(T t) {
        if (t != null) {
            for (int i = 0; i < size; i++) {
                if (data[i].equals(t)) {
                    T removedElement = (T) data[i];
                    remove(i);
                    return removedElement;
                }
            }
            throw new ArrayIndexOutOfBoundsException();
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder listToString = new StringBuilder();
        listToString.append("[ ");
        for (int i = 0; i < size; i++) {
            listToString.append(data[i]);
            listToString.append(" ");
        }
        listToString.append("]");
        return listToString.toString();
    }
}
