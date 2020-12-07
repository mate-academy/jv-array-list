package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_DATA = {};
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private T[] elements;
    private int size;

    public ArrayList() {
        this.elements = (T[]) new Object[DEFAULT_CAPACITY];
        this.size = ZERO;
    }

    @Override
    public void add(T value) {
        if (elements.length == size) {
            grow(size + ONE);
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        if (elements.length == size) {
            grow(size + ONE);
        }
        System.arraycopy(elements, index, elements, index + ONE, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        T[] listElements = (T[]) new Object[list.size()];
        for (int i = ZERO; i < list.size(); i++) {
            listElements[i] = list.get(i);
        }
        System.arraycopy(listElements, ZERO, elements, size, listElements.length);
        size += listElements.length;
        ;
    }

    @Override
    public T get(int index) {
        rangeCheckForGet(index);
        return elements[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException("Insert impossible");
        }
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        rangeCheckForAdd(index);
        T oldValue = elements[index];
        T[] newElements = elements;
        fastRemove(newElements, index);
        return oldValue;
    }

    @Override
    public T remove(T t) {
        for (int i = ZERO; i < size; i++) {
            if (t == null) {
                size--;
                return null;
            }
            if (t.equals(elements[i])) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Item not found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size == ZERO) {
            return true;
        }
        return false;
    }

    private T[] grow(int minCapacity) {
        int oldCapacity = elements.length;
        if (oldCapacity > ZERO || elements != EMPTY_DATA) {
            int newCapacity = oldCapacity + (oldCapacity >> ONE);
            T[] newElements = (T[]) new Object[size];
            System.arraycopy(elements, ZERO, newElements, ZERO, size);
            elements = (T[]) new Object[newCapacity];
            System.arraycopy(newElements, ZERO, elements, ZERO, size);
        } else {
            return elements = (T[]) new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
        return elements;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < ZERO) {
            throw new ArrayIndexOutOfBoundsException("Your index is more than size "
                    + "or index is less than 0");
        }
    }

    private void rangeCheckForGet(int index) {
        if (index >= size || index < ZERO) {
            throw new ArrayIndexOutOfBoundsException("Your index is more than size "
                    + "or index is less than 0");
        }
    }

    private void fastRemove(T[] array, int index) {
        if (--size > index) {
            System.arraycopy(array, index + ONE, array, index, size - index);
        }
        rangeCheckForAdd(size);
        array[size] = null;
    }
}
