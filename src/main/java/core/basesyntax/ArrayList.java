package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int INITIAL_LENGTH = 10;
    private int totalCount;
    private int filledCount;
    private Object[] items;

    public ArrayList() {
        items = new Object[INITIAL_LENGTH];
        totalCount = items.length;
        filledCount = 0;
    }

    @Override
    public void add(T value) {
        if (isFull()) {
            grow();
        }
        items[filledCount] = value;
        filledCount += 1;
    }

    @Override
    public void add(T value, int index) {
        if (index != filledCount) {
            checkOutOfBoundsException(index);
        }

        if (isFull()) {
            grow();
        }
        for (int i = filledCount; i >= index; i--) {
            items[i] = (i == index) ? value : items[i - 1];
        }
        filledCount += 1;
    }

    @Override
    public void addAll(List<T> list) {
        int newSize = filledCount + list.size();
        while (newSize > totalCount) {
            grow();
        }
        for (int i = filledCount; i < newSize; i++) {
            items[i] = list.get(i - filledCount);
        }
        filledCount = newSize;
    }

    @Override
    public T get(int index) {
        checkOutOfBoundsException(index);
        return (T) items[index];
    }

    @Override
    public void set(T value, int index) {
        checkOutOfBoundsException(index);
        items[index] = value;
    }

    @Override
    public T remove(int index) {
        checkOutOfBoundsException(index);
        Object removedItem = items[index];
        for (int i = index; i < filledCount; i++) {
            items[i] = (i == filledCount - 1) ? null : items[i + 1];
        }
        filledCount -= 1;
        return (T) removedItem;
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
        return this.filledCount;
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    private boolean isFull() {
        return (filledCount == totalCount);
    }

    private void checkOutOfBoundsException(int index) {
        if ((index >= filledCount) || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Index " + index + " outside of array list size (" + filledCount + ")");
        }
    }

    private void grow() {
        totalCount = (int) (totalCount * 1.5);
        Object[] itemsToCopy = items;
        items = new Object[totalCount];
        System.arraycopy(itemsToCopy, 0, items, 0, itemsToCopy.length);
    }

    private static boolean objectsAreEqual(Object object1, Object object2) {

        if (object1 == object2) {
            return true;
        }

        if (object1 == null || object2 == null) {
            return false;
        }

        return object1.equals(object2);

    }
}
