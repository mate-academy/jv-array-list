package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final String INDEX_ERROR_MESSAGE = "Array list index out of bounds";
    private static final String ELEMENT_ERROR_MESSAGE = "No such element in list";
    private static final int INCREASE_FACTOR = 1;
    private int size = 0;
    private int capacity = 10;
    private Object[] listArr;

    public ArrayList() {
        listArr = new Object[capacity];
    }

    @Override
    public void add(T value) {
        extendIfNecessary();
        listArr[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_ERROR_MESSAGE);
        }
        extendIfNecessary();
        System.arraycopy(listArr, index, listArr, index + 1, size - index);
        listArr[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if ((list.size() + size) >= capacity) {
            extendCapacity();
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    private void extendIfNecessary() {
        if (size >= capacity) {
            extendCapacity();
        }
    }

    private void extendCapacity() {
        capacity = capacity + INCREASE_FACTOR + (capacity >> 1);
        Object[] tempArray = new Object[capacity];
        System.arraycopy(listArr, 0, tempArray, 0, size);
        listArr = tempArray;
    }

    private void checkRange(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_ERROR_MESSAGE);
        }
    }

    @Override
    public T get(int index) {
        checkRange(index);
        return (T) listArr[index];
    }

    @Override
    public void set(T value, int index) {
        checkRange(index);
        listArr[index] = value;
    }

    @Override
    public T remove(int index) {
        checkRange(index);
        return getRemovedElementAndRemove(index);
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && listArr[i] == null)
                    || (listArr[i] != null && listArr[i].equals(element))) {
                return getRemovedElementAndRemove(i);
            }
        }
        throw new NoSuchElementException(ELEMENT_ERROR_MESSAGE);
    }

    private T getRemovedElementAndRemove(int i) {
        final T foundElement = (T) listArr[i];
        size--;
        System.arraycopy(listArr, i + 1, listArr, i, size - i);
        return foundElement;
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
