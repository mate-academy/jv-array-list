package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkLengthAndResize();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        checkLengthAndResize();
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
        checkIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        Object value = elements[index];
        if (index == size - 1) {
            elements[index] = null;
        } else {
            System.arraycopy(elements, index + 1, elements, index, size - index);
        }
        size--;
        return (T) value;
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
        throw new NoSuchElementException("The chosen element is not exist.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int capacity() {
        return elements.length;
    }

    private void checkLengthAndResize() {
        if (elements.length == size) {
            resizeArray();
        }
    }

    private void resizeArray() {
        Object[] temporary = elements;
        elements = new Object[capacity() + (capacity() / 2)];
        System.arraycopy(temporary, 0,
                    elements, 0, size);
    }

    private void checkIndex(int index) {
        if (!(index < size && index >= 0)) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index + "is incorrect."
                    + "Element by this index is not exist."
                    + "Array size: " + size);
        }
    }
}
