package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] things;
    private int size;

    public ArrayList() {
        this.things = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    private void resizing() {
        T[] newArray = (T[]) new Object[(int) (things.length * 1.5)];
        System.arraycopy(things,0, newArray, 0, size);
        things = newArray;
    }

    @Override
    public void add(T value) {
        if (size == things.length) {
            resizing();
        }
        things[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == things.length) {
            resizing();
        }
        System.arraycopy(things, index, things, index + 1, size - index);
        if (index == size) {
            add(value);
        } else {
            set(value, index);
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
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Incorrect index");
        }
        return things[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Incorrect index");
        }
        things[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Incorrect index");
        }
        T result = things[index];
        System.arraycopy(things, index + 1, things, index, size - index);
        things[--size] = null;
        return result;
    }

    @Override
    public T remove(T t) {
        int index = -1;
        T value = null;
        for (int i = 0; i < size; i++) {
            if (things[i] == t || things[i].equals(t)) {
                index = i;
                value = things[i];
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("Can't find element with same value");
        }
        System.arraycopy(things, index + 1, things, index, size - index);
        size--;
        return value;
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
