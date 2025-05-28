package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private Object[] elements;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size >= elements.length) {
            Object[] clone = elements.clone();
            elements = new Object[clone.length + clone.length / 2];
            for (int i = 0; i < clone.length; i++) {
                elements[i] = clone[i];
            }
            elements[size] = value;

        } else {
            elements[size] = value;
        }
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
        if (size + 1 >= elements.length) {
            Object[] clone = elements.clone();
            elements = new Object[clone.length + clone.length / 2];
            for (int i = 0; i < size; i++) {
                elements[i] = clone[i];
            }
        }
        if (index == size) {
            elements[index] = value;
            size++;
        } else {
            Object[] firstPiece = Arrays.copyOfRange(elements, 0, index);
            Object[] secondPiece = Arrays.copyOfRange(elements, index, size);
            for (int i = 0; i < firstPiece.length; i++) {
                elements[i] = firstPiece[i];
            }
            elements[index] = value;
            for (int i = 0; i < secondPiece.length; i++) {
                elements[index + i + 1] = secondPiece[i];
            }
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (list.size() + size >= elements.length) {
            Object[] clone = elements.clone();
            while (list.size() + size >= elements.length) {
                elements = new Object[elements.length + elements.length / 2];
            }
            for (int i = 0; i < size; i++) {
                elements[i] = clone[i];
            }
        }
        for (int i = 0; i < list.size(); i++) {
            elements[size + i] = list.get(i);
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index");
        }
        Object element = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return (T) element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || (element != null && element.equals(elements[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("There is no element");
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
