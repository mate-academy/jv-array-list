package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final double INCREMENT_CAPACITY = 1.5;
    private int size;
    private T[] elements;
    private int arrayCapacity;

    public ArrayList() {
        arrayCapacity = INITIAL_CAPACITY;
        elements = (T[]) new Object[arrayCapacity];
    }

    private void grow() {
        arrayCapacity *= INCREMENT_CAPACITY;
        T[] tempArray = (T[]) new Object[arrayCapacity];
        System.arraycopy(elements, 0, tempArray, 0, size);
        elements = tempArray;
    }

    private void checkItem(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid");
        }
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            grow();
        }
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        checkItem(index);
        if (size == elements.length) {
            grow();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
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
        checkItem(index + 1);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkItem(index + 1);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkItem(index + 1);
        T removeObject = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removeObject;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && elements[i] == null)
                    || (element != null && element.equals(elements[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element isn't exist");
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
