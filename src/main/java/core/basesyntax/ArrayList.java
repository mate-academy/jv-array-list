package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */

public class ArrayList<T> implements List<T> {
    private Object[] list;
    private int capacity = 10;
    private int size;

    public ArrayList() {
        this.list = new Object[capacity];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        resize();
        list[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        resize();
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
        correctIndex(index);
        return (T) list[index];
    }

    @Override
    public void set(T value, int index) {
        correctIndex(index);
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        correctIndex(index);
        T result = (T) list[index];
        System.arraycopy(list, index + 1, list, index, size - index - 1);
        size--;
        return result;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (list[i] == null || t != null && t.equals(list[i])) {
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

    private void resize() {
        if (size == capacity) {
            capacity = capacity + capacity / 2;
            Object[] newList = new Object[capacity];
            System.arraycopy(list, 0, newList, 0, size);
            list = newList;
        }
    }

    private void correctIndex(int index) {
        if (index == size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
