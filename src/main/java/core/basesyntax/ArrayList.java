package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_START_SIZE = 10;
    private static final double COEFFICIENT = 1.5;
    private Object[] list;
    private int size;

    public ArrayList() {
        list = new Object[ARRAY_START_SIZE];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        list[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range");
        }
        if (index == size()) {
            add(value);
            return;
        }
        growIfArrayFull();
        Object[] newList = new Object[list.length];
        System.arraycopy(list, 0, newList, 0, index);
        newList[index] = value;
        System.arraycopy(list, index, newList, index + 1, size() - index);
        list = newList;
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
        checkIndexIsValid(index);
        return (T) list[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexIsValid(index);
        list[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexIsValid(index);
        final T oldElement = (T) list[index];
        System.arraycopy(list, index + 1, list, index, size() - 1 - index);
        size--;
        list[size] = null;
        return oldElement;
    }

    @Override
    public T remove(T element) {
        for (int index = 0; index < size; index++) {
            if (Objects.equals(element, list[index])) {
                return remove(index);
            }
        }
        throw new NoSuchElementException("No such element " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndexIsValid(int index) {
        if (index >= size() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index out of range");
        }
    }

    private void growIfArrayFull() {
        if (list.length <= size) {
            int newSize = (int) (list.length * COEFFICIENT);
            Object[] temp = new Object[newSize];
            System.arraycopy(list, 0, temp, 0, size);
            list = temp;
        }
    }
}
