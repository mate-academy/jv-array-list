package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private static final int GROW_FACTORY = DEFAULT_CAPACITY >> 1;
    private int size;
    private T[] arrayLegacy;

    public ArrayList() {
        arrayLegacy = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        growCapacity();
        arrayLegacy[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        growCapacity();
        System.arraycopy(arrayLegacy, index, arrayLegacy, index + 1, size - index);
        arrayLegacy[index] = value;
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
        checkIndexEqualsSize(index);
        checkIndex(index);
        return arrayLegacy[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexEqualsSize(index);
        checkIndex(index);
        growCapacity();
        arrayLegacy[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexEqualsSize(index);
        checkIndex(index);
        T removedElement = arrayLegacy[index];
        for (int i = index; i < size - 1; i++) {
            arrayLegacy[i] = arrayLegacy[i + 1];
        }
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == arrayLegacy[i] || arrayLegacy[i] != null
                    && arrayLegacy[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element" + element + " is absent in the list.");
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void growCapacity() {
        if (arrayLegacy.length == size) {
            T[] newArrays = (T[]) new Object[(int) (arrayLegacy.length + GROW_FACTORY)];
            System.arraycopy(arrayLegacy, 0, newArrays, 0, arrayLegacy.length);
            arrayLegacy = newArrays;
        }
    }

    private void checkIndex(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is wrong: " + index);
        }
    }

    private void checkIndexEqualsSize(int index) {
        if (index == size) {
            throw new ArrayListIndexOutOfBoundsException("Index is wrong: " + index);
        }
    }
}
