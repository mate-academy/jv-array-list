package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private int size;
    private int index;
    private final Object[] array1 = new Object[DEFAULT_SIZE];
    private T[] values = (T[]) array1;

    @Override
    public void add(T value) {
        if (size >= values.length) {
            values = Arrays.copyOf(values, (int) (values.length * 1.5));
        }
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: "
                    + index + ", Size: " + size);
        }
        if (size == values.length) {
            values = Arrays.copyOf(values, (int) (values.length * 1.5));
        }
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
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
        this.index = index;
        checkIndexInBounds();
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        this.index = index;
        checkIndexInBounds();
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        this.index = index;
        checkIndexInBounds();
        T oldValue = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        values[--size] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        T oldValue;
        for (int i = 0; i < values.length; i++) {
            if (element == null) {
                if (values[i] == null) {
                    remove(i);
                    return null;
                }
            } else {
                if (values[i] != null) {
                    if (values[i].equals(element)) {
                        oldValue = values[i];
                        remove(i);
                        return oldValue;
                    }
                }
            }
        }
        throw new NoSuchElementException("no such element in List");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndexInBounds() {
        if (size <= index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index: "
                    + index + ", Size: " + size);
        }
    }
}
