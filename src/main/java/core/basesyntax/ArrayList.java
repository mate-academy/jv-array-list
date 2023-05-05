package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double RESIZE = 1.5;
    private Object[] elementsData;
    private int size;

    public ArrayList() {
        this.elementsData = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        checkGrow();
        elementsData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkGrow();
        checkIndexAdd(index);
        System.arraycopy(elementsData, index, elementsData, index + 1, size - index);
        elementsData[index] = value;
        size++;
    }

    public void checkIndexGet(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index: " + index);
        }
    }

    public void checkIndexAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Wrong index: " + index);
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIndexGet(index);
        return (T) elementsData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexGet(index);
        elementsData[index] = value;
    }

    public void checkGrow() {
        if (elementsData.length == size) {
            growIfArrayFull();
        }
    }

    public void growIfArrayFull() {
        int currentCapacity = elementsData.length;
        int newCapacity = (int) Math.round(currentCapacity * RESIZE);
        elementsData = Arrays.copyOf(elementsData, newCapacity);
    }

    @Override
    public T remove(int index) {
        checkIndexGet(index);
        T removedElement = removeElement(index);
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, elementsData[i])) {
                return removeElement(i);
            }
        }
        throw new NoSuchElementException("There is no element as: " + element);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public T removeElement(int index) {
        T removedElement = (T) elementsData[index];
        System.arraycopy(elementsData, index + 1, elementsData, index, size - index - 1);
        size--;
        return removedElement;
    }
}
