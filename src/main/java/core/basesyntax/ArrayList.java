package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int SIZE_OF_LIST = 10;
    private int size;
    private Object[] list;

    ArrayList() {
        list = new Object[SIZE_OF_LIST];
    }

    @Override
    public void add(T value) {
        if (size >= list.length) {
            increaseCapacity();
        }
        list[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexAdd(index);
        if (size < index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Can't add element by this index");
        }
        if (size == list.length) {
            increaseCapacity();
        }
        System.arraycopy(list, index, list, index + 1, size - index);
        list[index] = value;
        size++;

    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));

        }

    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) list[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        list[index] = value;

    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);
        T removedElement = (T) list[index];
        System.arraycopy(list, index + 1, list, index, --size - index);
        list[size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {

        int index = getIndexOfElement(element);
        try {
            remove(index);
        } catch (RuntimeException e) {
            throw new NoSuchElementException(
                    "There is no such element in ArrayList, method: " + element);
        }
        return element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void increaseCapacity() {
        final double multiplication = 1.5;
        int newCapacity = (int) (list.length * multiplication);
        Object[] copiedElements = new Object[newCapacity];
        System.arraycopy(list, 0, copiedElements, 0, size);
        list = copiedElements;
    }

    private void checkIndexAdd(int index) {
        if (size < index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Incorrect index");
        }
    }

    private void checkIndex(int index) {
        if (size <= index || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Incorrect index");
        }
    }

    private int getIndexOfElement(T element) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (list[i] == element || element != null && element.equals(list[i])) {
                index = i;
                break;
            }
        }
        return index;
    }
}
