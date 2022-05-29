package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private T[] data = (T[]) new Object[0];
    private int size = data.length;

    private void increase() {
        final T[] buffer = (T[]) new Object[data.length];
        System.arraycopy(data, 0, buffer, 0, data.length);
        data = (T[]) new Object[data.length + 1];
        for (int j = 0; j < buffer.length; j++) {
            data[j] = buffer[j];
        }
    }

    @Override
    public void add(T value) {
        increase();
        data[data.length - 1] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > data.length) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        final T[] half1 = (T[]) new Object[index];
        final T[] half2 = (T[]) new Object[data.length - index];
        System.arraycopy(data, 0, half1, 0, index);
        System.arraycopy(data, index, half2, 0, data.length - index);
        data = (T[]) new Object[data.length + 1];
        for (int i = 0; i < half1.length; i++) {
            data[i] = half1[i];
        }
        data[index] = value;
        for (int i = 0; i < half2.length; i++) {
            data[i + index + 1] = half2[i];
        }
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
        if (index < 0 || index >= data.length) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= data.length) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= data.length) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        final T[] half1 = (T[]) new Object[index];
        final T[] half2 = (T[]) new Object[data.length - index - 1];
        final T oldValue = data[index];
        System.arraycopy(data, 0, half1, 0, index);
        System.arraycopy(data, index + 1, half2, 0, data.length - index - 1);
        data = (T[]) new Object[data.length - 1];
        if (half1.length == 0) {
            System.arraycopy(half2, 0, data, 0, half2.length);
            size--;
            return oldValue;
        }
        if (half2.length == 0) {
            System.arraycopy(half1, 0, data, 0, half2.length);
            size--;
            return oldValue;
        }
        for (int i = 0; i < half1.length; i++) {
            data[i] = half1[i];
        }
        for (int i = 0; i < half2.length; i++) {
            data[i + index] = half2[i];
        }
        size--;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        if (element == null) { // here is how i played the nullpointerexception safe)
            for (int i = 0; i < data.length; i++) {
                if (data[i] == null) {
                    remove(i);
                    return element;
                }
            }
        }
        for (int i = 0; i < data.length; i++) {
            if (element.equals(data[i])) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("No such element");
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
