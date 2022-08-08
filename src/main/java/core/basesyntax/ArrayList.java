package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private int size;
    private Object[] elements;

    public ArrayList() {
        elements = new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        checkCapacity();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of List Range");
        }
        checkCapacity();
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
        Object element = elements[index];
        deleteElement(index);

        return (T) element;
    }

    @Override
    public T remove(T element) {
        int index = getIndex(element);
        if (index == -1) {
            throw new NoSuchElementException("No such element in List");
        }
        deleteElement(index);
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void deleteElement(int index) {
        elements[index] = null;
        if (index != size - 1) {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        }
        size--;
    }

    private int getIndex(T elem) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], elem)) {
                return i;
            }
        }
        return -1;
    }

    private void checkCapacity() {
        if (size >= elements.length) {
            elements = Arrays.copyOf(elements, size + size / 2);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of List Range");
        }
    }
}
