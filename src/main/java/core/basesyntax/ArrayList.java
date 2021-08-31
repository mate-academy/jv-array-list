package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final float INCREMENT_STEP = 1.5f;
    private static final int ONE_ELEMENT = 1;
    private T[] container;
    private int size;

    public ArrayList() {
        this.container = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void add(T value) {
        grow(container);
        container[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        checkIndexForAdd(index);
        grow(container);
        System.arraycopy(container, index, container, index + ONE_ELEMENT, size - index);
        container[index] = value;
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
        checkIndexForGet(index);
        return container[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndexForGet(index);
        container[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndexForGet(index);
        T removedElement = container[index];
        System.arraycopy(container, index + ONE_ELEMENT,
                container, index, size - index - ONE_ELEMENT);
        size--;
        return removedElement;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == container[i]) || (element != null && element.equals(container[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("The element is not correct!");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow(T[] oldArray) {
        if (container.length == size) {
            container = (T[]) new Object[(int) (size * INCREMENT_STEP)];
            System.arraycopy(oldArray, 0, container, 0, oldArray.length);
        }
    }

    private void checkIndexForGet(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is not correct!");
        }
    }

    private void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("The index is not correct!");
        }
    }
}
