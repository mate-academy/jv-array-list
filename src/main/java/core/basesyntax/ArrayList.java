package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private Object[] elements;
    private int size;
    private static final int DEFAULT_CAPASITY = 10;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPASITY];
        size = 0;
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            int newCapacity = elements.length + elements.length / 2;
            elements = Arrays.copyOf(elements, newCapacity);
        }

        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("invalid index");
        }

        if (size == elements.length) {
            int newCapacity = elements.length + elements.length / 2;
            elements = Arrays.copyOf(elements, newCapacity);
        }

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int listSize = list.size();

        for (int i = 0; i < listSize; i++) {
            if (size == elements.length) {
                int newCapacity = elements.length + elements.length / 2;
                elements = Arrays.copyOf(elements, newCapacity);
            }

            elements[size++] = list.get(i);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("invalid index");
        }

        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("invalid index");
        }
        elements[index] = value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("invalid index");
        }
        T oldElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;

        return oldElement;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(T element) {
        int index = -1;

        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    index = i;
                    break;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {
                    index = i;
                    break;
                }
            }
        }

        if (index == -1) {
            throw new NoSuchElementException("element not found");
        }

        T removedElement = (T) elements[index];

        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;

        return removedElement;
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
