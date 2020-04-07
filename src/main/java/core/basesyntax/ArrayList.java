package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private int newCapacity;
    private T[] list;
    private int size;

    public ArrayList() {
        newCapacity = DEFAULT_CAPACITY;
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
        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        ensureCapacity(size + 1);
        for (int i = 0; i < list.size(); i++) {
            this.list[size++] = list.get(i);
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
        if (list[index] != null) {
            final T object = list[index];
            list[index] = null;
            int tempIndex = index;
            while (tempIndex < size) {
                list[tempIndex] = list[tempIndex + 1];
                list[tempIndex + 1] = null;
                tempIndex++;
            }
            size--;
            return object;
        }
        size--;
        return null;
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
        if (size > newCapacity) {
            newCapacity = (int) (size * 1.5);
            list = Arrays.copyOf(list, newCapacity);
        }
    }
}
