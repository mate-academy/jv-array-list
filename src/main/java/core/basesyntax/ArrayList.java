package core.basesyntax;

import java.util.Arrays;

/**
 * <p>Реалізувати свій ArrayList який імплементує інтерфейс List</p>
 */
public class ArrayList<T> implements List<T> {
    private T[] elements;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 16;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            resize(size * 2);
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (size == elements.length) {
            resize(size * 2);
        }
        if (index == size) {
            elements[size++] = value;
            return;
        }
        T prevItem = elements[index];
        elements[index] = value;
        for (int i = index + 1; i <= size; i++) {
            T currentItem = elements[i];
            elements[i] = prevItem;
            prevItem = currentItem;
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
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (size == elements.length) {
            resize(size * 2);
        }
        elements[index] = value;
        if (index == size) {
            size++;
        }
    }

    @Override
    public T remove(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T item = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null;
        return item;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(t)) {
                return remove(i);
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int capacity) {
        elements = Arrays.copyOf(elements, capacity);
    }
}
