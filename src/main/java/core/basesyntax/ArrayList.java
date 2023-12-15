package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final String exceptionMessage = "Given index is out of bounds for this list, "
            + "given index: ";
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array = new Object[DEFAULT_CAPACITY];
    private int size = 0;

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        array[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throwOutOfBoundsException(index);
        }

        ensureCapacity(size + 1);

        System.arraycopy(array, index, array, index + 1, size - index);

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
        if (index < 0 || index > size - 1) {
            throwOutOfBoundsException(index);
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (validateIndex(index)) {
            throwOutOfBoundsException(index);
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (validateIndex(index)) {
            throwOutOfBoundsException(index);
        }
        final T removedValue = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size - 1] = null;
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && array[i] == null)
                    || (element != null && element.equals(array[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > array.length) {
            int newCapacity = Math.max(array.length * 2, minCapacity);
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    private void throwOutOfBoundsException(int index) {
        throw new ArrayListIndexOutOfBoundsException(exceptionMessage + index);
    }

    private boolean validateIndex(int index) {
        return index < 0 || index >= size;
    }
}
