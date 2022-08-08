package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] values;

    private int size;

    public ArrayList() {
        this.values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        increase();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkAddIndex(index);
        if (index == size) {
            add(value);
        } else {
            increase();
            T[] valuesTemp = (T[]) new Object[size - index + 1];
            for (int i = index; i <= size; i++) {
                valuesTemp[i - index] = values[i];
            }
            values[index] = value;
            for (int i = index + 1; i <= size; i++) {
                values[i] = valuesTemp[i - index - 1];
            }
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldValue = values[index];
        for (int i = index + 1; i < size; i++) {
            values[i - 1] = values[i];
        }
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int indexTemp = -1;
        for (int i = 0; i < size; i++) {
            if (values[i] == element || values[i] != null && values[i].equals(element)) {
                indexTemp = i;
            }
        }
        if (indexTemp == -1) {
            throw new NoSuchElementException("There is no such element");
        }
        for (int i = indexTemp + 1; i < size; i++) {
            values[i - 1] = values[i];
        }
        size--;
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

    private void increase() {
        if (size >= values.length) {
            T[] valuesTemp = (T[]) new Object[size * 3 / 2];
            for (int i = 0; i < size; i++) {
                valuesTemp[i] = values[i];
            }
            values = valuesTemp;
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("To big index");
        }
    }

    private void checkAddIndex(int index) {
        if (index != 0 && (index > size || index < 0)) {
            throw new ArrayListIndexOutOfBoundsException("To big index");
        }
    }

}
