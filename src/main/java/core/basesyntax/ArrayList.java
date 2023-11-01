package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList(int intCapacity) {
        if (intCapacity <= 0) {
            throw new IllegalArgumentException();
        }
        elements = new Object[intCapacity];
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    private void resizeItNeeded() {

        if (elements.length == size) {
            Object[] newArray = new Object[elements.length + elements.length / 2];
            System.arraycopy(elements,0,newArray,0,size);
            elements = newArray;
        }
    }

    @Override
    public void add(T value) {
        resizeItNeeded();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        resizeItNeeded();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        int newSize = size + list.size();
        if (newSize > elements.length) {
            Object[] newArray = new Object[Math.max(elements.length * 2, newSize)];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
        System.arraycopy(((ArrayList<T>) list).elements, 0, elements, size, list.size());
        size = newSize;
    }

    @Override
    @SuppressWarnings("Uncheked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        return (T)elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (isEmpty()) {
            throw new ArrayListIndexOutOfBoundsException("List is empty");
        }
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index: " + index);
        }
        final T removedValue = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[size - 1] = null;
        size--;
        return removedValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, elements[i])) {
                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                elements[size - 1] = null;
                size--;
                return element;
            }
        }
        throw new NoSuchElementException("Element not found: " + element);
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
