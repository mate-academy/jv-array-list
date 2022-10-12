package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] items;
    private int size;

    public ArrayList() {
        items = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        growCheck(size);
        items[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        growCheck(size);
        System.arraycopy(items, index,
                items, index + 1,
                size - index);
        items[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        growCheck(this.size + list.size());
        int modCount = size;
        for (int i = 0; i < list.size(); i++) {
            items[modCount] = list.get(i);
            modCount++;
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        rangeCheck(index);
        return (T) items[index];
    }

    @Override
    public void set(T value, int index) {
        rangeCheck(index);
        items[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheck(index);
        T result = (T) items[index];
        if (index == size - 1) {
            items[index] = null;
        } else {
            System.arraycopy(items, index + 1,
                    items, index,
                    size - index);
        }
        size--;
        return result;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == get(i) || (element != null
                    && element.equals(get(i)))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element on this list like " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = items.length;
        int newCapacity = Math.max(minCapacity, oldCapacity << 1);
        return Arrays.copyOf(items, newCapacity);
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(String
                    .format("Index %d is not in the range of this list", index));
        }
    }

    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(String
                    .format("Index %d is not in the range of this list", index));
        }
    }

    private void growCheck(int expectedSize) {
        if (expectedSize >= items.length) {
            items = grow(expectedSize + 1);
        }
    }
}
