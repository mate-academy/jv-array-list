package core.basesyntax;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T>, Iterable {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double RESIZE_CONSTANT = 1.5;
    private static final int EMPTY_SIZE = 0;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfFull();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        indexOutOfBoundCheck(index);
        growIfFull();
        System.arraycopy(elementData, index,
                elementData, index + 1,
                size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (Object element : list) {
            if (element != null) {
                growIfFull();
                add((T) element);
            }
        }
    }

    @Override
    public T get(int index) {
        indexOutOfBoundCheck(index + 1);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexOutOfBoundCheck(index + 1);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        indexOutOfBoundCheck(index + 1);
        final T elementToRemove = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        size--;
        elementData[size] = null;
        return elementToRemove;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < elementData.length; i++) {
            if (element == elementData[i] || element != null && element.equals(elementData[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " doesn't exist");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == EMPTY_SIZE;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int currentIndex = 0;
            @Override
            public boolean hasNext() {
                return currentIndex < size && elementData[currentIndex] != null;
            }

            @Override
            public T next() {
                return elementData[currentIndex++];
            }
        };
    }

    private void indexOutOfBoundCheck(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index " + index
                    + " is out of bounds " + size);
        }
    }

    private void growIfFull() {
        if (elementData.length == size) {
            int newCapacity = (int) (elementData.length * RESIZE_CONSTANT);
            Object[] oldElementData = elementData;
            elementData = (T[])new Object[newCapacity];
            System.arraycopy(oldElementData, 0, elementData, 0, size);
        }
    }
}
