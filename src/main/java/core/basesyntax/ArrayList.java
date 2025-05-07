package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_CAPACITY = 10;
    private static final float GROWTH_COEFFICIENT = 1.5f;
    private static final String NO_ELEMENT_MESSAGE = "No such element in the list";
    private static final String INDEX_ERROR_MESSAGE = "The index is out of list`s interval ";
    private Object[] data;
    private int size;

    public ArrayList() {
        data = new Object[MAX_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == data.length) {
            increaseCapacity();
        }
        data[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (size == data.length) {
            increaseCapacity();
        }
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_ERROR_MESSAGE + index);
        }
        System.arraycopy(data, index, data, index + 1, size - index);
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
        checkIfIndexInInterval(index);
        return (T) data[index];
    }

    @Override
    public T remove(int index) {
        checkIfIndexInInterval(index);
        T elementToRemove = (T) data[index];
        System.arraycopy(data, index + 1, data, index, size - (index + 1));
        size--;
        return elementToRemove;
    }

    @Override
    public T remove(T element) {
        int index = getIndex(element);
        return remove(index);
    }

    @Override
    public void set(T value, int index) {
        checkIfIndexInInterval(index);
        data[index] = value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void increaseCapacity() {
        Object[] temp = new Object[(int) (size * GROWTH_COEFFICIENT)];
        System.arraycopy(data, 0, temp, 0, size);
        data = temp;
    }

    private int getIndex(T element) {
        for (int i = 0; i < size; i++) {
            if (element == data[i] || (data[i] != null && data[i].equals(element))) {
                return i;
            }
        }
        throw new NoSuchElementException(NO_ELEMENT_MESSAGE);
    }

    private void checkIfIndexInInterval(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_ERROR_MESSAGE + index);
        }
    }
}
