package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int CAPACITY = 10;
    private Object[] elements;
    private int count;

    public ArrayList() {
        elements = new Object[CAPACITY];
        count = 0;
    }

    @Override
    public void add(T value) {
        if (isFull()) {
            grow();
        }
        elements[count++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexToAdd(index);
        if (isFull()) {
            grow();
        }
        System.arraycopy(elements, index, elements, index + 1, size() - index);
        elements[index] = value;
        count++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexToGet(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexToGet(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexToGet(index);
        T toRemove = (T) elements[index];
        if (!isFull()) {
            System.arraycopy(elements, index + 1, elements, index, count - (index + 1));
        }
        elements[--count] = null;
        return toRemove;
    }

    @Override
    public T remove(T element) {
        return remove(getIndexByValue(element));
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return size() <= 0;
    }

    private boolean isFull() {
        return size() >= elements.length;
    }

    private void grow() {
        int newLength = size() + (size() >> 1);
        Object[] tempArr = new Object[newLength];
        System.arraycopy(elements, 0, tempArr, 0, size());
        elements = tempArr;
    }

    private void checkIndexToAdd(int index) {
        if (index > size() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private void checkIndexToGet(int index) {
        if (index >= size() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private int getIndexByValue(T value) {
        for (int i = 0; i < size(); i++) {
            T current = (T) elements[i];
            if (Objects.equals(current, value)) {
                return i;
            }
        }
        throw new NoSuchElementException("No such value in list");
    }
}
