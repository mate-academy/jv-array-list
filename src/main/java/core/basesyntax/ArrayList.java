package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEF_ARRAY_LENGTH = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) new Object[DEF_ARRAY_LENGTH];
    }

    @Override
    public void add(T value) {
        increaseSize();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndexOfArray(index);
        increaseSize();
        System.arraycopy(elements, index, elements, (index + 1), (size - index));
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
        checkIndexOfArray(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexOfArray(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexOfArray(index);
        T removedValue = elements[index];
        deleteElementInArray(index);
        return removedValue;
    }

    @Override
    public T remove(T element) {
        T rightElement;
        for (int i = 0; i < elements.length; i++) {
            T currentElement = elements[i];
            if (compareElements(currentElement, element)) {
                rightElement = element;
                deleteElementInArray(i);
                return rightElement;
            }
        }
        throw new NoSuchElementException("Can't find element: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0) ? true : false;
    }

    private void growSize() {
        int currentLength = elements.length;
        int newArrayLength = currentLength + (currentLength >> 1);
        T[] newArray = (T[]) new Object[newArrayLength];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }

    private void checkIndexOfArray(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index does not exist: " + index);
        }
    }

    private void deleteElementInArray(int index) {
        System.arraycopy(elements, index + 1, elements, index, elements.length - index - 1);
        size--;
    }

    private void increaseSize() {
        if (size == elements.length) {
            growSize();
        }
    }

    private boolean compareElements(T elementOne, T elementTwo) {
        return elementOne == elementTwo || elementOne != null && elementOne.equals(elementTwo);
    }
}
