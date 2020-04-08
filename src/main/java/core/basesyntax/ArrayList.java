package core.basesyntax;

import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private T[] list;
    private int size;

    public ArrayList() {
        list = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        list[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        ensureCapacity(size + 1);
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        System.arraycopy(list, index, list, index + 1, size - index);
        System.out.println();
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
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return list[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        final T object = list[index];
        System.arraycopy(list, index + 1, list, index, size - index - 1);
        size--;
        return object;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (t == list[i]
                    || t != null && t.equals(list[i])) {
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

    public void ensureCapacity(int size) {
        if (size > list.length) {
            T[] oldList = list;
            list = (T[]) new Object[(int) (size * 1.5)];
            System.arraycopy(oldList, 0, list, 0, size - 1);
        }
    }
}
