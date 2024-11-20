package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int DEFAULT_SIZE = 0;
    private Object[] elements;
    private int capacity;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
        size = DEFAULT_SIZE;
        capacity = DEFAULT_CAPACITY;
    }

    private void resize() {
        capacity = (int) (capacity * 1.5);
        elements = Arrays.copyOf(elements, capacity);
    }

    @Override
    public void add(T value) {
        if (size == capacity) {
            resize();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("The index " + index + " is unavailable");
        }
        if (size == capacity) {
            resize();
        }
        if (index == size) {
            elements[index] = value;
        } else {
            for (int i = size; i > index; i--) {
                elements[i] = elements[i - 1];
            }
            elements[index] = value;
        }
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        while (capacity < size + list.size()) {
            resize();
        }
        for (int i = 0; i < list.size(); i++) {
            elements[size] = list.get(i);
            size++;
        }

    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index " + index + " is unavailable");
        }
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index " + index + " is unavailable");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("The index " + index + " is unavailable");
        }
        final T removedElement = (T) elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int removeIndex = -1;
        for (int i = 0; i < size(); i++) {
            if (element == null) {
                if (elements[i] == null) {
                    removeIndex = i;
                    break;
                }
            } else if (elements[i] != null && elements[i].equals(element)) {
                removeIndex = i;
                break;
            }
        }
        if (removeIndex == -1) {
            throw new NoSuchElementException("The element " + element + " does not exist");
        }
        final T removedElement = (T) elements[removeIndex];
        for (int k = removeIndex; k < size - 1; k++) {
            elements[k] = elements[k + 1];
        }
        elements[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
}
