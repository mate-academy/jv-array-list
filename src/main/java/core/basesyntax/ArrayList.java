package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int START_LENGTH = 10;
    private T[] data;
    private int size;

    public ArrayList() {
        data = (T[]) new Object[START_LENGTH];
    }

    @Override
    public void add(T value) {
        growIfNeeded();
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        growIfNeeded();
        if (index != size) {
            System.arraycopy(data, index, data, index + 1, size - index);
        }
        data[index] = value;
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
        checkIndexForGet(index);
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexForGet(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexForGet(index);
        T removedValue = data[index];
        if (index == size - 1) {
            data[index] = null;
        } else {
            System.arraycopy(data, index + 1, data, index, size - index);
        }
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (data[i] == element || (data[i] != null && data[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("The element to delete wasn`t found. Element: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfNeeded() {
        if (size == data.length) {
            T[] oldArray = data;
            data = (T[]) new Object[(int) (oldArray.length * 1.5) + 1];
            System.arraycopy(oldArray, 0, data, 0, oldArray.length);
        }
    }

    private void checkIndexForGet(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("No existing element with index " + index);
        }
    }

    private void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Cant add element at index: " + index);
        }
    }
}
