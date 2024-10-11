package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private T[] elements;
    private int size = 0;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    private void ensureCapacity() {
        T[] newElements = (T[]) new Object[elements.length * 2];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    private boolean isFull() {
        return size == elements.length;
    }

    private boolean isIndexValid(int index) {
        return index < size && index >= 0;
    }

    @Override
    public void add(T value) {
        if (isFull()) {
            ensureCapacity();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index >= size + 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "You tried to add element to non exist index"
            );
        }
        if (isFull()) {
            ensureCapacity();
        }
        T[] newElements = (T[]) new Object[elements.length];

        for (int i = 0; i <= size; i++) {
            if (i > index) {
                newElements[i] = elements[i - 1];
            } else {
                newElements[i] = elements[i];
            }
        }
        newElements[index] = value;
        size++;
        elements = newElements;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (!isIndexValid(index)) {
            throw new ArrayListIndexOutOfBoundsException("You"
                    + " tried to get element by non existed index");
        }
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (!isIndexValid(index)) {
            throw new ArrayListIndexOutOfBoundsException(
                    "You tried to set element to non exist index"
            );
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (!isIndexValid(index)) {
            throw new ArrayListIndexOutOfBoundsException(
                    "You tried to remove element from non exist index"
            );
        }
        final T elementToRemove = elements[index];
        T[] newElements = (T[]) new Object[elements.length];
        for (int i = 0; i < size; i++) {
            if (i > index) {
                newElements[i - 1] = elements[i];
            } else {
                newElements[i] = elements[i];
            }
        }
        elements = newElements;
        size--;
        return elementToRemove;
    }

    @Override
    public T remove(T element) {
        T[] newElements = (T[]) new Object[elements.length];
        T elementToRemove = null;
        boolean isElementFound = false;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, elements[i])) {
                isElementFound = true;
                elementToRemove = elements[i];
            }
            if (isElementFound) {
                newElements[i] = elements[i + 1];
            } else {
                newElements[i] = elements[i];
            }
        }
        elements = newElements;
        if (isElementFound) {
            size--;
            return elementToRemove;
        } else {
            throw new NoSuchElementException();
        }
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
