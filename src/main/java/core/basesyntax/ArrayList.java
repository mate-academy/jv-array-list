package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_LENGTH = 10;
    private static final double GROW_FACTOR = 1.5;
    private int size;
    private T[] items;

    public ArrayList() {
        items = (T[]) new Object[INITIAL_LENGTH];
    }

    @Override
    public void add(T value) {
        growIfNeeded();
        items[size++] = value;
    }

    @Override
    public void add(T value, int index) {
        if (index != size) {
            checkOutOfBoundsException(index);
        }
        growIfNeeded();
        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = value;
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
        System.arraycopy(items, index + 1, items, index, size - 1 - index);
        items[--size] = null;
        return removedItem;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (objectsAreEqual(items[i], element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Can't find element " + element + " in the list");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    private void checkOutOfBoundsException(int index) {
        if ((index >= size) || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index + " outside of array list size (" + size + ")");
        }
    }

    private void growIfNeeded() {
        if (size < items.length) {
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
