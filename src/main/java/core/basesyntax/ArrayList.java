package core.basesyntax;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int BEGINNING_CAPACITY = 10;
    private static final double GROWTH_VALUE = 1.5;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[BEGINNING_CAPACITY];
    }

    @Override
    public void add(T value) {
        resizeIfNeeded();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        resizeIfNeeded();
        if (index != size) {
            System.arraycopy(elements, index, elements, index + 1, size - index);
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
        if (index == size) {
            throw new ArrayListIndexOutOfBoundsException("Your index is out of bounds " + index);
        } else {
            checkIndexForAdd(index);
        }

        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index == size) {
            throw new ArrayListIndexOutOfBoundsException("Your index is out of bounds " + index);
        } else {
            checkIndexForAdd(index);
        }

        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index == size) {
            throw new ArrayListIndexOutOfBoundsException("Your index is out of bounds " + index);
        } else {
            checkIndexForAdd(index);
        }

        T elementToRemove = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return elementToRemove;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (isEqual(elements[i], element)) {
                return remove(i);
            }
        }

        throw new NoSuchElementException("Can't delete this element, because it does not exist.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isEqual(T firstElement, T secondElement) {
        return firstElement != null
                && firstElement.equals(secondElement)
                || secondElement == firstElement;
    }

    public void increase() {
        int newCapacity = (int) (GROWTH_VALUE * size);
        T[] newArrayOfElements = (T[]) new Object[newCapacity];
        System.arraycopy(elements, 0, newArrayOfElements, 0, size);
        elements = newArrayOfElements;
    }

    private void resizeIfNeeded() {
        if (elements.length == size) {
            increase();
        }
    }

    private void checkIndexForAdd(int index) {
        if ((index > size) || (index < 0)) {
            throw new ArrayListIndexOutOfBoundsException("Your index is out of bounds " + index);
        }
    }
}
