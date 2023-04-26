package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;

    private T[] arrayLegacy;

    public ArrayList() {
        arrayLegacy = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size <= DEFAULT_CAPACITY) {
            ensureCapacity(size);
        }
        arrayLegacy[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        ensureCapacity(this.size + 1);
        System.arraycopy(this.arrayLegacy, index, arrayLegacy, index + 1, size - index - 1);
        arrayLegacy[index] = value;
        this.size++;
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
        return arrayLegacy[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        ensureCapacity(this.size + 1);
        this.arrayLegacy[index] = value;
    }

    @Override
    public T remove(int index) {
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

    private void ensureCapacity(int needCapacity) {
        if (needCapacity > arrayLegacy.length) {
            Object[] oldElements = this.arrayLegacy;
            int newSize = this.size * 2;
            this.arrayLegacy = (T[]) Arrays.copyOf(oldElements, newSize);
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= (size)) {
            throw new ArrayListIndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
    }
}
