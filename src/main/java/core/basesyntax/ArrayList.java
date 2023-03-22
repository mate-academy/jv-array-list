package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        elements = (T[]) new Object[initialCapacity];
    }

    @Override
    public void add(T value) {
        resizeIdNeeded();
        elements[size] = value;
        size++;
    }

    private void resizeIdNeeded() {
        if (elements.length == size) {
            Object[] newArray = new Object[elements.length + (elements.length / 2)];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("index <" + index
                    + "> out of ArrayList current size bounds <" + size + ">");
        }
        if (elements.length == size) {
           resizeIdNeeded();
        }
        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
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
        Objects.checkIndex(index, size);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        Objects.checkIndex(index, size);
        elements[index] = value;

    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size + 1);
        T removedElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - 1 - index);
        size--;
        return removedElement;

    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < this.size; i++) {
            T currentElement = (T) elements[i];
            if (currentElement == element || currentElement != null
                    && currentElement.equals(element)) {
                remove(i);
                return currentElement;
            }
        }
        throw new NoSuchElementException("Cannot find element <"
                + (element == null ? "null" : element.toString()) + ">");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return  size == 0;
    }

}
