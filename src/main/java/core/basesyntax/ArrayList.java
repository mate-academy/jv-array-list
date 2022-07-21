package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double MULTIPLIER_CAPACITY = 1.5;
    private int size;
    private Object[] elements;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            elements = this.grow();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bound.");
        }
        if (size == elements.length) {
            elements = this.grow();
        }
        ++size;
        Object[] newElements = new Object[elements.length];
        for (int i = 0; i < size; i++) {
            if (i == index) {
                newElements[i] = value;
            } else if (i < index) {
                newElements[i] = elements[i];
            } else {
                newElements[i] = elements[i - 1];
            }
        }
        elements = newElements;
    }

    @Override
    public void addAll(List<T> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                add(list.get(i));
            }
        }
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bound.");
        } else {
            return (T) elements[index];
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bound.");
        } else {
            elements[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index + " out of bound.");
        } else {
            Object[] newElements = new Object[elements.length];
            for (int i = 0; i < size; i++) {
                if (i < index) {
                    newElements[i] = elements[i];
                } else if (i > index) {
                    newElements[i - 1] = elements[i];
                }
            }
            T removeElement = (T) elements[index];
            --size;
            elements = newElements;
            return removeElement;
        }
    }

    @Override
    public T remove(T element) {
        int index = getIndex(element);
        if (index >= 0) {
            remove(index);
        } else {
            throw new NoSuchElementException();
        }
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

    private Object[] grow() {
        return elements = Arrays.copyOf(elements, (int) (size * MULTIPLIER_CAPACITY));
    }

    private int getIndex(T element) {
        for (int i = 0; i < this.size; i++) {
            T value = (T) elements[i];
            if ((value != null && value.equals(element)) || value == element) {
                return i;
            }
        }
        return -1;
    }
}
