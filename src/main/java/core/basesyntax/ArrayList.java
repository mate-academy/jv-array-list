package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private int capacity;
    private Object[] elements;

    public ArrayList() {
        capacity = DEFAULT_CAPACITY;
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        this.capacity = capacity;
        elements = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size >= capacity) {
            increaseCapacity();
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            getArrayListIndexOutOfBoundsException();
        }
        if (size + 1 >= capacity) {
            increaseCapacity();
        }
        if (index > size) {
            index = size;
        }
        System.arraycopy(elements, 0, elements, 1, size);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                add((T) list.get(i));
            }
        }
    }

    @Override
    public T get(int index) {
        if ((index < size) && (index >= 0)) {
            return (T) elements[index];
        } else {
            getArrayListIndexOutOfBoundsException();
        }
        return null;
    }

    @Override
    public void set(T value, int index) {
        if ((index < size) && (index >= 0)) {
            Object o = elements[index];
            elements[index] = value;
        } else {
            getArrayListIndexOutOfBoundsException();
        }
    }

    @Override
    public T remove(int index) {
        Object o = null;
        if ((index < size) && (index >= 0)) {
            o = get(index);
            shiftToLeft(index);
        } else {
            getArrayListIndexOutOfBoundsException();
        }
        return (T) o;
    }

    @Override
    public T remove(T element) {

        for (int i = 0; i < size; i++) {
            if (elements[i] == element || elements[i] != null && elements[i].equals(element)) {
                return remove(i);
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
        if (size == 0) {
            return true;
        }
        return false;
    }

    private void increaseCapacity() {
        capacity = capacity * 3 / 2;
        Object[] newArray = new Object[capacity];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }

    private void shiftToLeft(int start) {
        size--;
        if (size <= 0) {
            return;
        }
        if (size != start) {
            System.arraycopy(elements, start + 1, elements, start, size - start);
        }
        elements[size] = null;
    }

    private void getArrayListIndexOutOfBoundsException() {
        throw new ArrayListIndexOutOfBoundsException("Wrong input data");
    }
}

