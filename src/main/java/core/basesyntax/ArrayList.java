package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] data;
    private int size = 0;

    public ArrayList() {
        data = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        resizeArrayIfFull();
        data[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index >= size + 1) {
            throw new ArrayIndexOutOfBoundsException(index + " index out of bounds");
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
        checkAvailable(index);
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        checkAvailable(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        checkAvailable(index);
        Object result = data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        return (T) result;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (t == data[i] || t != null && t.equals(data[i])) {
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

    private void resizeArrayIfFull() {
        if (data.length == size) {
            Object[] newList = new Object[((data.length * 3) / 2 + 1)];
            System.arraycopy(data, 0, newList, 0, size);
            data = newList;
        }
    }

    private void checkAvailable(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index + " index out of bounds");
        }
    }
}
