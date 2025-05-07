package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE_OF_ARRAY = 10;
    private static final double ARRAY_SIZE_MULTIPLIER = 1.5;
    private int size;
    private T[] arrayElements;

    public ArrayList() {
        this.arrayElements = (T[]) new Object[DEFAULT_SIZE_OF_ARRAY];
    }

    @Override
    public void add(T value) {
        extendArray();
        arrayElements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        extendArray();
        checkIndex(index);
        System.arraycopy(arrayElements, index, arrayElements, index + 1, size - index);
        arrayElements[index] = value;
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
        return arrayElements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        arrayElements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = arrayElements[index];
        System.arraycopy(arrayElements,index + 1, arrayElements, index, size - index - 1);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (equals(element, arrayElements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Such element doesn't exist in the array.");
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
            throw new ArrayListIndexOutOfBoundsException("Index is incorrect for this array size.");
        }
    }

    private void extendArray() {
        if (arrayElements.length == size) {
            T[] newArrayElements = (T[]) new Object[(int) (size * ARRAY_SIZE_MULTIPLIER)];
            System.arraycopy(arrayElements, 0, newArrayElements, 0, size);
            arrayElements = newArrayElements;
        }
    }

    private Boolean equals(T firstElement, T secondElement) {
        return (firstElement == secondElement)
                || (firstElement != null && firstElement.equals(secondElement));
    }
}
