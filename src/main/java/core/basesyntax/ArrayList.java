package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double MAX_CAPACITY_RAISE_INDEX = 1.5;

    private T[] elementData;
    private int size;
    private int currentMaxCapacity = 10;

    public ArrayList() {
        elementData = (T[]) new Object[currentMaxCapacity];
    }

    @Override
    public void add(T value) {
        if (size + 1 <= elementData.length) {
            elementData[size] = value;
        } else {
            grow();
            elementData[size] = value;
        }
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Array List shorter then you think");
        }
        if (size + 1 > elementData.length) {
            grow();
        }
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        if (size + list.size() > elementData.length) {
            do {
                currentMaxCapacity *= MAX_CAPACITY_RAISE_INDEX;
            } while (size + list.size() >= currentMaxCapacity);
            elementData = Arrays.copyOf(elementData, currentMaxCapacity);
        }
        for (int i = size; i < (size + list.size()); i++) {
            elementData[i] = list.get(i - size);
        }
        size += list.size();
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        T removedObject;
        checkIndex(index);
        removedObject = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - 1 - index);
        elementData[size - 1] = null;
        size--;
        return removedObject;
    }

    @Override
    public T remove(T element) throws NoSuchElementException {
        for (int i = 0; i < size; i++) {
            if ((elementData[i] != null && elementData[i].equals(element))
                    || (elementData[i] == null && element == null)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("NoSuchElementException");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Array List shorter then you think");
        }
    }

    private T[] grow() {
        return elementData = Arrays.copyOf(elementData,
                (int) (elementData.length * MAX_CAPACITY_RAISE_INDEX));
    }
}
