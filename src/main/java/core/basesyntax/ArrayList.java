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

    private void ensureCapacity(int amount) {
        if (data.length <= amount) {
            data = Arrays.copyOf(data, data.length + amount + DEFAULT_CAPACITY);
        }
    }

    private void arrayIndexOutOfBoundsException(int index) {
        throw new ArrayIndexOutOfBoundsException("The index is out of range: "
                + index + ", the size is: " + this.size());
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        size++;
        data[size - 1] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            arrayIndexOutOfBoundsException(index);
        }
        if (index == size - 1) {
            this.add(value);
        } else {
            ensureCapacity(size + 1);
            size++;
            System.arraycopy(data, index, data, index + 1, size - index);
            data[index] = value;
        }
    }

    @Override
    public void addAll(List<T> list) {
        ensureCapacity(size + 1);
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            arrayIndexOutOfBoundsException(index);
        }
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            arrayIndexOutOfBoundsException(index);
        }
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size) {
            arrayIndexOutOfBoundsException(index);
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
                    this.remove(i);
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
