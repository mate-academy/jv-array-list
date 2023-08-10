package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_CAPACITY = 10;
    private Object[] storage = new Object[INITIAL_CAPACITY];
    private int currentTailIndex = 0;

    @Override
    public void add(T value) {
        growIfArrayFull();
        storage[currentTailIndex] = value;
        currentTailIndex++;
    }

    @Override
    public void add(T value, int index) {
        growIfArrayFull();

        if (index < 0 || index > currentTailIndex) {
            throw new ArrayListIndexOutOfBoundsException("Can't add by index " + index
                + "while current index is " + currentTailIndex);
        }

        System.arraycopy(storage, index, storage, index + 1, currentTailIndex - index);
        currentTailIndex++;
        set(value, index);
    }

    @Override
    public void addAll(List<T> list) {
        while (list.size() + currentTailIndex > storage.length) {
            grow();
        }

        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkIfIndexExists(index);

        return (T) storage[index];
    }

    @Override
    public void set(T value, int index) {
        checkIfIndexExists(index);
        storage[index] = value;
    }

    @Override
    public T remove(int index) {
        T element;

        checkIfIndexExists(index);
        growIfArrayFull();
        element = get(index);
        System.arraycopy(storage, index + 1, storage, index, currentTailIndex - index);
        currentTailIndex--;

        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < currentTailIndex; i++) {
            if ((element == null) ? (storage[i] == null) : (element.equals(storage[i]))) {
                return remove(i);
            }
        }

        throw new NoSuchElementException("Can't delete - value does not exist!");
    }

    @Override
    public int size() {
        return currentTailIndex;
    }

    @Override
    public boolean isEmpty() {
        return currentTailIndex == 0;
    }

    private void growIfArrayFull() {
        if (currentTailIndex == storage.length) {
            storage = Arrays.copyOf(storage, (int) (storage.length * 1.5));
        }
    }

    private void grow() {
        storage = Arrays.copyOf(storage, (int) (storage.length * 1.5));
    }

    private void checkIfIndexExists(int index) {
        if (index < 0 || index >= currentTailIndex) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index " + index
                    + " while current list size is " + currentTailIndex);
        }
    }
}
