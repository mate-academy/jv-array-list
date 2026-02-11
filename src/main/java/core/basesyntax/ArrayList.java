package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int MIN_CAPACITY = 1;
    private int size = 0;
    private Object[] elementsData = new Object[DEFAULT_CAPACITY];

    private void grow() {
        int newCapacity = Math.max(MIN_CAPACITY, size + (size >> 1));
        Object[] newElementsData = new Object[newCapacity];
        System.arraycopy(elementsData,0,newElementsData,0, size);
        elementsData = newElementsData;
    }

    private void checkAddIndex(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(index, size);
        }
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(index, size);
        }
    }

    @Override
    public void add(T value) {
        if (size == elementsData.length) {
            grow();
        }
        elementsData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkAddIndex(index);
        if (size == index) {
            add(value);
            return;
        }

        if (size == elementsData.length) {
            grow();
        }
        // зсунути вправо
        System.arraycopy(
                elementsData,
                index,
                elementsData,
                index + 1,
                size - index
        );
        elementsData[index] = value;
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
        checkElementIndex(index);
        return (T)elementsData[index];
    }

    @Override
    public void set(T value, int index) {
        checkElementIndex(index);
        elementsData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkElementIndex(index);
        T removedElement = (T) elementsData[index];
        System.arraycopy(
                elementsData,
                index + 1,
                elementsData,
                index,
                size - index - 1
        );
        elementsData[--size] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (elementsData[i] == null) {
                    return remove(i);
                }
            } else {
                if (element.equals(elementsData[i])) {
                    return remove(i);
                }
            }
        }
        throw new NoSuchElementException("there is no such element in this ArrayList:" + element);
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
