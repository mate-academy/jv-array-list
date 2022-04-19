package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_COEFFICIENT = 1.5;
    private T[] data;
    private int size;

    public ArrayList() {
        data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        data[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index. Should be: 0 <= index <= "
                + size);
        }
        grow();
        System.arraycopy(data, index, data, (index + 1), (size - index));
        data[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new RuntimeException("Provided List is empty.");
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        indexCheck(index);
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        if (index == size) {
            data[index] = value;
            size++;
        } else {
            data[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T oldValue = data[index];
        System.arraycopy(data, index + 1, data, index, (size - index - 1));
        data[--size] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == element || (data[i] != null && data[i].equals(element))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        if (size == data.length) {
            int newLength = (int) (data.length * GROW_COEFFICIENT);
            T[] newTempArray = (T[]) new Object[newLength];
            System.arraycopy(data, 0, newTempArray, 0, size);
            data = newTempArray;
        }
    }

    private void indexCheck(int index) {
        if (index >= 0 && index < size) {
            return;
        }
        throw new ArrayListIndexOutOfBoundsException("Wrong index. Index should be: 0 <= index < "
                + size);
    }
}
