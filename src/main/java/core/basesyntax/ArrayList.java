package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] items;
    private int size;

    public ArrayList() {
        this.items = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        if (size == items.length) {
            items = increaseCapacity();
        }

        items[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);

        if (size == items.length) {
            items = increaseCapacity();
        }

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
        checkIndex(index);

        return getElementByIndex(index);
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        items[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        T oldValue = getElementByIndex(index);

        int numMoved = size - index - 1;

        System.arraycopy(items, index + 1, items, index, numMoved);

        size--;

        return oldValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            T elementByIndex = getElementByIndex(i);

            if ((element == elementByIndex)
                    || (element != null && element.equals(elementByIndex))) {
                return remove(i);
            }
        }

        throw new NoSuchElementException("Element doesn't exist");
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
            throw new ArrayListIndexOutOfBoundsException("Index doesn't exist");
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index doesn't exist");
        }
    }

    private Object[] increaseCapacity() {
        int oldCapacity = items.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);

        return Arrays.copyOf(items, newCapacity);
    }

    private T getElementByIndex(int index) {
        return (T) items[index];
    }
}
