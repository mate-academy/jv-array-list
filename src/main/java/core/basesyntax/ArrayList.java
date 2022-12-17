package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double ARRAY_GROW = 0.5;
    private T[] collectionOfElement;
    private int size;

    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            collectionOfElement = (T[]) new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("The size is incorrect");
        }
    }

    public ArrayList() {
        collectionOfElement = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow();
        collectionOfElement[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index == size) {
            add(value);
            return;
        }
        checkIndex(index);
        grow();
        System.arraycopy(collectionOfElement, index, collectionOfElement, index + 1, size - index);
        collectionOfElement[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    private void grow() {
        if (size == collectionOfElement.length) {
            T[] newCollectionOfElement = (T[]) new Object[(int) (collectionOfElement.length
                    + collectionOfElement.length * ARRAY_GROW)];
            System.arraycopy(collectionOfElement, 0, newCollectionOfElement, 0, size);
            collectionOfElement = newCollectionOfElement;
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return collectionOfElement[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        collectionOfElement[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T oldElement = collectionOfElement[index];
        System.arraycopy(collectionOfElement, index + 1, collectionOfElement, index,
                size - index - 1);
        size--;
        return oldElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size(); i++) {
            if (Objects.equals(element, collectionOfElement[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("This collection doesn't have element");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("This index is incorrect");
        }
    }
}
