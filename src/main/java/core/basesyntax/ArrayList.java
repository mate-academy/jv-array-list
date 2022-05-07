package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROWTH_COEFFICIENT = 1.5;
    private Object[] elemets;
    private int size;

    public ArrayList() {
        elemets = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elemets.length) {
            resize();
        }
        elemets[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index != 0) {
            isInRange(index - 1);
        }
        if (size == elemets.length) {
            resize();
        }
        System.arraycopy(elemets, index, elemets, index + 1, size - index);
        elemets[index] = value;
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
        isInRange(index);
        return (T) elemets[index];
    }

    @Override
    public void set(T value, int index) {
        isInRange(index);
        elemets[index] = value;
    }

    @Override
    public T remove(int index) {
        isInRange(index);
        T element = (T) elemets[index];
        System.arraycopy(elemets, index + 1, elemets, index, size - index - 1);
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elemets[i], element)) {
                System.arraycopy(elemets, i + 1, elemets, i, size - i - 1);
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("There is no such element " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void isInRange(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index " + index);
        }
    }

    private void resize() {
        int capacity = (int) (elemets.length * GROWTH_COEFFICIENT);
        Object[] objects = new Object[capacity];
        System.arraycopy(elemets, 0, objects, 0, elemets.length);
        elemets = objects;
    }
}
