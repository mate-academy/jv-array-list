package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int MAX_SIZE = 10;
    private int size;
    private T[] values;

    public ArrayList() {
        values = (T[]) new Object[MAX_SIZE];
    }

    @Override
    public void add(T value) {
        resize();
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0 & index <= size) {
            resize();
            System.arraycopy(values, index, values, index + 1, size - index);
            values[index] = value;
            size++;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Sorry received bad index");
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            values[size++] = list.get(i);
        }
    }

    @Override
    public T get(int index) {
        if (checkIndexIsCorrect(index)) {
            return values[index];
        } else {
            throw new ArrayListIndexOutOfBoundsException("Sorry received bad index");
        }
    }

    @Override
    public void set(T value, int index) {
        if (checkIndexIsCorrect(index)) {
            values[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Sorry this index is not exit");
        }
    }

    @Override
    public T remove(int index) {
        if (checkIndexIsCorrect(index)) {
            T objectRemoved = values[index];
            System.arraycopy(values, index + 1, values, index, size - index - 1);
            values[size--] = null;
            return objectRemoved;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Sorry this index is not exit");
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (values[i] == element || values[i] != null && values[i].equals(element)) {
                T objectRemoved = values[i];
                System.arraycopy(values, i + 1, values, i, size - i - 1);
                values[size--] = null;
                return objectRemoved;
            }
        }
        throw new NoSuchElementException("Sorry this value does not exit");
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
        values = Arrays.copyOf(values, values.length + 10);
    }

    private boolean checkIndexIsCorrect(int index) {
        return index < size & index >= 0;
    }
}
