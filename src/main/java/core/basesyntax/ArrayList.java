package core.basesyntax;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] values;
    private int size;

    public ArrayList() {
        values = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void resize() {
        if (size == values.length) {
            T[] buffer = (T[]) new Object[values.length + values.length / 2];
            System.arraycopy(values, 0, buffer, 0, size);
            values = buffer;
        }
    }

    private void indexCheck(int index, int rangeMax) {
        if (index < 0 || index > rangeMax) {
            throw new ArrayListIndexOutOfBoundsException("The passed index is wrong");
        }
    }

    public int indexOf(T element) {
        int index = -1;
        for (int i = 0; i < size(); i++) {
            if (values[i] == element || values[i] != null && values[i].equals(element)) {
                index = i;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException();
        }
        return index;
    }

    @Override
    public void add(T value) {
        resize();
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        indexCheck(index, size);
        resize();
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
        indexCheck(index, size - 1);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index, size - 1);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index, size - 1);
        T element = get(index);
        System.arraycopy(values, index + 1, values, index, size - index - 1);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        System.arraycopy(values, index + 1, values, index, size - index - 1);
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
}
