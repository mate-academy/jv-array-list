package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final double GROW_FACTOR = 1.5;
    private Object[] elementsArray;
    private int size;

    public ArrayList() {
        elementsArray = new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementsArray.length) {
            grow();
        }
        elementsArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        validateIndex(index);
        growIfFull();
        System.arraycopy(elementsArray, index, elementsArray, index + 1, size - index);
        elementsArray[index] = value;
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
        validateIndex(index);
        return (T) elementsArray[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        elementsArray[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index == size - 1) {
            T temp = (T) elementsArray[index];
            elementsArray[index] = null;
            size--;
            return temp;
        } else {
            validateIndex(index);
            T elementToBeRemoved = get(index);
            regroupOnRemove(index);
            return elementToBeRemoved;
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementsArray[i]
                    || (element != null
                    && element.equals(elementsArray[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element - " + element.toString());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index - " + index);
        }
    }

    private void growIfFull() {
        if (size == elementsArray.length) {
            grow();
        }
    }

    private void grow() {
        Object[] newElementsArray = new Object[(int) (elementsArray.length * GROW_FACTOR)];
        System.arraycopy(elementsArray, 0, newElementsArray, 0, elementsArray.length);
        elementsArray = newElementsArray;
    }

    private void regroupOnRemove(int index) {
        Object[] newElementsArray = new Object[elementsArray.length];
        System.arraycopy(elementsArray, 0, newElementsArray, 0, index);
        System.arraycopy(elementsArray, index + 1, newElementsArray, index, size - index);
        elementsArray = newElementsArray;
        size--;
    }
}
