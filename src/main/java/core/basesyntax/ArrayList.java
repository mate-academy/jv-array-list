package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double GROW_FACTOR = 1.5;
    private int size;
    private T[] elementsData;

    public ArrayList() {
        elementsData = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        resizeLength();
        elementsData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        resizeLength();
        checkIndex(index);
        System.arraycopy(elementsData, index, elementsData, index + 1, size - index);
        elementsData[index] = value;
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
        checkIndexRemove(index);
        return (T) elementsData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexRemove(index);
        elementsData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexRemove(index);
        T removedElements = (T) elementsData[index];
        System.arraycopy(elementsData, index + 1, elementsData, index, size - index - 1);
        size--;
        return removedElements;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementsData[i]
                    || element != null && element.equals(elementsData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Couldn't find this element: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeLength() {
        if (size == elementsData.length) {
            T[] biggerArray = (T[]) new Object[(int) (elementsData.length * GROW_FACTOR)];
            System.arraycopy(elementsData, 0, biggerArray, 0, size);
            elementsData = biggerArray;
        }
    }

    private void checkIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("This index: " + index + " is more "
                    + "than size: " + size + "` of ArrayList");
        }
    }

    private void checkIndexRemove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("This index: " + index + " is more "
                    + "than size: " + size + "` of ArrayList");
        }
    }
}