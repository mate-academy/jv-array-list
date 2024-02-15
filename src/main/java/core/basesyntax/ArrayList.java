package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private static final int DEFAULT_MIN_INCREMENT = 1;
    private int size;
    private T[] elementData;

    public ArrayList() {
        elementData = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        validateAddIndex(index);
        if (index == size) {
            add(value);
            return;
        }
        ensureCapacity();
        System.arraycopy(elementData, index, elementData, index + 1, size);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        ensureCapacity(list.size());
        for (int i = 0; i < list.size(); i++) {
            /*add() is not used to avoid calling ensureCapacity() in every iteration*/
            elementData[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        validateGetRemoveSetIndex(index);
        return elementData[index];
    }

    @Override
    public void set(T value, int index) {
        validateGetRemoveSetIndex(index);
        elementData[index] = value;

    }

    @Override
    public T remove(int index) {
        validateGetRemoveSetIndex(index);
        T removedElement = elementData[index];

        if (index == size - 1) {
            elementData[index] = null;
            size--;
            return removedElement;
        }
        System.arraycopy(elementData, index + 1, elementData, index, size);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] != null && elementData[i].equals(element)
                    || elementData[i] == element) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found in list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow(int neededCapacity) {
        neededCapacity += elementData.length;
        int newCapacity = elementData.length;
        do {
            newCapacity *= 1.5;
        } while (neededCapacity > newCapacity);
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private void ensureCapacity() {
        if (size == elementData.length) {
            grow(DEFAULT_MIN_INCREMENT);
        }
    }

    private void ensureCapacity(int size) {
        if (this.size + size >= elementData.length) {
            grow(size - (elementData.length - this.size));
        }
    }

    private void validateAddIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }

    private void validateGetRemoveSetIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
    }
}
