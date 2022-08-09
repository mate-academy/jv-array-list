package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private Object[] elements;
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
        checkValidationIndex(index, size + 1);
        checkingSizeOfList();
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
        checkValidationIndex(index, size);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkValidationIndex(index, size);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkValidationIndex(index, size);
        final Object object = elements[index];
        elements[index] = null;
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return (T) object;
    }

    @Override
    public T remove(T value) {
        for (int i = 0; i < size; i++) {
            if (value != null && value.equals(elements[i])
                    || value == elements[i]) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("This value is missing in list");
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

    private void checkValidationIndex(int index, int size) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index value: "
                    + index + ". Index out of bounds. Index: ");
        }
    }
}
