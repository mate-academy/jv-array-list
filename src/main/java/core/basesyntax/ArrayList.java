package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final int FIRST_ELEMENT_OF_ARRAY = 0;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (isFull()) {
            resizeArray();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < FIRST_ELEMENT_OF_ARRAY) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index + " is incorrect");
        }
        if (isFull()) {
            resizeArray();
        }
        if (index == size) {
            add(value);
        } else {
            System.arraycopy(elements, index, elements, index + 1, size - index);
            elements[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < FIRST_ELEMENT_OF_ARRAY) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Element by index " + index + " is not exist");
        }
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < size && index >= FIRST_ELEMENT_OF_ARRAY) {
            elements[index] = value;
        } else {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index + " is incorrect");
        }
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < FIRST_ELEMENT_OF_ARRAY) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index + " is incorrect");
        }
        T value = (T) elements[index];
        if (index == size - 1) {
            elements[index] = null;
        } else {
            System.arraycopy(elements, index + 1, elements, index, size - index);
        }
        size--;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == null && element != null) {
                continue;
            }
            if (elements[i] == element || elements[i].equals(element)) {
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
        return size == 0;
    }

    public int capacity() {
        return elements.length;
    }

    private boolean isFull() {
        return elements.length == size;
    }

    private void resizeArray() {
        Object[] temporary = elements;
        elements = new Object[capacity() + (capacity() / 2)];
        System.arraycopy(temporary, FIRST_ELEMENT_OF_ARRAY,
                    elements, FIRST_ELEMENT_OF_ARRAY, size);
    }
}
