package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int INDEX_NOT_FOUND = -1;
    private static final int DEFAULT_CAPACITY = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    private T[] resize(int currentSize, int index) {
        T[] newArray = (T[]) new Object[currentSize * 3 / 2];
        System.arraycopy(elements, 0, newArray, 0, index);
        System.arraycopy(elements, index, newArray, index + 1, elements.length - index);
        return elements = newArray;
    }

    private T[] resize(int currentSize) {
        T[] newArray = (T[]) new Object[currentSize * 3 / 2];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        return elements = newArray;
    }

    private void checkIndex(int index, String message) {
        if (!(index < size) || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(message);
        }
    }

    @Override
    public void add(T value) {
        if (elements.length == size) {
            elements = resize(elements.length);
        }
        this.elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't add element by this index");
        }
        if (elements.length == size) {
            elements = resize(elements.length, index);
        } else {
            T[] newArray = (T[]) new Object[elements.length];
            System.arraycopy(elements, 0, newArray, 0, index);
            System.arraycopy(elements, index, newArray, index + 1, elements.length - index - 1);
            elements = newArray;
        }
        this.elements[index] = value;
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
        checkIndex(index, "Can't get element by this index");
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, "Can't get element by this index");
        this.elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index,"Can't remove element by this index");
        final T removedElement = elements[index];
        T[] newArray = (T[]) new Object[elements.length];
        System.arraycopy(elements, 0, newArray, 0, index);
        System.arraycopy(elements,index + 1, newArray, index, elements.length - index - 1);
        elements = newArray;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int elementsIndex = INDEX_NOT_FOUND;
        for (int i = 0; i < elements.length; i++) {
            if (Objects.equals(elements[i], element)) {
                elementsIndex = i;
                break;
            }
        }
        if (elementsIndex == INDEX_NOT_FOUND) {
            throw new NoSuchElementException("There is no such element in list");
        }
        return remove(elementsIndex);
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
