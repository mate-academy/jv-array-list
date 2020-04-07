package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static int DEFAULT_CAPACITY = 10;
    private T[] data;
    private int size;

    public ArrayList() {
        data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkDataCapacity(1);
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexExists(index);
        checkDataCapacity(1);
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        checkDataCapacity(list.size());
        for (int i = 0; i < list.size(); i++) {
            data[size + i] = list.get(i);
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        checkIndexExists(index);
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexExists(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        T obj = get(index);
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        return obj;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < data.length; i++) {
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

    private void resizeData(int requiredCapacity) {
        int newCapacity = data.length;
        do {
            newCapacity += newCapacity >> 1;
        } while (newCapacity < requiredCapacity);

        T[] newData = (T[]) new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    private void checkDataCapacity(int itemsToAdd) {
        if (size + itemsToAdd > data.length) {
            resizeData(size + itemsToAdd);
        }
    }

    private void checkIndexExists(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
