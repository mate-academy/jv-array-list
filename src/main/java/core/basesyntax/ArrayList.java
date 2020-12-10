package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int LENGTH = 10;

    private T[] defaultArray;
    private int size;

    public ArrayList() {
        defaultArray = (T[]) new Object[LENGTH];
        size = 0;
    }

    @Override
    public void add(T value) {
        checkCapacity();
        defaultArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Wrong index: index out of bound");
        }
        checkCapacity();
        System.arraycopy(defaultArray, index, defaultArray, index + 1, size - index);
        defaultArray[index] = value;
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
        return defaultArray[index];
    }

    @Override
    public void set(T value, int index) {
        checkIndex(index);
        defaultArray[index] = value;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removeElement = defaultArray[index];
        System.arraycopy(defaultArray, index + 1, defaultArray, index, size - index - 1);
        defaultArray [--size] = null;
        return removeElement;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if ((t == defaultArray[i]) || (t != null) && (t.equals(defaultArray[i]))) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("This element don't exist in this collection");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkCapacity() {
        if (size >= defaultArray.length) {
            T[] largeArray = (T[]) new Object [(int)(defaultArray.length * 1.5)];
            System.arraycopy(defaultArray, 0, largeArray, 0, size);
            defaultArray = largeArray;
        }
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Wrong index: index out of bound");
        }
    }
}
