package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int counter;
    private T[] values = (T[]) new Object[DEFAULT_CAPACITY];

    @Override
    public void add(T value) {
        checkExtention();
        values[counter++] = value;
    }

    @Override
    public void add(T value, int index) {
        verifyIndexInBound(index);
        checkExtention();
        if (index == counter) {
            add(value);
            return;
        }
        System.arraycopy(values, index, values, index + 1, counter - index);
        values[index] = value;
        counter++;
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
        System.arraycopy(values, index + 1, values, index, counter - index - 1);
        counter--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < counter; i++) {
            if (element != null && element.equals(values[i])
                    || (element == null && values[i] == null)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public boolean isEmpty() {
        return counter == 0;
    }

    private void checkExtention() {
        if (counter == values.length) {
            T[] copy = values.clone();
            double capacityMultiplier = 1.5;
            values = (T[]) new Object[(int) (values.length * capacityMultiplier)];
            System.arraycopy(copy, 0, values, 0, copy.length);
        }
    }

    private void verifyIndexInBound(int index) {
        if (index < 0 || index > counter) {
            throw new ArrayListIndexOutOfBoundsException("incorrect index");
        }
    }

    private void verifyIndexInBoundOrEmpty(int index) {
        if (isEmpty() || (index < 0 || index >= counter)) {
            throw new ArrayListIndexOutOfBoundsException("Incorrect index: " + index);
        }
    }
}
