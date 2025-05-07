package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double RESIZE_COEFFICIENT = 1.5;
    private Object[] array;
    private int size;

    public ArrayList(int capacity) {
        if (capacity > 0) {
            array = new Object[capacity];
        } else {
            array = new Object[DEFAULT_CAPACITY];
        }
    }

    public ArrayList() {
        array = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            resizeArray(array);
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        // case when added value into the middle of array:
        if (size == array.length) {
            resizeArray(array);
        }
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
        checkIndex(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T value = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[array.length - 1] = null;
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < array.length; i++) {
            if (equalsRow(array[i], element)) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No such element " + element);
    }

    private void resizeArray(Object[] array) {
        this.array = new Object[(int) (array.length * RESIZE_COEFFICIENT)];
        System.arraycopy(array, 0, this.array, 0, array.length);
    }

    public <T> boolean equalsRow(T element1, T element2) {
        return element1 == element2 || element1 != null && element1.equals(element2);
    }

    public void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index " + index);
        }
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
