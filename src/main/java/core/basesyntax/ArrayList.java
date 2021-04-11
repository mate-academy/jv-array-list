package core.basesyntax;

import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_ARRAY_SIZE = 10;
    private Object[] array;
    private int currentSize;

    public ArrayList() {
        currentSize = 0;
        this.array = new Object[DEFAULT_ARRAY_SIZE];
    }

    private Object[] grow(Object[] oldArray) {
        Object[] grownArray = new Object [oldArray.length + (oldArray.length >> 1)];
        System.arraycopy(oldArray, 0, grownArray, 0, currentSize);
        return grownArray;
    }

    @Override
    public void add(T value) {
        if (currentSize < array.length) {
            array[currentSize] = value;
            currentSize++;
        } else {
            array = grow(array);
            this.add(value);
        }

    }

    @Override
    public void add(T value, int index) {
        if (index > currentSize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of list bounds");
        }
        if (index == currentSize) {
            add(value);
            return;
        }
        if (array.length <= currentSize - 1) {
            array = grow(array);
        }
        System.arraycopy(array, index, array, index + 1, currentSize - index);
        set(value, index);
        currentSize++;
    }

    @Override
    public void addAll(List<T> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        Object[] listArray = new Object[list.size()];
        while (array.length <= currentSize + listArray.length) {
            array = grow(array);
        }
        for (int i = 0; i < list.size(); i++) {
            listArray[i] = list.get(i);
        }
        for (int i = 0; i < listArray.length; i++) {
            this.add((T)listArray[i]);
        }

    }

    @Override
    public T get(int index) {
        if (index >= currentSize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of list bounds");
        }
        return (T) array[index];
    }

    @Override
    public void set(T value, int index) {
        if (index >= currentSize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of list bounds");
        }
        array[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index >= currentSize || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Index is out of list bounds");
        }
        T value = (T) array[index];
        if (index == currentSize - 1) {
            this.set(null, index);
            currentSize--;
            return value;
        }
        System.arraycopy(array, index + 1, array, index, currentSize - index);
        currentSize--;
        return value;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < currentSize; i++) {
            if (Objects.equals(element, array[i])) {
                return this.remove(i);
            }
        }
        throw new NoSuchElementException("No such element here");
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }
}
