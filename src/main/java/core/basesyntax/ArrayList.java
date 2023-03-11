package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_SIZE = 10;
    private T[] elements;
    private int size;

    public ArrayList() {
        elements = (T[]) (new Object[DEFAULT_SIZE]);
    }

    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (size >= elements.length) {
            elements = getExpanded();
        }
        checkIndex(index, "You can't add an element at that index");
        insertElement(value, index);
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
        String message = "There is no element with such index";
        checkIndex(index, message);
        if (index == size) {
            throw new ArrayListIndexOutOfBoundsException(message);
        }
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        get(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        T oldValue = get(index);
        removeAndCopy(index);
        return oldValue;
    }

    @Override
    public T remove(T element) {
        return removeAndCopy(element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] getExpanded() {
        T[] expandedArray = (T[]) (new Object[elements.length * 2]);
        System.arraycopy(elements, 0, expandedArray, 0, elements.length);
        return expandedArray;
    }

    private void insertElement(T value, int index) {
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
    }

    private T removeAndCopy(T value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(value, elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("you tried to remove a non-existent element");
    }

    private void removeAndCopy(int index) {
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
    }

    private void checkIndex(int index, String excMessage) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(excMessage);
        }
    }
}
