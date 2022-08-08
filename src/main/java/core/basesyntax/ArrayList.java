package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private Object[] elements;
    private Object object;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        checkingSizeOfList();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index value. "
                    + "Index out of bounds.");
        }
        checkingSizeOfList();
        if (index != size) {
            System.arraycopy(elements, index, elements, index + 1, size - index);
        }
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
        if (validationIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index value. "
                    + "Index out of bounds.");
        }
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (validationIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index value. "
                    + "Index out of bounds.");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (validationIndex(index)) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index value. "
                    + "Index out of bounds.");
        }
        object = elements[index];
        elements[index] = null;
        if (index != size - 1) {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        }
        size--;
        return (T) object;
    }

    @Override
    public T remove(T value) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (value != null && value.equals(elements[i]) || value == elements[i]) {
                index = i;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException("This value is missing in list");
        }
        elements[index] = null;
        if (index != size - 1) {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        }
        size--;
        return value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkingSizeOfList() {
        if (size == elements.length) {
            elements = Arrays.copyOf(elements,size + size / 2);
        }
    }

    private boolean validationIndex(int index) {
        return index >= size || index < 0;
    }
}
