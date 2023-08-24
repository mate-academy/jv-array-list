package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int START_SIZE = 10;
    private int cursor;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[START_SIZE];
    }

    @Override
    public void add(T value) {
        if (elements.length == cursor) {
            resize();
        }
        elements[cursor] = value;
        cursor++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > cursor) {
            throw new ArrayListIndexOutOfBoundsException("Index ["
                    + index + "] is out of List range");
        }
        if (elements.length == cursor) {
            resize();
        }
        System.arraycopy(elements, index, elements, index + 1, cursor - index);
        elements[index] = value;
        cursor++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > cursor - 1) {
            throw new ArrayListIndexOutOfBoundsException("Index ["
                    + index + "] is out of List range");
        }
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index > cursor - 1) {
            throw new ArrayListIndexOutOfBoundsException("Index ["
                    + index + "] is out of List range");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > cursor - 1) {
            throw new ArrayListIndexOutOfBoundsException("Index ["
                    + index + "] is out of List range");
        }
        T removedElement = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, cursor - index - 1);
        cursor--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = 0;
        for (T elem : elements) {
            if (elem == element || elem != null && elem.equals(element)) {
                return remove(index);
            }
            index++;
        }
        throw new NoSuchElementException("The List doesn't contain such element " + element);
    }

    @Override
    public int size() {
        return cursor;
    }

    @Override
    public boolean isEmpty() {
        return cursor == 0;
    }

    public void resize() {
        int tempCursor = (int) (cursor * 1.5);
        T[] tempArray = (T[])new Object[tempCursor];
        System.arraycopy(elements, 0, tempArray, 0, cursor);
        elements = tempArray;
    }
}
