package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static int listCapacity = 10;
    private T[] data = (T[]) new Object[listCapacity];
    private int size = 0;

    @Override
    public void add(T value) {
        if (size == data.length) {
            this.data = (T[]) grow();
        }
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        isIndexInBoundAdd(index);
        if (size == data.length) {
            this.data = (T[]) grow();
        }
        Object[] data = this.data;
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        isIndexInBoundGet(index);
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexInBoundGet(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexInBoundGet(index);
        final Object[] es = data;
        T oldValue = (T) es[index];
        fastRemove(es, index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        final Object[] es = data;
        for (int i = 0; i < size; i++) {
            if (isFulfillCondition(element, i)) {
                T oldValue = (T) es[i];
                fastRemove(es, i);
                return oldValue;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    private Object[] grow(int minCapacity) {
        int newCapacity = (int) Math.round(minCapacity * 1.5);
        Object[] growed = new Object[newCapacity];
        System.arraycopy(data, 0, growed, 0, data.length);
        return growed;
    }

    private Object[] grow() {
        return grow(size + 1);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void isIndexInBoundGet(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Nonexistent index");
        }
    }

    private void isIndexInBoundAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Nonexistent index");
        }
    }

    private boolean isFulfillCondition(T element, int i) {
        return (data[i] == null && element == null) || (data[i] != null && data[i].equals(element));
    }

    private void fastRemove(Object[] es, int i) {
        final int newSize;
        if ((newSize = size - 1) > i) {
            System.arraycopy(es, i + 1, es, i, newSize - i);
        }
        es[size = newSize] = null;
    }
}
