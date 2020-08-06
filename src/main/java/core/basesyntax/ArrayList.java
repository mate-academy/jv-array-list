package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] list;
    private int size;

    public ArrayList() {
        list = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (checkCapacity()) {
            resize();
        }
        list[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (checkCapacity()) {
            resize();
        }
        System.arraycopy(list, index, list, index + 1, size - index);
        list[index] = value;
        size++;
    }

    @Override
    public void set(T value, int index) {
        checkIndexBounds(index);
        list[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexBounds(index);
        return (T) list[index];
    }

    @Override
    public T remove(int index) {
        checkIndexBounds(index);
        T removed = (T) list[index];
        trimArray(index);
        list[size] = null;
        return removed;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (t == list[i] || t != null && t.equals(list[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No Such Element!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean checkCapacity() {
        return (size == list.length);
    }

    private void resize() {
        Object[] newList = new Object[list.length + (list.length / 2)];
        System.arraycopy(list, 0, newList, 0, list.length);
        list = newList;
    }

    private void trimArray(int index) {
        System.arraycopy(list, index + 1, list, index, size - index - 1);
        size--;
    }

    private void checkIndexBounds(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Given index is out of bounds!");
        }
    }
}
