package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int ARRAY_CAPACITY = 10;
    private T[] data;
    private int size;

    public ArrayList() {
        data = (T[]) new Object[ARRAY_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size >= data.length) {
            expandCapacity();
        }
        data[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (validIndex(index)) {
            System.arraycopy(data, index, data, index + 1, size - index);
            data[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        validIndex(index);
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        validIndex(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        validIndex(index);
        Object result = data[index];
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        return (T) result;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null && data[i].equals(t) || data[i] == t) {
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

    private void expandCapacity() {
        int expandedCapacity = (int) (size + (size * 1.5));
        T[] oldArr = data;
        data = (T[]) new Object[expandedCapacity];
        System.arraycopy(oldArr,0, data,0,size);
    }

    private boolean validIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return true;
    }
}


