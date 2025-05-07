package core.basesyntax;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_COEFFICIENT = 1.5;
    private T[] elementData;
    private int size;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growIfArrayFull();
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index,true);
        growIfArrayFull();
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (T element : list) {
            add(element);
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index, false);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index,false);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index,false);
        T removeElement = elementData[index];
        size--;
        System.arraycopy(elementData,index + 1,elementData,index,size - index);
        return removeElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element != null && element.equals(elementData[i]) || element == elementData[i]) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element " + element + " not found.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growIfArrayFull() {
        if (size == elementData.length) {
            int newCapacity = (int) (size * GROW_COEFFICIENT);
            T[] newElements = (T[]) new Object[newCapacity];
            System.arraycopy(elementData, 0, newElements, 0, elementData.length);
            elementData = newElements;
        }
    }

    private void checkIndex(int index, boolean checkCondition) {
        if (index < 0 || (checkCondition ? index > size : index >= size)) {
            throw new ArrayListIndexOutOfBoundsException("Index is invalid: " + index);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int position = 0;

            @Override
            public boolean hasNext() {
                return size > position;
            }

            @Override
            public T next() {
                T value = elementData[position];
                position++;
                return value;
            }
        };
    }
}
