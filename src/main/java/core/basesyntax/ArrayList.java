package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private T[] values = (T[]) new Object[DEFAULT_CAPACITY];

    @Override
    public void add(T value) {
        resize();
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        verifyIndexInBound(index);
        resize();
        if (index == size) {
            add(value);
            return;
        }
        System.arraycopy(values, index, values, index + 1, size - index);
        values[index] = value;
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
        verifyIndexInBoundOrEmpty(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        verifyIndexInBoundOrEmpty(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        verifyIndexInBoundOrEmpty(index);
        T removedValue = values[index];
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element != null && element.equals(values[i])
                    || (element == null && values[i] == null)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no element in collection: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        if (size == values.length) {
            T[] copy = values.clone();
            double capacityMultiplier = 1.5;
            values = (T[]) new Object[(int) (values.length * capacityMultiplier)];
            System.arraycopy(copy, 0, values, 0, copy.length);
        }
    }

    private void verifyIndexInBound(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("incorrect index: " + index + " for size " + size);
        }
    }

    private void verifyIndexInBoundOrEmpty(int index) {
        if (isEmpty() || (index < 0 || index >= size)) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index: " + index);
        }
    }
}
