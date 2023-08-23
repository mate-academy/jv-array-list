package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] values;
    private int size;

    ArrayList() {
        values = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size >= values.length) {
            resize();
        }
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (size < index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Can't add element by this index");
        }
        if (size == values.length) {
            resize();
        }
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int j = 0; j < list.size(); j++) {
            add(list.get(j));
        }
    }

    @SuppressWarnings("unchecked")
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

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = (T) values[index];
        System.arraycopy(values, index + 1, values, index, --size - index);
        values[size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = getIndex(element);
        try {
            remove(index);
        } catch (RuntimeException e) {
            throw new NoSuchElementException(
                    "There is no such element in ArrayList, method: " + element);
        }
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        int newCapacity = (int)(values.length * 1.5);
        Object[] array = new Object[newCapacity];
        System.arraycopy(values, 0, array, 0, size);
        values = array;
    }

    private int getIndex(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if ((element == null && element == values[i])
                    || (element != null && element.equals(values[i]))) {
                index = i;
                break;
            }
        }
        return index;
    }

    private void checkIndex(int index) {
        if (size <= index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Incorrect index");
        }
    }
}
