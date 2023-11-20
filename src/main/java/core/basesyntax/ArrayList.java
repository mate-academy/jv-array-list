package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double GROW_FACTOR = 1.5;
    private T[] data;
    private int size;

    public ArrayList() {
        data = (T[]) new Object[size];
    }

    @Override
    public void add(T value) {
        changeCapacity();
        data[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("You must indicate"
                    + " the index from 0 till " + (size - 1)
                    + " but was " + index);
        }
        changeCapacity();
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
        checkIndex(index);
        return data[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removeElement = data[index];
        System.arraycopy(data, index + 1,
                data, index, size - index - 1);
        size--;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        int indexRemove = findByValue(element);
        if (indexRemove == -1) {
            throw new NoSuchElementException("Element " + element + " not found");
        }
        T removedElement;
        removedElement = data[indexRemove];
        remove(indexRemove);
        return removedElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void changeCapacity() {
        int actualCapacity = size + 1;
        int oldCapacity = data.length;
        if (actualCapacity > oldCapacity) {
            int newCapacity = (int) (oldCapacity * GROW_FACTOR);
            if (newCapacity < actualCapacity) {
                newCapacity = actualCapacity;
            }
            Object[] newElements = new Object[newCapacity];
            System.arraycopy(data, 0, newElements, 0, size);
            data = (T[]) newElements;
        }
    }

    private int findByValue(T element) {
        for (int i = 0; i < size; i++) {
            if (element == data[i] || element != null && element.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("You must indicate"
                     + " the index from 0 till " + (size - 1)
                     + " but was " + index);
        }
    }
}
