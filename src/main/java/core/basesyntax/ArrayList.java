package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_FACTOR = 1.5;
    private static final String INDEX_ERROR = "The index is larger than the size of the list.";
    private static final String MISSING_ELEMENT = "The element in the list is missing.";
    private Object[] values;
    private int size;
    private int capacity;

    {
        values = new Object[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
    }

    @Override
    public void add(T value) {
        if (size == capacity) {
            increaseCapasity();
        }
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) throws ArrayListIndexOutOfBoundsException {
        checkIndex(index > size);
        if (index == size) {
            add(value);
        } else {
            Object[] temp = Arrays.copyOfRange(values, index, values.length);
            values[index] = value;
            size = index + 1;
            addArray(temp);
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        checkIndex(index >= size);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index)  throws ArrayListIndexOutOfBoundsException{
        checkIndex(index >= size);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index >= size);
        T removedElement = (T) values[index];
        Object[] temp = Arrays.copyOfRange(values, index + 1, values.length);
        size = index;
        addArray(temp);
        return removedElement;
    }

    @Override
    public T remove(T element) throws NoSuchElementException {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (element.equals(values[i])) {
                remove(i);
                return element;
            } else {
                throw new NoSuchElementException(MISSING_ELEMENT);
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void increaseCapasity() {
        capacity *= GROWTH_FACTOR;
        values = Arrays.copyOf(values, capacity);
    }

    private void checkIndex(boolean incorrectIndex) throws ArrayListIndexOutOfBoundsException {
        if (incorrectIndex) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_ERROR);
        }
    }

    private void addArray(Object[] temp) {
        for (Object element : temp) {
            add((T) element);
        }
    }
}
