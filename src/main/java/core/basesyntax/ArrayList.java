package core.basesyntax;

import java.util.Arrays;

/*
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 10;
    private int size = 0;
    private Object[] array;

    public ArrayList() {
        array = new Object[CAPACITY];
    }

    public ArrayList(int userCapacity) {
        array = new Object[userCapacity];
    }

    public void outOfCapacity(int length) {
        array = Arrays.copyOf(array, length + CAPACITY + array.length);
    }

    @Override
    public void add(T value) {
        outOfCapacity(size + 1);
        size++;
        array[size - 1] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size - 1) {
            this.add(value);
        } else {
            outOfCapacity(size + 1);
            size++;
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
        }
    }

    @Override
    public void addAll(List<T> list) {
        outOfCapacity(size + 1);
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Index out of range");
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Index out of range");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > size) {
            throw new ArrayIndexOutOfBoundsException("Index out of range");
        }
        T elem = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size--] = null;
        return elem;
    }

    @Override
    public T remove(T t) {
        if (t != null) {
            for (int i = 0; i < size; i++) {
                if (array[i].equals(t)) {
                    T elem = (T) array[i];
                    remove(i);
                    return elem;
                }
            }
            throw new ArrayIndexOutOfBoundsException("Index out of range");
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
}
