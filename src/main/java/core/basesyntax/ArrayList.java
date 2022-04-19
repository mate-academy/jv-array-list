package core.basesyntax;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final String INDEX_EXCEPTION_TEXT = "Index is out of bounds!";
    private static final String NO_SUCH_ELEMENT_EXCEPTION = "Element not found in list!";

    private T[] items = (T[]) new Object[DEFAULT_CAPACITY];
    private int size;

    @Override
    public void add(T value) {
        if (size == items.length) {
            items = grow();
        }
        items[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (size == items.length) {
            items = grow();
        }
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of bounds!");
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
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_EXCEPTION_TEXT);
        }
        return items[index];
    }

    @Override
    public void set(T value, int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_EXCEPTION_TEXT);
        }
        items[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException(INDEX_EXCEPTION_TEXT);
        }
        T[] modifyItems = (T[]) new Object[size];
        if (index + 1 != size) {
            System.arraycopy(items, 0, modifyItems, 0, index);
            System.arraycopy(items, index + 1, modifyItems, index, size - index);
        }
        T removedElement = items[index];
        items = modifyItems;
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
        throw new NoSuchElementException(NO_SUCH_ELEMENT_EXCEPTION);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T[] grow() {
        T[] resizedItems = (T[]) new Object[(int) (size * 1.5)];
        System.arraycopy(items,0, resizedItems, 0, size);
        return resizedItems;
    }
}
