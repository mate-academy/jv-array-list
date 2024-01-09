package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double GROW_FACTOR = 1.5;
    private int size;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index can't be higher "
                    + "than size or negative");
        }
        if (size == elementData.length) {
            elementData = grow();
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null) {
            throw new NullPointerException("Can't add null List to ArrayList");
        }
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkBound(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkBound(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        checkBound(index);
        T removeElement = elementData[index];
        int startIndex = index;
        int elementsToMove = size - index - 1;
        System.arraycopy(elementData, startIndex + 1, elementData, startIndex, elementsToMove);
        size--;
        return removeElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == elementData[i] || element != null && element.equals(elementData[i])) {
                T removeElement = elementData[i];
                int startIndex = i;
                int elementsToMove = size - i - 1;
                System.arraycopy(elementData, startIndex + 1, elementData,
                        startIndex, elementsToMove);
                size--;
                return removeElement;
            }
        }
        throw new NoSuchElementException("Can't find element for remove :" + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean checkBound(int index) {
        if (index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index can't be negative :" + index);
        }
        if (index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Badinput index: " + index
                    + "for size: " + size);
        }
        return true;
    }

    private T[] grow() {
        int oldCapacity = elementData.length;
        int newCapacity = (int) (oldCapacity * GROW_FACTOR);
        T[] newElementData = (T[]) new Object[newCapacity];
        System.arraycopy(elementData, 0, newElementData, 0, elementData.length);
        return elementData = newElementData;
    }

    private T[] grow(int capacity) {
        return elementData = Arrays.copyOf(elementData, elementData.length + capacity);
    }

}
