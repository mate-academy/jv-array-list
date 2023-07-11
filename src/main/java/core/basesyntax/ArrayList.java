package core.basesyntax;

import java.util.NoSuchElementException;
import org.apache.commons.lang.ObjectUtils;

public class ArrayList<T> implements List<T> {
    private static int capacity = 10;
    private static final double RESIZE_FACTOR = 1.5;
    private int size;
    private T[] elements;

    public ArrayList() {
        elements = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        checkSize();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        checkSize();
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
        checkAddIndex(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkAddIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkAddIndex(index);
        int updateSize = size - 1;
        final T removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, updateSize - index);
        elements[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        remove(getIndexByElement(element));
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

    private void checkSize() {
        if (size == capacity) {
            grow();
        }
    }

    private int getIndexByElement(T element) {
        for (int i = 0; i < elements.length; i++) {
            if (ObjectUtils.equals(elements[i], element)) {
                return i;
            }
        }
        throw new NoSuchElementException("Can't remove element " + element);
    }

    private void checkAddIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(String.format("Out of bound exception."
                    + " %s is not included in the length %s", index, size()));
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Out of bound exception."
                            + " %s is not included in the length %s", index, size()));
        }
    }

    private void grow() {
        capacity *= RESIZE_FACTOR;
        T[] copy = elements;
        elements = (T[]) new Object[capacity];
        System.arraycopy(copy, 0, elements, 0, size);
    }
}
