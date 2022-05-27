package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] newArray = new Object[DEFAULT_CAPACITY];
    private int size = 0;

    @Override
    public void add(T value) {
        increaseSize();
        newArray[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        size++;
        if (size == newArray.length) {
            increaseSize();
        }
        if (index < size && index >= 0) {
            System.arraycopy(newArray, index, newArray, index + 1, size - index);
            newArray[index] = value;
        } else {
            getArrayException(index);
        }
    }

    @Override
    public void addAll(List<T> list) {
        increaseSize();
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (index < size && index >= 0) {
            return (T) newArray[index];
        }
        return getArrayException(index);
    }

    @Override
    public void set(T value, int index) {
        getArrayException(index);
        newArray[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < size && index >= 0) {
            final Object removed = newArray[index];
            System.arraycopy(newArray, index + 1, newArray, index, size - index - 1);
            newArray[size - 1] = null;
            size--;
            return (T) removed;
        }
        return getArrayException(index);
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == newArray[i] || newArray[i] != null && newArray[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element cannot be found");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private T getArrayException(int index) {
        if (index < 0 || index >= size || index >= newArray.length) {
            throw new ArrayListIndexOutOfBoundsException("Your index is out of array's size.");
        }
        return null;
    }

    private void increaseSize() {
        if (newArray.length == size) {
            Object[] increasedArray = new Object[(int) (size * 1.5)];
            System.arraycopy(newArray, 0, increasedArray, 0, newArray.length);
            newArray = increasedArray;
        }
    }
}
