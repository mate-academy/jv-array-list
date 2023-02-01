package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int GROW = 2;
    private T[] arrayOfElements;
    private int maxSize = 10;
    private int size = 0;

    public ArrayList() {
        this.arrayOfElements = (T[]) new Object[maxSize];
    }

    @Override
    public void add(T value) {
        if (size == maxSize) {
            grow();
        }
        arrayOfElements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        rangeCheckForAdd(index);
        if (size == maxSize) {
            grow();
        }
        System.arraycopy(arrayOfElements, index, arrayOfElements, index + 1, size - index);
        arrayOfElements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            if (size == maxSize) {
                grow();
            }
            this.arrayOfElements[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        indexExist(index);
        return arrayOfElements[index];
    }

    @Override
    public void set(T value, int index) {
        indexExist(index);
        arrayOfElements[index] = value;
    }

    @Override
    public T remove(int index) {
        indexExist(index);
        size--;
        T oldValue = arrayOfElements[index];
        if (size > index) {
            System.arraycopy(arrayOfElements, index + 1, arrayOfElements, index, size - index);
        }
        arrayOfElements[size] = null;
        return oldValue;
    }

    @Override
    public T remove(T element) {
        int i = 0;
        for (; i < size; i++) {
            if (Objects.equals(arrayOfElements[i], element)) {
                remove(i);
                return element;
            }
        }
        throw new NoSuchElementException("List don`t have element with value: " + element);
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
        maxSize = maxSize + maxSize / GROW;
        arrayOfElements = Arrays.copyOf(arrayOfElements, maxSize);
    }

    private void indexExist(int index) {
        if (index >= size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Element with index " + index + " don`t exist!");
        }
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException(
                    "Can`t put element to index: " + index);
        }
    }
}
