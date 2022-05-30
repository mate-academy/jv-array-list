package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private Object[] values;

    public ArrayList() {
        values = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (size == values.length) {
            values = grow();
        }
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index < size) {
            int countElementsForCopying = size - index;
            System.arraycopy(values, index, values, index + 1, countElementsForCopying);
            values[index] = value;
            size++;
            return;
        }
        values[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        Object[] fromList = toArray(list);
        for (Object obj : fromList) {
            add((T) obj);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        values[index] = value;

    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T removedItem = (T) values[index];
        int countElementsForCopying = size - index - 1;
        System.arraycopy(values, index + 1, values, index, countElementsForCopying);
        values[--size] = null;
        return removedItem;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == values[i] || element != null && element.equals(values[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There no such element in array");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] grow() {
        int oldCapacity = values.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        Object[] grown = new Object[newCapacity];
        System.arraycopy(values, 0, grown, 0, size);
        return grown;
    }

    private Object[] toArray(List<T> list) {
        int size = list.size();
        Object[] listToArr = new Object[size];
        for (int i = 0; i < size; i++) {
            listToArr[i] = list.get(i);
        }
        return listToArr;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
