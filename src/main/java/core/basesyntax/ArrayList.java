package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int LENGTH = 10;

    private T [] defaultArray;
    private int size;

    public ArrayList() {
        this.defaultArray = (T[]) new Object[LENGTH];
        this.size = 0;
    }

    private boolean isFull() {
        if (size >= defaultArray.length) {
            T [] largeArray = (T []) new Object [(int)(defaultArray.length * 1.5)];
            System.arraycopy(defaultArray, 0, largeArray, 0, size);
            defaultArray = largeArray;
        }
        return false;
    }

    @Override
    public void add(T value) {
        isFull();
        defaultArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size) {
            throw new ArrayIndexOutOfBoundsException("Can't add element with this index");
        }
        T[] newArray = (T[]) new Object[size + 1];
        isFull();
        System.arraycopy(defaultArray, 0, newArray, 0, index);
        newArray[index] = value;
        System.arraycopy(defaultArray, index, newArray, index + 1, size - index);
        defaultArray = newArray;
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
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException("Can't get element with this index");
        }
        return defaultArray[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException("Can't set element with this index");
        }
        defaultArray[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException("Can't remove element with this index");
        }
        T removeElement = defaultArray[index];
        System.arraycopy(defaultArray, index + 1, defaultArray, index, size - index - 1);
        size--;
        return removeElement;
    }

    @Override
    public T remove(T t) {
        for (int i = 0; i < size; i++) {
            if ((t == defaultArray[i]) || (t != null) && (t.equals(defaultArray[i]))) {
                System.arraycopy(defaultArray, i + 1, defaultArray, i, size - i - 1);
                break;
            } else if (i == size - 1) {
                throw new NoSuchElementException("This element don't exist in this collection");
            }
        }
        defaultArray[size - 1] = null;
        size--;
        return t;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
