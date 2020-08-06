package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] list;
    private int index;
    private int size;

    public ArrayList() {
        list = new Object[DEFAULT_CAPACITY];
        size = 0;
        index = 0;
    }

    public boolean checkCapacity() {
        return (index >= list.length);
    }

    public void resize() {
        Object[] newList = new Object[list.length + (list.length / 2)];
        System.arraycopy(list, 0, newList, 0, this.index);
        list = newList;
    }

    public void trimArray(int index) {
        System.arraycopy(list, index + 1, list, index, this.index - index);
        size--;
        this.index--;
    }

    private void checkIndexBounds(int index) {
        if (0 > index || index >= this.index) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public void add(T value) {
        if (checkCapacity()) {
            resize();
        }
        list[index] = value;
        index++;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (checkCapacity()) {
            resize();
        }
        System.arraycopy(list, index, list, index + 1, this.index - index);
        list[index] = value;
        this.index++;
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
        return removed;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (t == list[i] || t != null && t.equals(list[i])) {
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
}
