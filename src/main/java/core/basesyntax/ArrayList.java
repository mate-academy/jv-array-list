package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[])new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size < elements.length) {
            elements[size++] = value;
        } else {
            resize();
            elements[size++] = value;
        }
    }

    @Override
    public void add(T value, int index) {
        isValidIndexForAdd(index);
        if (size + 1 > elements.length) {
            resize();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() + size > elements.length) {
            resize();
        }
        int indexOfLastElement = 0;
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == null) {
                indexOfLastElement = i;
                break;
            }
        }
        for (int i = 0; i < list.size(); i++) {
            elements[indexOfLastElement++] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        isValidIndex(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        isValidIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        isValidIndex(index);
        T deletedElement = elements[index];
        for (int i = index; i < elements.length - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return deletedElement;
    }

    @Override
    public T remove(T element) {
        T deletedElement;
        for (int i = 0; i < size; i++) {
            if (elements[i] != null && elements[i].equals(element)) {
                deletedElement = elements[i];
                remove(i);
                return deletedElement;
            } else if (elements[i] == element) {
                deletedElement = elements[i];
                remove(i);
                return deletedElement;
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

    private void resize() {
        elements = Arrays.copyOf(elements, elements.length + (elements.length >> 1));
    }

    private boolean isValidIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("invalid index: " + index + " not exist");
        }
        return true;
    }

    private boolean isValidIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("wrong index: " + index
                    + " for ArrayList size: " + size);
        }
        return true;
    }
}
