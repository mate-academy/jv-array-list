package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final float GROW_FACTOR = 1.5F;
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] values;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfNeeded(size);
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (size == 0 && index == size) {
            add(value);
            return;
        }
        checkBoundsIndex(index == size ? index - 1 : index);
        int newSize = size + 1;
        growIfNeeded(newSize);
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
        size = newSize;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkBoundsIndex(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkBoundsIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkBoundsIndex(index);
        T oldValue = get(index);
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == values[i] || element != null && element.equals(values[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element couldn't be found: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfNeeded(int size) {
        if (size >= values.length) {
            values = grow();
        }
    }

    private T[] grow() {
        int newCapacity = (int) (GROW_FACTOR * values.length);
        T[] newValues = (T[]) new Object[newCapacity];
        System.arraycopy(values, 0, newValues, 0, size);
        return newValues;
    }

    private void checkBoundsIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index = " + index
                    + " are not exist for this array with size " + size);
        }
    }
}
