package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int CAPACITY_INDEX = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[CAPACITY_INDEX];
    }

    @Override
    public void add(T value) {
        resize();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index! Index '"
                    + index + "' doesn't exist");
        }
        resize();
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
        checkIndex(index);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        T oldElement = null;
        for (int i = 0; i < size; i++) {
            if (elements[i] != null && elements[i].equals(element)
                    || elements[i] == null && elements[i] == element) {
                oldElement = element;
                return remove(i);
            }
        }
        if (oldElement != element) {
            throw new NoSuchElementException("This " + element + " wasn't found");
        }
        return oldElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index! Index '"
                    + index + "' doesn't exist");
        }
    }

    private void resize() {
        if (elements.length == size) {
            int newCapacity = (int) (elements.length * CAPACITY_INDEX);
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = (T[]) newArray;
        }
    }
}
