package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_SIZE = 10;
    private static final int EMPTY_SIZE = 0;
    private static final int CAPACITY_DIVISOR = 2;
    private T[] dataElement;
    private int size;

    public ArrayList() {
        dataElement = (T[]) new Object[INITIAL_SIZE];
    }

    @Override
    public void add(T value) {
        grow();
        dataElement[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Can't add to index " + index);
        }
        grow();
        System.arraycopy(dataElement, index, dataElement, index + 1, size++ - index);
        dataElement[index] = value;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (checkIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Can't get from index " + index);
        }
        return dataElement[index];
    }

    @Override
    public void set(T value, int index) {
        if (checkIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Can't set to index " + index);
        }
        dataElement[index] = value;
    }

    @Override
    public T remove(int index) {
        if (checkIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Can't remove from index "
                    + index);
        }
        final T element = dataElement[index];
        System.arraycopy(dataElement, index + 1, dataElement, index, size-- - index - 1);
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == dataElement[i] || areEqual(element,dataElement[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Not found element in the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == EMPTY_SIZE;
    }

    private void grow() {
        if (size == dataElement.length) {
            int oldCapacity = dataElement.length;
            int newCapacity = oldCapacity + (oldCapacity / CAPACITY_DIVISOR);
            if (newCapacity < 0) {
                newCapacity = Integer.MAX_VALUE;
            }
            dataElement = Arrays.copyOf(dataElement, newCapacity);
        }
    }

    private boolean checkIndex(int index) {
        return index < 0 || index >= size;
    }

    private boolean areEqual(T a, T b) {
        return (a != null && b != null && a.hashCode() == b.hashCode());
    }

}
