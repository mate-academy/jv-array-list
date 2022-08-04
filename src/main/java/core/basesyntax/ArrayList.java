package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

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
        checkIndex(index > size || index < 0);
        if (index == size) {
            add(value);
        } else {
            Object[] temp = Arrays.copyOfRange(values, index, size);
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
        checkIndex(index >= size || index < 0);
        return (T) values[index];
    }

    @Override
    public void set(T value, int index) throws ArrayListIndexOutOfBoundsException {
        checkIndex(index >= size || index < 0);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index >= size || index < 0);
        T removedElement = (T) values[index];
        Object[] temp = Arrays.copyOfRange(values, index + 1, size);
        size = index;
        addArray(temp);
        return removedElement;
    }

    @Override
    public T remove(T element) throws NoSuchElementException {
        boolean isExist = false;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, values[i])) {
                remove(i);
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            throw new NoSuchElementException(MISSING_ELEMENT);
        }
        return element;
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
