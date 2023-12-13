package core.basesyntax;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int BEGINNING_CAPACITY = 10;
    private static final double GROWTH_VALUE = 1.5;
    private T[] arrayOfElements;
    private int arraySize;

    public ArrayList() {
        arrayOfElements = (T[]) new Object[BEGINNING_CAPACITY];
    }

    @Override
    public void add(T value) {
        resize();
        arrayOfElements[arraySize++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexToAdd(index);
        resize();
        if (index != arraySize) {
            System.arraycopy(arrayOfElements, index, arrayOfElements, index + 1, arraySize - index);
        }
        arrayOfElements[index] = value;
        arraySize++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index == arraySize) {
            throw new ArrayListIndexOutOfBoundsException("Your index is out of bounds " + index);
        } else {
            checkIndexToAdd(index);
        }

        return arrayOfElements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index == arraySize) {
            throw new ArrayListIndexOutOfBoundsException("Your index is out of bounds " + index);
        } else {
            checkIndexToAdd(index);
        }

        arrayOfElements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index == arraySize) {
            throw new ArrayListIndexOutOfBoundsException("Your index is out of bounds " + index);
        } else {
            checkIndexToAdd(index);
        }

        T elementToRemove = arrayOfElements[index];
        System.arraycopy(arrayOfElements, index + 1, arrayOfElements, index, arraySize - index - 1);
        arraySize--;
        return elementToRemove;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < arraySize; i++) {
            if (isEqual(arrayOfElements[i], element)) {
                return remove(i);
            }
        }

        throw new NoSuchElementException("Can't delete this element, because it does not exist.");
    }

    @Override
    public int size() {
        return arraySize;
    }

    @Override
    public boolean isEmpty() {
        return arraySize == 0;
    }

    public boolean isEqual(T firstElement, T secondElement) {
        return firstElement != null
                && firstElement.equals(secondElement)
                || secondElement == firstElement;
    }

    public void increase() {
        int newCapacity = (int) (GROWTH_VALUE * arraySize);
        T[] newArrayOfElements = (T[]) new Object[newCapacity];
        System.arraycopy(arrayOfElements, 0, newArrayOfElements, 0, arraySize);
        arrayOfElements = newArrayOfElements;
    }

    private void resize() {
        if (arrayOfElements.length == arraySize) {
            increase();
        }
    }

    private void checkIndexToAdd(int index) {
        if ((index > arraySize) || (index < 0)) {
            throw new ArrayListIndexOutOfBoundsException("Your index is out of bounds " + index);
        }
    }
}
