package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private int capacity = 0;
    private Object[] elements;

    public ArrayList() {
        capacity = DEFAULT_CAPACITY;
        elements = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        this.capacity = capacity;
        elements = new Object[capacity];
    }

    private void increaseCapacity() {
        capacity = capacity * 3 / 2;
        Object[] newArray = new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = elements[i];
            elements[i] = null;
        }
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
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
        if (size + 1 >= capacity) {
            increaseCapacity();
        }
        if (index > size) {
            index = size;
        }
        for (int i = size; i >= index; i--) {
            elements[i + 1] = elements[i];
        }
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
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
    }

    @Override
    public void set(T value, int index) {
        if ((index < size) && (index >= 0)) {
            Object o = elements[index];
            elements[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
    }

    @Override
    public T remove(int index) {
        Object o = null;
        if ((index < size) && (index >= 0)) {
            o = get(index);
            shiftToLeft(index);
        } else {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
        return (T) o;
    }

    @Override
    public T remove(T element) {
        if ((size == 0)) {
            return null;
        }
        int i;
        for (i = 0; i < size; i++) {
            if (elements[i] == null && element == null) {
                break;
            }
            if ((elements[i] != null) && (elements[i].equals(element))) {
                break;
            }
        }
        if (i < size) {
            shiftToLeft(i);
            return element;
        } else {
            throw new NoSuchElementException("Wrong element");
        }

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
}

