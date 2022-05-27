package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index passed to the method is negative or "
                            + "bigger than size of ArrayList. "
                            + "Index: " + index + ", Size: " + size);
        }
        int s;
        Object[] elementData;
        if ((s = size) == (elementData = this.elements).length) {
            elementData = grow();
        }
        if (index == 0) {
            System.arraycopy(elementData, 0, elementData, 1, s);
        } else {
            System.arraycopy(elementData, 0, elementData, 0, index);
            System.arraycopy(elementData, index, elementData, index + 1, s - index);
        }
        elementData[index] = value;
        size = s + 1;
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
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removedElement = (T) elements[index];
        int numberOfElements = size - index - 1;
        System.arraycopy(elements, index + 1, elements, index, numberOfElements);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        Object[] es = elements;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, es[i])
                    || (element != null && element.equals(es[i]))) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("There is no such element.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] grow() {
        int oldCapacity = elements.length;
        if (oldCapacity > 0) {
            int newCapacity = (int) (oldCapacity * 1.5);
            return elements = Arrays.copyOf(elements, newCapacity);
        }
        return elements = new Object[DEFAULT_CAPACITY];
    }

    private void checkIndex(int index) {
        int localSize = (size == 0) ? 0 : (size - 1);
        if (index > localSize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index passed to the method is negative or "
                            + "bigger than size of ArrayList. "
                            + "Index: " + index + ", Size: " + size);
        }
    }
}
