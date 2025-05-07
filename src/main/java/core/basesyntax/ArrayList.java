package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int DEFAULT_SIZE = 0;
    private static final int ONE = 1;
    private static final String ELEMENT_NOT_FOUND = "Element not found";
    private static final String INDEX_OUT_OF_BOUND_EXCEPTION = "Index: %d out of bound!! "
            + "Current size: %d";
    private Object[] array;
    private int size;

    public ArrayList() {
        array = new Object[DEFAULT_CAPACITY];
        size = DEFAULT_SIZE;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > array.length) {
            int newCapacity = array.length + (array.length >> 1);
            Object[] newData = new Object[newCapacity];
            System.arraycopy(array, 0, newData, 0, size);
            array = newData;
        }
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + ONE);
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        validateIndex(index);
        ensureCapacity(size + ONE);
        System.arraycopy(array, index, array, index + ONE, size - index);
        array[index] = value;
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
        validateIndex(index + ONE);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index + ONE);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        T oldValue = get(index);
        System.arraycopy(array, index + ONE, array, index, size - index - ONE);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == array[i]) || (element != null && element.equals(array[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException(ELEMENT_NOT_FOUND);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format(INDEX_OUT_OF_BOUND_EXCEPTION, index, size)
            );
        }
    }
}
