package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_LIST_SIZE = 10;
    private static final int FIRST_ELEMENT = 0;
    private static final double LIST_GROW_COEFFICIENT = 1.5;
    private int size;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[INITIAL_LIST_SIZE];
    }

    @Override
    public void add(T value) {
        if (size >= elementData.length) {
            grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (checkAddIndex(index)) {
            if (size >= elementData.length) {
                grow();
            }
            System.arraycopy(elementData, index, elementData, index + 1, size - index);
            elementData[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        if (size + list.size() >= elementData.length) {
            grow();
        }
        for (int i = 0; i < list.size(); i++) {
            elementData[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        if (checkNotAddIndex(index)) {
            return elementData[index];
        }
        return null;
    }

    @Override
    public void set(T value, int index) {
        if (checkNotAddIndex(index)) {
            elementData[index] = value;
        }
    }

    @Override
    public T remove(int index) {
        if (checkNotAddIndex(index)) {
            T result = elementData[index];
            removingResize(index);
            size--;
            return result;
        }
        return null;
    }

    @Override
    public T remove(T element) {
        if (checkElement(element)) {
            removingResize(getIndex(element));
            size--;
            return element;
        }
        return null;
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
        T[] temporaryCopy = elementData;
        elementData = (T[]) new Object[(int) (elementData.length * LIST_GROW_COEFFICIENT)];
        System.arraycopy(temporaryCopy, FIRST_ELEMENT, elementData, FIRST_ELEMENT,
                temporaryCopy.length);
    }

    private boolean checkAddIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is greater than List length");
        }
        return index <= size;
    }

    private boolean checkNotAddIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is greater than List size");
        }
        return index < size;
    }

    private boolean checkElement(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == element || elementData[i] != null
                    && elementData[i].equals(element)) {
                return true;
            }
        }
        throw new NoSuchElementException("No such element in the list");
    }

    private int getIndex(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if (elementData[i] == element || elementData[i] != null
                    && elementData[i].equals(element)) {
                return i;
            }
        }
        throw new NoSuchElementException("No such element in the list");
    }

    private void removingResize(int indexOfMovedElement) {
        if (indexOfMovedElement == elementData.length - 1) {
            elementData[indexOfMovedElement] = null;
            return;
        }
        System.arraycopy(elementData, indexOfMovedElement + 1, elementData, indexOfMovedElement,
                    size - indexOfMovedElement);
    }
}
