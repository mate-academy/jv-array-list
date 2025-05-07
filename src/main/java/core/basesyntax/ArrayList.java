package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_FACTOR = 1.5;
    private T[] values;
    private int size;

    public ArrayList() {
        this.values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == values.length) {
            increaseCapacity();
        }
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (size == values.length) {
            increaseCapacity();
        }
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("This index "
                    + index + " is not in size: " + size);
        }
        T[] newList = (T[]) new Object[values.length];
        for (int i = 0; i < values.length; i++) {
            if (i < index) {
                newList[i] = values[i];
            }
            if (i == index) {
                newList[i] = value;
                newList[i + 1] = values[i];
            }
            if (i > index + 1) {
                newList[i] = values[i - 1];
            }
        }
        size++;
        fullCopyArray(newList);
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexInBounds(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexInBounds(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        return remove(get(index));
    }

    @Override
    public T remove(T element) {
        T[] newList = (T[]) new Object[values.length];
        for (int i = 0; i < size; i++) {
            if (isEquals(values[i], element)) {
                size--;
                System.arraycopy(values, i + 1, newList, i, size - i);
                fullCopyArray(newList);
                return element;
            } else {
                newList[i] = values[i];
            }
        }
        throw new NoSuchElementException("There is no such element " + element.toString());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    private void increaseCapacity() {
        T[] data = values;
        values = (T[]) new Object[(int) (size * GROW_FACTOR)];
        System.arraycopy(data, 0, values, 0, size);
    }

    private boolean isEquals(T firstKey, T secondKey) {
        return firstKey == secondKey || (firstKey != null && firstKey.equals(secondKey));
    }

    private void checkIndexInBounds(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("This index "
                    + index + " is not in size: " + size);
        }
    }

    private void fullCopyArray(T[] newList) {
        System.arraycopy(newList, 0, values, 0, size);
    }
}
