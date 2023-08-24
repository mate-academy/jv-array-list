package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int SIZE_OF_LIST = 10;
    private static double MULTYPLYCATION = 1.5;
    private int size;
    private Object[] list;

    public ArrayList() {
        list = new Object[SIZE_OF_LIST];
    }

    @Override
    public void add(T value) {
        if (size >= list.length) {
            increaseCapacity();
        }
        list[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndexAdd(index);
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
        int newCapacity = (int) (list.length * MULTYPLYCATION);
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
                    "Incorrect index" + index);
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
