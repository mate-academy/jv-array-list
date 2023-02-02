package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_FACTOR = 1.5;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (isLengthInsufficient(this.size)) {
            changeCapacity();
        }
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("index " + index + "is incorrect");
        }
        if (isLengthInsufficient(size)) {
            changeCapacity();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
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
        checkRange(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkRange(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkRange(index);
        T removedElement = elements[index];
        removeElement(index);
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < this.size; i++) {
            if (element == elements[i] || element != null && element.equals(elements[i])) {
                T removedElement = elements[i];
                removeElement(i);
                return removedElement;
            }
        }
        throw new NoSuchElementException("There is no such element in list " + element);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private boolean isLengthInsufficient(int needCapacity) {
        return needCapacity == elements.length;
    }

    private int getResizeCapacity() {
        return (int) (elements.length * GROW_FACTOR);
    }

    private void changeCapacity() {
        T[] temp = (T[]) new Object[getResizeCapacity()];
        System.arraycopy(elements, 0, temp, 0, elements.length);
        this.elements = temp;
    }

    private void checkRange(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " out of range " + size);
        }
    }

    private void removeElement(int index) {
        size--;
        if ((size - index) > 0) {
            System.arraycopy(this.elements, index + 1, this.elements, index, size - index);
        }
        elements[size] = null;
    }
}
