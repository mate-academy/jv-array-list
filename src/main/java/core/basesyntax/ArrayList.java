package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_CAPASITY = 10;
    private static final double ONE_AND_A_HALF = 1.5;
    private Object[] array;
    private int size;

    public ArrayList() {
        this.array = new Object[MAX_CAPASITY];
    }

    @Override
    public void add(T value) {
        if (size == array.length) {
            grow();
        }
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        if (size == array.length) {
            grow();
        }

        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }

        array[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (size + list.size() > array.length) {
            while (array.length <= size + list.size()) {
                grow();
            }
        }

        for (int i = 0; i < list.size(); i++) {
            array[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        if (index == array.length - 1 && array[size - 1] != null) {
            grow();
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        final T removedElement = (T) array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null ? array[i] == null : element.equals(array[i])) {
                final T removedElement = (T) array[i];
                for (int j = i; j < size - 1; j++) {
                    array[j] = array[j + 1];
                }
                array[size - 1] = null;
                size--;
                return removedElement;
            }
        }
        throw new NoSuchElementException("There is no such element");
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
        int newCapacity = (int) (array.length * ONE_AND_A_HALF);
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }
}
