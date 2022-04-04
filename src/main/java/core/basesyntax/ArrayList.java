package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int START_CAPACITY = 10;
    private int newSize = START_CAPACITY;
    private T[] elements;
    private T[] tail;
    private T remove;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[START_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == newSize) {
            elements = grow(elements);
            newSize = elements.length;
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == newSize) {
            elements = grow(elements);
            newSize = elements.length;
        }
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Cant add element by this index");
        }
        tail = getTail(elements, index);
        elements[index] = value;
        for (int i = 0; i < tail.length; i++) {
            elements[index + i + 1] = tail[i];
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (size + list.size() >= newSize) {
            elements = grow(elements);
            newSize = elements.length;
        }
        for (int i = 0; i < list.size(); i++) {
            elements[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("No element by this index");
        }
        for (int i = 0; i < elements.length; i++) {
            if (index == i) {
                return elements[i];
            }
        }
        return null;
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Can't set element at this position");
        }
        elements[index] = value;

    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Can't remove element at this position");
        }
        remove = elements[index];
        tail = getTail(elements, index);
        T[] tempArray = (T[]) new Object[size - 1];
        System.arraycopy(elements, 0, tempArray, 0, index);
        if (tail.length - 1 >= 0) {
            System.arraycopy(tail, 1, tempArray, index, tail.length - 1);
        }
        elements = tempArray;
        size--;
        return remove;
    }

    @Override
    public T remove(T elementToRemove) {
        remove = null;
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], elementToRemove)) {
                remove = elements[i];
                index = i;
            }
        }
        if (remove == null && elementToRemove != null) {
            throw new NoSuchElementException();
        }
        tail = getTail(elements, index);
        T[] tempArray = (T[]) new Object[size - 1];
        System.arraycopy(elements, 0, tempArray, 0, index);
        if (tail.length - 1 >= 0) {
            System.arraycopy(tail, 1, tempArray, index, tail.length - 1);
        }
        elements = tempArray;
        size--;
        return remove;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] getTail(T[] elements, int cutPosition) {
        T[] tail = (T[]) new Object[size - cutPosition];
        if (size - cutPosition >= 0) {
            System.arraycopy(elements, cutPosition, tail, 0, size - cutPosition);
        }
        return tail;
    }

    private T[] grow(T[] oldElements) {
        int additional = oldElements.length >> 1;
        int newValue = oldElements.length + additional;
        T[] newElements = (T[]) new Object[newValue];
        System.arraycopy(oldElements, 0, newElements, 0, oldElements.length);
        return newElements;
    }
}
