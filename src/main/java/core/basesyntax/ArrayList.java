package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_LIST_CAPACITY = 10;
    private static final double ARRAY_MULTIPLIER = 1.5;
    private T[] elements;
    private int size;

    public ArrayList() {
        this.elements = (T[]) new Object[DEFAULT_ARRAY_LIST_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            resize();
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        if (size == elements.length) {
            resize(value, index);
        } else {
            for (int i = size - 1; i >= index; i--) {
                elements[i + 1] = elements[i];
            }
            elements[index] = value;
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int remainingCapacity = elements.length - size;
        if (list.size() > remainingCapacity) {
            resize();
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        final T removedElement = elements[index];
        int elementsMoved = size - index - 1;
        if (elementsMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, elementsMoved);
        }
        size--;
        elements[size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, elements[i])) {
                return remove(i);

            }
        }
        throw new NoSuchElementException("There is no such value");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndexForAdd(int index) {
        String msg = "Index must be between 0 and ";
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(msg + (size - 1));
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index must be between 0 and " + (size - 1)
            );
        }
    }

    private void resize() {
        int newCapacity = (int) (elements.length * ARRAY_MULTIPLIER);
        T[] newElements = (T[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = newElements;
    }

    private void resize(T value, int index) {
        int newCapacity = (int) (elements.length * ARRAY_MULTIPLIER);
        T[] newElements = (T[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, index);
        newElements[index] = value;
        System.arraycopy(elements, index, newElements, index + 1, size - index);
        elements = newElements;
    }
}
