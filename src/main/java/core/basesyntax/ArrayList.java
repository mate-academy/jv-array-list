package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[10];
        size = 0;
    }

    @Override
    public void add(T value) {
        grow();
        elements[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > this.size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can`t add element with index: " + index);
        }
        grow();
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
        if (index >= this.size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can`t get element with index: " + index);
        }
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= this.size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can`t set element with index: " + index);
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= this.size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can`t remove element with index: "
                    + index);
        }
        T out = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, --size - index);
        return out;
    }

    @Override
    public T remove(T element) {
        int index = findIndexByElement(element);
        if (index == -1) {
            throw new NoSuchElementException("Element not exist in list");
        }
        return remove(index);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        if (size + 1 > elements.length) {
            Object[] newElement = new Object[elements.length + (elements.length >> 1)];
            System.arraycopy(elements, 0, newElement, 0, elements.length);
            elements = newElement;
        }
    }

    private int findIndexByElement(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, elements[i])) {
                return i;
            }
        }
        return -1;
    }
}
