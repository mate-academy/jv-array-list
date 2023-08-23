package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final double GROW_COEFFICIENT = 0.75;
    private static final byte DEFAULT_SIZE = 10;
    private int size = DEFAULT_SIZE;
    private int currentIndex = 0;
    private T[] storage;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        storage = (T[]) new Object[DEFAULT_SIZE];
    }

    @Override
    public void add(T value) {
        shouldGrow();
        storage[currentIndex++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index > currentIndex || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Index: %d, Size: %d", index, currentIndex)
            );
        }
        shouldGrow();

        System.arraycopy(storage, index, storage, index + 1, currentIndex - index);
        storage[index] = value;
        currentIndex++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int j = 0; j < list.size(); j++) {
            add(list.get(j));
        }
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return storage[index];
    }

    @Override
    public void set(T value, int index) {
        validateIndex(index);
        storage[index] = value;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T removedElement = storage[index];
        System.arraycopy(storage, index + 1, storage, index, --currentIndex - index);
        storage[currentIndex] = null;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        int index = -1;
        for (int i = 0; i < currentIndex; i++) {
            if ((element == null && storage[i] == null)
                    || (element != null && element.equals(storage[i]))) {
                index = i;
            }
        }

        if (index == -1) {
            throw new NoSuchElementException("Element was not found in list");
        }

        return remove(index);
    }

    @Override
    public int size() {
        return currentIndex;
    }

    @Override
    public boolean isEmpty() {
        return currentIndex == 0;
    }

    @SuppressWarnings("unchecked")
    private void shouldGrow() {
        if (currentIndex == size) {
            int newSize = (int) (storage.length + storage.length * GROW_COEFFICIENT);
            Object[] tempArray = new Object[newSize];
            System.arraycopy(storage, 0, tempArray, 0, size);
            storage = (T[]) tempArray;
            size = newSize;
        }
    }

    private void validateIndex(int index) {
        if (index >= currentIndex || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    String.format("Index: %d, Size: %d", currentIndex, size)
            );
        }
    }
}
