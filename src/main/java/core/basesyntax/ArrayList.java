package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = elements.length + (elements.length >> 1); // Збільшуємо на 1.5x
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity; // Якщо потрібно більше, ніж 1.5x, беремо реальну потребу
            }
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    @Override
    public void add(T value) {
        ensureCapacity(size + 1);
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index" + index);
        }

        ensureCapacity(size + 1);

        for (int i = size; i > index; i--) {
            elements[i] = elements [i - 1];
        }
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new NullPointerException("Null");
        }
        int listSize = list.size();
        while (size + listSize > elements.length) {
            ensureCapacity(size + listSize);
        }
        if ((!list.isEmpty())) {

            for (int i = 0; i < listSize; i++) {
                elements[size + i] = list.get(i);
            }
        }
        size += listSize;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index" + index);
        }
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return elements[i];
            }
        }
        return null;
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out");
        }
        final T removedElement = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements [i + 1];
        }
        elements[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i],element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found" + element);
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
