package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private int capacity = 10;
    private Object[] values = new Object[capacity];
    private int size;

    public void resize() {
        Object[] objects = new Object[size * 2];
        System.arraycopy(values, 0, objects, 0, size);
        values = objects;
    }

    @Override
    public void add(T value) {
        if (size == values.length) {
            resize();
        }
        values[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        if (size == values.length) {
            resize();
        }

        if (index < 0 || index > size) {
            throw new core.basesyntax.ArrayListIndexOutOfBoundsException("");
        }
        Object[] temp = (T[]) new Object[capacity];

        System.arraycopy(values, index, values, index + 1,size);
        values[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() == 0) {
            System.out.println("The list is empty");
        }
        if (list.size() > values.length) {
            resize();
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new core.basesyntax.ArrayListIndexOutOfBoundsException("");

        }
        T result = null;
        for (int i = 0; i < values.length; i++) {
            if (i == index) {
                result = (T) values[i];
                return result;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("");
        }
        for (int i = 0; i < values.length; i++) {
            if (i == index) {
                values[i] = value;
            }
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("");
        }
        T value = (T) values[index];
        System.arraycopy(values, index + 1, values, index,size - index - 1);
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(values[i], element)) {
                T value = (T) values[i];
                remove(i);
                return value;
            }
        }
        throw new NoSuchElementException();
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
