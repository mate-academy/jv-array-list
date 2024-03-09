package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_RATE = 1.5;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growArray();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("This index does not exist");
        }
        growArray();
        for (int i = size; i > index; i--) {
            values[i] = values[i - 1];
        }
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
        checkIndex(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removeValue = values[index];
        for (int i = index; i < size; i++) {
            if (i == values.length - 1) {
                break;
            }
            values[i] = values[i + 1];
        }
        size--;
        return removeValue;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        if (element == null) {
            for (int i = 0; i < size - 1; i++) {
                if (element == null) {
                    index = i;
                    remove(index);
                    return null;
                }
            }
        } else if (element != null) {
            for (int i = 0; i < size - 1; i++) {
                if (element.equals(values[i])) {
                    index = i;
                    return remove(index);
                }
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("This value does not exist");
        }
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (values == null || size == 0) {
            return true;
        }
        return false;
    }

    private boolean checkIndex(int index) throws ArrayListIndexOutOfBoundsException {
        if (index != 0
                && index >= size
                || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("This index does not exist");
        }
        return true;
    }

    private void growArray() {
        if (size == values.length) {
            T[] newArrayList = (T[]) new Object [(int) (values.length * GROW_RATE)];
            System.arraycopy(values, 0, newArrayList, 0, values.length);
            values = newArrayList;
        }
    }
}
