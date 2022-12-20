package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private int size;
    private Object[] elementData;

    public ArrayList() {
        elementData = new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            ensureCapacity();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        indexCheck(index);
        if (size == elementData.length) {
            ensureCapacity();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        indexCheck(index);
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) {
        indexCheck(index);
        elementData[index] = value;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        indexCheck(index);
        Object removedElement = elementData[index];
        if (size - 1 - index >= 0) {
            System.arraycopy(elementData, index + 1, elementData, index, size - 1 - index);
        }
        size--;
        return (T) removedElement;
    }

    @Override
    public T remove(T element) {
        int index = indexOf(element);
        if (index >= 0) {
            System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
            size--;
            elementData[size] = null;
            return element;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void indexCheck(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("");
        }
    }

    public void ensureCapacity() {
        int newIncreasedCapacity = (int) (elementData.length * 1.5);
        elementData = Arrays.copyOf(elementData, newIncreasedCapacity);
    }

    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            //don't know how to improve
            if (element == null) {
                if (element == elementData[i]) {
                    return i;
                }
                continue;
            }
            if (element.equals(elementData[i])) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }
}
