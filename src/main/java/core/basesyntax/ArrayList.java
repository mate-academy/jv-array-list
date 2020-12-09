package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int ARRAY_CAPACITY = 10;
    private T[] elements;
    private int size = 0;

    public ArrayList() {
        this.elements = (T[]) new Object[ARRAY_CAPACITY];
    }

    @Override
    public void add(T value) {
        elements[size] = value;
        size++;
        if (elements.length == size) {
            T[] newElements = (T[]) new Object[(int) (elements.length * 1.5)];
            for (int i = 0; i < elements.length; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
    }

    @Override
    public void add(T value, int index) {
        if (index >= 0 && index <= size) {
            if (elements.length == size) {
                grow();
            }
            for (int i = size; i > index; i--) {
                elements[i] = elements[i - 1];
            }
            elements[index] = value;
            size++;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            return elements[index];
        } else {
            throw new ArrayIndexOutOfBoundsException("Wrong index!");
        }
    }

    @Override
    public void set(T value, int index) {
        if (index >= 0 && index < size) {
            elements[index] = value;
        } else {
            throw new ArrayIndexOutOfBoundsException("Wrong index!");
        }
    }

    @Override
    public T remove(int index) {
        if (index >= 0 && index < size) {
            T deletedElement = elements[index];
            if (index >= 0 && index < size) {
                for (int i = index; i < size - 1; i++) {
                    elements[i] = elements[i + 1];
                }
                size--;
            }
            return deletedElement;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public T remove(T t) {
        int deleteIndex = -1;
        T returnElement = (T) new Object();
        for (int i = 0; i < elements.length; i++) {
            if ((t == null && elements[i] == null)
                    || ((t != null && elements[i] != null) && t.equals(elements[i]))) {
                deleteIndex = i;
                returnElement = elements[i];
                remove(i);
                break;
            }
        }
        if (deleteIndex != -1) {
            return returnElement;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size > 0) {
            return false;
        } else {
            return true;
        }
    }

    public void grow() {
        T[] newElements = (T[]) new Object[(int) (elements.length * 1.5)];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }
}
