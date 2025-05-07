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
        copyArray(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    private void copyArray(T[] arrayFrom,
                           int arrayFromPos,
                           T[] arrayTo,
                           int arrayToPos,
                           int amount) {
        for (int i = 0; i < amount; i++) {
            arrayTo[arrayToPos + i] = arrayFrom[arrayFromPos + i];
        }
    }

    private boolean isFull() {
        return size == elements.length;
    }

    private boolean isIndexValid(int index) {
        return index < size && index >= 0;
    }

    private boolean isIndexValidForAdd(int index) {
        return index <= size && index >= 0;
    }

    @Override
    public void add(T value) {
        elements[size] = value;
        size++;
        if (isFull()) {
            ensureCapacity();
        }
    }

    @Override
    public void add(T value, int index) {
        if (!isIndexValidForAdd(index)) {
            throw new ArrayListIndexOutOfBoundsException(
                    "You tried to add element to non exist index"
            );
        }

        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = value;
        size++;

        if (isFull()) {
            ensureCapacity();
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            elements[size] = list.get(i);
            size++;
            if (isFull()) {
                ensureCapacity();
            }
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
        size--;
        copyArray(elements, 0, newElements, 0, index);
        copyArray(elements, index + 1, newElements, index, size - index);
        elements = newElements;
        return elementToRemove;
    }

    @Override
    public T remove(T element) {
        boolean isElementFound = false;
        int indexOfFoundElement = 0;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, elements[i])) {
                isElementFound = true;
                indexOfFoundElement = i;
            }
        }
        if (isElementFound) {
            return remove(indexOfFoundElement);
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
