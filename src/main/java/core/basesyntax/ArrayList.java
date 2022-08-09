package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            Object[] newObject = new Object[(int) (elements.length * 1.5)];
            for (int i = 0; i < elements.length; i++) {
                newObject[i] = elements[i];
            }
            elements = newObject;
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't write an element with index "
                    + index + " to ArrayList");
        }
        if (size == elements.length) {
            Object[] newObject = new Object[(int) (elements.length * 1.5)];
            for (int i = 0; i < elements.length; i++) {
                newObject[i] = elements[i];
            }
            elements = newObject;
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index > size() - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't get an element with index "
                    + index + "from ArrayList");
        }
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > size() - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of bounds of the list size");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of bounds of the list size");
        }
        T removed = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removed;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] != null && elements[i].equals(element) || elements[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No element " + element + " in the list");
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
