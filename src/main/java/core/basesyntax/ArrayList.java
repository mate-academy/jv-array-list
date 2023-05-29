package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double NUMBER_INCREAD_ARRAY = 1.5;
    private Object[] elements;
    private int size;

    public ArrayList() {
        this.elements = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(T value) {
        if (elements.length == size) {
            Object[] newArray = new Object[(int) (elements.length * NUMBER_INCREAD_ARRAY)];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("index are incorect");
        }
        if (elements.length == size) {
            Object[] newArray = new Object[(int) (elements.length * NUMBER_INCREAD_ARRAY)];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (size + list.size() > elements.length) {
            T[] newArray = (T[]) new Object[size + list.size()];
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
        int index = size;
        for (int i = 0; i < list.size(); i++) {
            elements[index++] = list.get(i);
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index are incorect");
        }
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index are incorect");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("index are incorect");
        }
        T removedIndex = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        return removedIndex;
    }

    @Override
    public T remove(T element) {
        int indexOfElement = -1;
        for (int i = 0; i < elements.length; i++) {
            if (Objects.equals(elements[i], element)) {
                indexOfElement = i;
                break;
            }
        }
        if (indexOfElement == -1) {
            throw new NoSuchElementException("elements not found");
        }
        T removedElements = (T) elements[indexOfElement];
        System.arraycopy(elements, indexOfElement + 1, elements, indexOfElement,
                size - indexOfElement - 1);
        size--;
        return removedElements;
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
