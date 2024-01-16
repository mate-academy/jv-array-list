package core.basesyntax;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;


    private int size;
    Object[] elements = new Object[DEFAULT_CAPACITY];

    @Override
    public void add(T value) {
        ensureCapacity();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index >=size()) {
            throw new ArrayListIndexOutOfBoundsException("Wishes index more than current list length");
        }
        ensureCapacity();
        Object[] newElements = new Object[elements.length + 1];
        System.arraycopy(elements, 0, newElements, 0, index - 2);
        newElements[index-1] = value;
        System.arraycopy(elements, index-1, newElements, index, elements.length);
        elements = newElements;
        size = size + 1;

    }

    @Override
    public void addAll(List<T> list) {
        ensureCapacity(list.size());
        for (int i = 0; i < list.size(); i++) {
            elements[size + i] = list.get(i);
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >=size()) {
            throw new ArrayListIndexOutOfBoundsException("Wishes index more than current list length");
        }
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >=size()) {
            throw new ArrayListIndexOutOfBoundsException("Wishes index more than current list length");
        }

    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >=size()) {
            throw new ArrayListIndexOutOfBoundsException("Wishes index more than current list length");
        }
        T removedItem = (T) elements[index];
        Object[] newElements = new Object[size - 1];
        System.arraycopy(elements, 0, newElements, 0, index);
        System.arraycopy(elements, index + 1, newElements, index, newElements.length - index);
        elements = newElements;
        size--;
        return removedItem;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == null) {
                if (elements[i] == element) {
                    remove(i);
                    return element;
                } else {
                    continue;
                }
            } else if (elements[i].equals(element)) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = size + (size >> 1);
            Object[] newElements = new Object[newCapacity];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }
    }

    private void ensureCapacity(int size) {
        if (size >= elements.length) {
            int newCapacity = size + (size >> 1);
            Object[] newElements = new Object[newCapacity];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }
    }

}
