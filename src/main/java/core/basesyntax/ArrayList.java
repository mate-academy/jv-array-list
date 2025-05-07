package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elements.length) {
            grow();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (!isIndexValid(index, 0, size + 1)) {
            throw new ArrayListIndexOutOfBoundsException("Can't write an element to ArrayList");
        }
        if (size == elements.length) {
            grow();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (!isIndexValid(index, 0, size - 1)) {
            throw new ArrayListIndexOutOfBoundsException("Can't get an element");
        }
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (!isIndexValid(index, 0, size + 1)) {
            throw new ArrayListIndexOutOfBoundsException("There is not such index");
        }
        elements[index] = value;
        if (index == size) {
            this.get(index);
        } else {
            elements[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (!isIndexValid(index, 0, size - 1)) {
            throw new ArrayListIndexOutOfBoundsException("Index "
                    + String.valueOf(index)
                    + " is not exist");
        }
        Object removedObject = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return (T) removedObject;
    }

    @Override
    public T remove(T element) {
        Object removedObject;
        for (int i = 0; i < size; i++) {
            if (element == elements[i] || elements[i] != null && elements[i].equals(element)) {
                removedObject = elements[i];
                remove(i);
                return (T) removedObject;
            }
        }
        throw new NoSuchElementException("There is not such element.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        Object[] newObject = new Object[elements.length + elements.length / 2];
        for (int i = 0; i < elements.length; i++) {
            newObject[i] = elements[i];
        }
        elements = newObject;
    }

    private boolean isIndexValid(int index, int indexFrom, int indexTo) {
        return index >= indexFrom && index <= indexTo;
    }
}
