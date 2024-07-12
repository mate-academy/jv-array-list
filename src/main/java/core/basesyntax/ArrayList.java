package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static int CAPACITY = 10;
    private static double SIZE_OF_INCREASE = 1.5;
    private T[] values;
    private int size;

    public ArrayList() {

        values = (T[]) new Object[CAPACITY];
    }

    private void increaseCapacity() {
        if (values.length == size) {
            int newCapacity = (int)(values.length * SIZE_OF_INCREASE);
            values = Arrays.copyOf(values, newCapacity);
        }
    }

    private void correctIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public void add(T value) {
        increaseCapacity();
        values[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        increaseCapacity();
        for (int i = size; i > index; i--) {
            values[i] = values[i - 1];
        }
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
        correctIndex(index);
        return values[index];
    }

    @Override
    public void set(T value, int index) {
        correctIndex(index);
        values[index] = value;
    }

    @Override
    public T remove(int index) {
        increaseCapacity();
        correctIndex(index);
        T value = values[index];
        for (int i = index; i < size; i++) {
            values[i] = values[i + 1];
        }
        values[--size] = null;
        return value;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        increaseCapacity();
        if (index == -1) {
            throw new NoSuchElementException("Element not found");
        }
        return remove(index);
    }

    private int indexOf(T element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (values[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(values[i])) {
                    return i;
                }
            }
        }
        return -1;
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
