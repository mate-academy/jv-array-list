package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int BASIC_SIZE = 10;
    private static final double RESIZE_MULTIPLIER = 1.5;
    private T[] data;
    private int size;

    public ArrayList() {
        this.data = (T[]) new Object[BASIC_SIZE];
    }

    @Override
    public void add(T value) {
        if (data.length == size) {
            resize();
        }
        data[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index, 1);
        if (data.length == size) {
            resize();
        }
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
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
        checkIndex(index, 0);
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, 0);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index, 0);
        T element = data[index];
        System.arraycopy(data, index + 1, data, index, size - index);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == data[i] || element != null && element.equals(data[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("I'm sorry, sir, I haven't such element!");
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;

    }

    private void resize() {
        T[] data = (T[]) new Object[(int) (size * RESIZE_MULTIPLIER)];
        System.arraycopy(this.data, 0, data, 0, size);
        this.data = data;
    }

    private void checkIndex(int index, int plus) {
        if (index >= size + plus || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Sir, your index is invalid! "
                    + "I can't pass it to the method");
        }
    }
}
