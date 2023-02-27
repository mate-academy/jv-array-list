package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private Object[] elements;
    private int size;

    public ArrayList() {
        this.size = 0;
        this.elements = new Object[size];
    }

    public ArrayList(Object[] elements, int size) {
        this.size = size;
        this.elements = elements;
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index > size() || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        } else if (index < size()) {
            size++;
            Object[] newElements = new Object[size()];
            for (int i = size() - 1; i > index; i--) {
                newElements[i] = elements[i - 1];
            }
            newElements[index] = value;
            for (int j = index - 1; j >= 0; j--) {
                newElements[j] = elements[j];
            }
            elements = newElements;
        } else if (index == size()) {
            size++;
            Object[] newElements = new Object[size()];
            for (int i = 0; i < size() - 1; i++) {
                newElements[i] = elements[i];
            }
            newElements[index] = value;
            elements = newElements;
        }
    }

    @Override
    public void addAll(List<T> list) {
        Object[] newElements = new Object[size() + list.size()];

        for (int i = 0; i < size(); i++) {
            newElements[i] = elements[i];
        }
        for (int j = 0; j < list.size(); j++) {
            newElements[size() + j] = list.get(j);
        }
        size = newElements.length;
        elements = newElements;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        } else {
            return (T)elements[index];
        }
    }

    @Override
    public void set(T value, int index) throws ArrayListIndexOutOfBoundsException {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        } else {
            elements[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        } else {
            T removedElement = (T)elements[index];
            for (int i = index; i < size() - 1; i++) {
                elements[i] = elements[i + 1];
            }
            size--;
            return removedElement;
        }
    }

    @Override
    public T remove(T element) {
        int j;
        for (j = 0; j < size; j++) {
            if (equals(elements[j], element)) {
                return remove(j);
            }
        }
        throw new NoSuchElementException("no such element in array list");
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean equals(Object element1, Object element2) {
        if (element1 != null && element2 == null || element1 == null && element2 != null) {
            return false;
        }
        if (element1 == null && element2 == null
                || element1 == element2
                || element1.equals(element2)) {
            return true;
        }
        return false;
    }
}
