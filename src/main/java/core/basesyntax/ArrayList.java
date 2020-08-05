package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List. Дотриматися основних вимог щодо
 * реалізації ArrayList (default capacity, newCapacity...)</p>
 */
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private int size;

    private T[] items;

    public ArrayList() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        if (index == items.length - 1) {
            items = grow();
        }
        size += 1;
        for (int i = size; i >= index + 1; i--) {
            items[i] = items[i - 1];
        }
        items[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        rangeCheck(index);
        return items[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheck(index);
        items[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        T item = items[index];
        for (int i = index; i < size - 1; i++) {
            items[i] = items[i + 1];
        }
        size -= 1;
        return item;
    }

    @Override
    public T remove(T t) {
        int indexObjectDelete = -1;
        for (int i = 0; i < items.length; i++) {
            if (t != null ? t.equals(items[i]) : items[i] == null) {
                indexObjectDelete = i;
            }
        }
        if (indexObjectDelete == -1) {
            throw new NoSuchElementException("No element with this value exists");
        }
        T item = items[indexObjectDelete];
        for (int i = indexObjectDelete; i < size - 1; i++) {
            items[i] = items[i + 1];
        }
        size -= 1;
        return item;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] grow() {
        int newCapacity = size + (size >> 1);
        return Arrays.copyOf(items, newCapacity);
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
