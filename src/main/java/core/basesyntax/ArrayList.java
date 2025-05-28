package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {

    private int size = 0;
    private int capacity = 10;
    private Object[] array = new Object[capacity];

    @Override
    public void add(T value) throws ArrayListIndexOutOfBoundsException {
        if (size == capacity) {
            grow();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) throws ArrayListIndexOutOfBoundsException {
        rangeCheckForAddingLast(index);
        if (size == capacity) {
            grow();
        }

        int numMoved = size - index;
        System.arraycopy(array, index, array, index + 1, numMoved);
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
        try {
            Objects.checkIndex(index, size);
            return (T) array[index];
        } catch (IndexOutOfBoundsException e) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index: " + index + ", Capacity: " + capacity);
        }
    }

    @Override
    public void set(T value, int index) {
        try {
            Objects.checkIndex(index, size);
            array[index] = value;
        } catch (IndexOutOfBoundsException e) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index: " + index + ", Capacity: " + capacity);
        }
    }

    @Override
    public T remove(int index) {
        try {
            Objects.checkIndex(index, size);
            T value = (T) array[index];
            int numMoved = size - index - 1;
            System.arraycopy(array, index + 1, array, index, numMoved);
            size--;
            return value;
        } catch (IndexOutOfBoundsException e) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index: " + index + ", Capacity: " + capacity);
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, array[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        return "ArrayList{"
                + "size=" + size
                + ", capacity=" + capacity
                + ", array=" + Arrays.toString(array)
                + '}';
    }

    private void grow() {
        capacity = (int) (capacity * 1.5);
        array = Arrays.copyOf(array, capacity);
    }

    private void rangeCheckForAddingLast(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index: " + index + ", size: " + size);
        }
    }
}
