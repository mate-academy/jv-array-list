package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final double MULTIPLIER = 1.5;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (size >= elements.length) {
            increaseCapacity();
        }
        checkIndex(index);
        if (index != size) {
            System.arraycopy(elements, index, elements, index + 1, size - index);
        }
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        while (elements.length <= size + list.size()) {
            increaseCapacity();
            System.out.println("increasing");
        }

        T[] elementsCopy = (T[]) new Object[size + list.size()];
        System.arraycopy(elements, 0, elementsCopy, 0, size);
        System.arraycopy(((ArrayList<T>) list).toArray(), 0, elementsCopy, size, list.size());
        elements = elementsCopy;
        size += list.size();
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        if (index == size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        get(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        T item = get(index);
        System.arraycopy(elements, index + 1, elements, index, size - 1 - index);
        size--;
        return item;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], element)) {
                return remove(i);
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

    private void increaseCapacity() {
        T[] elementsCopy = (T[]) new Object[(int) (elements.length * MULTIPLIER)];
        System.arraycopy(elements, 0, elementsCopy, 0, size);
        elements = elementsCopy;
    }

    private void checkIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
    }

    public T[] toArray() {
        return elements;
    }
}
