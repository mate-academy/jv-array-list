package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_CAPACITY = 10;
    private static final float GROWTH_COEFFICIENT = 1.5f;
    private static final String NO_ELEMENT = "No such element in the list";
    private static final String INDEX_ERROR = "The index is out of list`s interval";
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
    public void add(T value, int index) throws ArrayListIndexOutOfBoundsException {
        if (size == data.length) {
            increaseCapacity();
        }
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_ERROR);
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
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        checkIfIndexInInterval(index);
        return (T) data[index];
    }

    @Override
    public T remove(int index) throws ArrayListIndexOutOfBoundsException {
        checkIfIndexInInterval(index);
        // mvn попросив зробити змінну final, чи так правильно?
        final T elementToRemove = (T) data[index];
        if (size == data.length) {
            increaseCapacity();
        }
        System.arraycopy(data, index + 1, data, index, size - index);
        size--;
        return elementToRemove;
    }

    @Override
    public T remove(T element) throws ArrayListIndexOutOfBoundsException {
        int index = 0;
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (element == data[i] || (data[i] != null && data[i].equals(element))) {
                index = i;
                count++;
                break;
            }
        }
        if (count != 0) {
            return remove(index);
        } else {
            throw new NoSuchElementException(NO_ELEMENT);
        }
    }

    @Override
    public void set(T value, int index) throws ArrayListIndexOutOfBoundsException {
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

    public void checkIfIndexInInterval(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_ERROR);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}
