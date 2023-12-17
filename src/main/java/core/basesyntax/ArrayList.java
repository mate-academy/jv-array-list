package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private static final double RESIZE_NUMBER = 1.5;
    private int size;
    private Object[] elementsData = new Object[DEFAULT_SIZE];

    public void resizeLength() {
        if (size == elementsData.length) {
            Object[] biggerArray = new Object[(int) (elementsData.length * RESIZE_NUMBER)];
            System.arraycopy(elementsData, 0, biggerArray, 0, size);
            elementsData = biggerArray;
        }
    }

    @Override
    public void add(T value) {
        resizeLength();
        elementsData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        resizeLength();
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index isn't correct");
        } else {
            System.arraycopy(elementsData, index, elementsData, index + 1, size - index);
            elementsData[index] = value;
            size++;
        }
    }

    @Override
    public void addAll(List<T> list) {
        Object[] compliet = new Object[size + list.size()];
        System.arraycopy(elementsData, 0, compliet, 0, size);
        for (int i = 0; i < list.size(); i++) {
            compliet[size + i] = list.get(i);
        }
        size = compliet.length;
        elementsData = compliet;
    }

    @Override
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("This index is more "
                    + "than size of ArrayList");
        }
        return (T) elementsData[index];
    }

    @Override
    public void set(T value, int index) {
        if (index > size - 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("This index is more "
                    + "than size of ArrayList");
        }
        elementsData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("This index is more "
                    + "than size of ArrayList");
        } else {
            T removedElements = (T) elementsData[index];
            System.arraycopy(elementsData, index + 1, elementsData, index, size - index - 1);
            size--;
            return removedElements;
        }
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementsData[i]
                    || (element != null && element.equals(elementsData[i]))) {
                T removedElements = (T) elementsData[i];
                System.arraycopy(elementsData, i + 1, elementsData, i, size - i - 1);
                size--;
                return removedElements;
            }
        }
        throw new NoSuchElementException();
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
