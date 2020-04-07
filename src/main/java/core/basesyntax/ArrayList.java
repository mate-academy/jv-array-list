package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static int CAPACITY = 10;
    private T[] list;
    private int size;

    public ArrayList() {
        list = (T[])new Object[CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        list[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        ensureCapacity();
        System.arraycopy(list, index, list, index + 1, size - index);
        list[index] = value;
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
        checkIndex(index);
        return list[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Object removed = list[index];
        System.arraycopy(list, index + 1, list, index, size - index - 1);
        size--;
        return (T) removed;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null && list[i].equals(t) || list[i] == t) {
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

    public void ensureCapacity() {
        if (size == list.length) {
            Object oldList = list;
            list = (T[]) new Object[(size * 3) / 2 + 1];
            System.arraycopy(oldList, 0, list, 0, size);
        }
    }

    public void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("index " + index + " out of bounds");
        }
    }
}
