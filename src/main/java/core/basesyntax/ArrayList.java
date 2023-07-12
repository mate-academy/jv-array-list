package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double MAGNIFICATION_FACTOR = 1.5;
    private static int defaultCapacity = 10;
    private int size;
    private T[] elements = (T[]) new Object[defaultCapacity];

    @Override
    public void add(T value) {
        growIfArrayFull();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        growIfArrayFull();
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
        System.arraycopy(elements, index, elements, index + 1,size - index);
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
        indexCheck(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        indexCheck(index);
        T elementToRemove = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return elementToRemove;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || elements[i] != null && elements[i].equals(element)) {
                return remove(i);
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

    private void growIfArrayFull() {
        if (size == defaultCapacity) {
            defaultCapacity *= MAGNIFICATION_FACTOR;
            T[] value = elements;
            elements = (T[]) new Object[defaultCapacity];
            System.arraycopy(value, 0, elements, 0, size);
        }
    }

    private void indexCheck(int listIndex) {
        if (listIndex < 0) {
            throw new ArrayListIndexOutOfBoundsException("Entered negative index " + listIndex);
        }
        if (listIndex >= size) {
            throw new ArrayListIndexOutOfBoundsException("Entered index " + listIndex
                    + " exceeds the size");
        }
    }
}
