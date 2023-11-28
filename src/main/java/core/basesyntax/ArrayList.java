package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_LENGTH = 10;
    private static final double GROW_FACTOR = 1.5;
    private int filledCount;
    private T[] items;

    public ArrayList() {
        items = (T[]) new Object[INITIAL_LENGTH];
    }

    @Override
    public void add(T value) {
        growIfNeeded();
        items[filledCount++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index != filledCount) {
            checkOutOfBoundsException(index);
        }
        growIfNeeded();
        System.arraycopy(items, index, items, index + 1, filledCount - index);
        items[index] = value;
        filledCount++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkOutOfBoundsException(index);
        return items[index];
    }

    @Override
    public void set(T value, int index) {
        checkOutOfBoundsException(index);
        items[index] = value;
    }

    @Override
    public T remove(int index) {
        checkOutOfBoundsException(index);
        final T removedItem = items[index];
        System.arraycopy(items, index + 1, items, index, filledCount - 1 - index);
        items[--filledCount] = null;
        return removedItem;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < filledCount; i++) {
            if (objectsAreEqual(items[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can't find element " + element + " in the list");
    }

    @Override
    public int size() {
        return filledCount;
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    private void checkOutOfBoundsException(int index) {
        if ((index >= filledCount) || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index + " outside of array list size (" + filledCount + ")");
        }
    }

    private void growIfNeeded() {
        if (filledCount < items.length) {
            return;
        }
        int totalCount = (int) (items.length * GROW_FACTOR);
        T[] itemsToCopy = items;
        items = (T[]) new Object[totalCount];
        System.arraycopy(itemsToCopy, 0, items, 0, itemsToCopy.length);
    }

    private static boolean objectsAreEqual(Object obj1, Object obj2) {
        return obj1 == obj2 || ((obj1 == null || obj2 == null) ? false : obj1.equals(obj2));
    }
}
