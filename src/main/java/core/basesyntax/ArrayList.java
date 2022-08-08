package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int ZERO = 0;
    private Object[] elements;
    private int size;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initCapacity) {
        if (initCapacity <= ZERO) {
            throw new ArrayListIndexOutOfBoundsException("Illegal argument to create array");
        }
        elements = new Object[initCapacity];
    }

    @Override
    public void add(T value) {
        resizeIfFull();
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        resizeIfFull();
        checkIndex(index, size);
        System.arraycopy(elements, index, elements, index + 1, size - index);
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
        checkEqualsIndex(index, size);
        checkIndex(index, size);
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index, size);
        checkEqualsIndex(index, size);
        resizeIfFull();
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkEqualsIndex(index, size);
        checkIndex(index, size);
        final T removedElement = (T) elements[index];
        resizeIfFull();
        System.arraycopy(elements, index + 1, elements, index, size - index);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = 0;
        boolean noElement = false;
        for (Object s : elements) {
            if (element == null && s == null) {
                noElement = true;
                break;
            }

            if (s == null) {
                index++;
                continue;
            }

            if (s.equals(element)) {
                noElement = true;
                break;
            }
            index++;
        }
        if (!noElement) {
            throw new NoSuchElementException("There are no more elements remaining!");
        }
        checkIndex(index, size);
        resizeIfFull();
        T removedElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index);
        size--;
        return removedElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == ZERO;
    }

    private void resizeIfFull() {
        if (elements.length == size) {
            Object[] newArray = new Object[elements.length + (elements.length >> 2)];
            //arraycopy берет кусок памяти и вставляет куда мы скажем
            System.arraycopy(elements, 0, newArray, 0, size);
            elements = newArray;
        }
    }

    private void checkIndex(int index, int size) {
        if (index < ZERO || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Passed index is invalid");
        }
    }

    private void checkEqualsIndex(int index, int size) {
        if (index == size) {
            throw new ArrayListIndexOutOfBoundsException("Passed index is invalid");
        }
    }

}
