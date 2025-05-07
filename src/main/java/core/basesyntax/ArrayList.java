package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static int DEFAULT_CAPACITY = 10;
    private static final String INDEX_OF_BOUND_MSG = "Index can`t be bigger than size of the array";
    private Object[] array = new Object[DEFAULT_CAPACITY];
    private int size = 0;

    @Override
    public void add(T value) {
        growIfFull();
        array[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        isIndexInBoundsForAdd(index);
        growIfFull();
        System.arraycopy(array, index, array, index + 1, size - index);
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
        isIndexInBounds(index);
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        isIndexInBounds(index);
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        isIndexInBounds(index);
        T oldValue = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no such element: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void isIndexInBounds(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_OF_BOUND_MSG);
        }
    }

    private void isIndexInBoundsForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_OF_BOUND_MSG);
        }
    }

    private void growIfFull() {
        if (size == DEFAULT_CAPACITY) {
            DEFAULT_CAPACITY = DEFAULT_CAPACITY + (DEFAULT_CAPACITY >> 1);
            array = Arrays.copyOf(array, DEFAULT_CAPACITY);
        }
    }
}
