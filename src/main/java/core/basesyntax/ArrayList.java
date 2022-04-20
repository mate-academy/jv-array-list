package core.basesyntax;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] items = (T[]) new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        grow();
        items[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        grow();
        checkIndexToBounds(index, size + 1);
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
        checkIndexToBounds(index, size);
        return items[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexToBounds(index, size);
        items[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexToBounds(index, size);
        T removedElement;
        removedElement = items[index];
        System.arraycopy(items, index + 1, items, index, items.length - index - 1);
        if (items.length > size) {
            items[size] = null;
        }
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < items.length; i++) {
            if (element == items[i] || element != null && element.equals(items[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not found in list!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        if (size == items.length) {
            T[] resizedItems = (T[]) new Object[(int) (size * 1.5)];
            System.arraycopy(items, 0, resizedItems, 0, size);
            items = resizedItems;
        }
    }

    private void checkIndexToBounds(int index, int size) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds!");
        }
    }
}
